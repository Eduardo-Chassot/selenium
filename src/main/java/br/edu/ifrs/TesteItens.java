package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

import net.datafaker.Faker;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteItens {
	
	private DSL dsl;
	private static ItensPage page = new ItensPage();
	private static Faker faker = new Faker(new Locale("pt-BR"));
	
	private static String codigoDuplicado = faker.number().digits(15);
	private static String nomeDuplicado = faker.lorem().characters(10, 13) + "_BASE";
	
	private static String CATEGORIA_PADRAO = "494";

	@Parameter
	public String status;
	@Parameter(value = 1)
	public String codigo;
	@Parameter(value = 2)
	public String categoria;
	@Parameter(value = 3)
	public String nome;
	@Parameter(value = 4)
	public String unidadeMedida;
	@Parameter(value = 5)
	public String valorMinimo;
	@Parameter(value = 6)
	public boolean policiaFederal;
	@Parameter(value = 7)
	public boolean exercito;
	@Parameter(value = 8)
	public String resposta;

	@BeforeClass
	public static void setUpClass() {
		Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");

        criarItemBase(nomeDuplicado, codigoDuplicado);
	}
	
    private static void criarItemBase(String nome, String codigo) {
    	
    	System.out.println("Nome: " + nome + " codigo: " + codigo);
        page.irParaItens();
        page.inicarCriacao();
        page.setNome(nome);
        page.setStatus("A");
        page.setCodigo(codigo);
        page.setCategoria("494");
        page.setUnidadeMedida("559");
        page.setValorMinimo("100");
        page.enviarItem();
 
    }

	
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("http://35.209.123.161/front");
		dsl = new DSL();
		
		page.irParaItens();
	}
	

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"I", codigoDuplicado, CATEGORIA_PADRAO, faker.lorem().characters(10, 20), "559", faker.number().digit(), true, true, "Error: Já existe um Elemento com o mesmo código!"},
			{"I", faker.number().digits(15), CATEGORIA_PADRAO, nomeDuplicado, "407", "15", false, false, "Error: Já existe um Elemento com o mesmo nome!"},
			{"A", faker.number().digits(15), CATEGORIA_PADRAO, faker.lorem().characters(10, 20), "407", "10", true, false, "Item cadastrado com sucesso!"}
		});
	}

	@Test
	public void t01_deveValidarRegrasNegocioItens() throws IOException {
		
		page.inicarCriacao();
		page.setNome(nome);
		page.setStatus(status);
		page.setCodigo(codigo);
		page.setUnidadeMedida(unidadeMedida);
		page.setCategoria(categoria);
		page.setValorMinimo(valorMinimo);

		if (policiaFederal) {
			page.clickPoliciaFederal();
		}
		if (exercito) {
			page.clickExercito();
		}

		page.enviarItem();

		Assert.assertEquals(resposta, dsl.obterTexto(By.xpath(page.pathMensagem)));
		
	}
	
	@Test
	public void t02_validarInsercao() throws IOException{
		
		if (!resposta.equals("Item cadastrado com sucesso!")) {
	        System.out.println("Ignorando inserção para itens não cadastrados");
	        return;
	    }
		
		page.clicarEditarPorNome(nome);
		
		System.out.println(nome);
		Assert.assertEquals(nome, page.obterValorNome());
		Assert.assertEquals(codigo, page.obterValorCodigo());
		Assert.assertEquals(unidadeMedida, page.obterValorUnidadeMedida());
		Assert.assertEquals(categoria, page.obterValorCategoria());
		Assert.assertEquals(valorMinimo, page.obterValorMinimo());
		Assert.assertEquals(policiaFederal, page.isPoliciaFederalMarcado());
		Assert.assertEquals(exercito, page.isExercitoMarcado());
		
	}
	
	@Test
	public void t03_validarEdicaoNomeDuplicado() throws IOException {

		if (!resposta.equals("Item cadastrado com sucesso!")) {
	        System.out.println("Ignorando inserção para itens não cadastrados");
	        return;
	    }
		page.irParaItens();
		
		page.clicarEditarPorNome(nome);
		
		page.setNome(nomeDuplicado);

		page.enviarItem();

		Assert.assertEquals("Error: Já existe um Elemento com o mesmo nome!", dsl.obterTexto(By.xpath(page.pathMensagem)));
		
	}
	
	@Test
	public void t04_validarEdicaoCodigoDuplicado() throws IOException {

		if (!resposta.equals("Item cadastrado com sucesso!")) {
	        System.out.println("Ignorando inserção para itens não cadastrados");
	        return;
	    }
		
		page.irParaItens();
		
		page.clicarEditarPorNome(nome);

		page.setCodigo(codigoDuplicado);

		page.enviarItem();

		Assert.assertEquals("Error: Já existe um Elemento com o mesmo código!", dsl.obterTexto(By.xpath(page.pathMensagem)));
		
	}
	
	@Test
	public void t05_deveExcluir() throws IOException {
		
	    if (!resposta.equals("Item cadastrado com sucesso!")) {
	        System.out.println("Ignorando exclusão para itens não cadastrados");
	        return;
	    }
	    
	    page.irParaItens();
	    System.out.println(nome);
	    page.clicarExcluirPorNome(nome);
	    Assert.assertEquals("Sucesso\nElemento inativado com sucesso!", dsl.obterTexto(By.xpath(page.pathToast)));
	}
	
	
	@AfterClass
	public static void cleanUp() {
		page.reload();
		page.irParaItens();
		System.out.println(nomeDuplicado);
		page.clicarExcluirPorNome(nomeDuplicado);
		DriverFactory.killDriver();
	}

}
