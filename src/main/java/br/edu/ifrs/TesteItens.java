package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;

@RunWith(Parameterized.class)
public class TesteItens {
	private DSL dsl;
	private static ItensPage page = new ItensPage();
	private static LoginPage loginPage = new LoginPage();

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
	    DriverFactory.getDriver().get("http://35.209.123.161/front");
	    loginPage.setEmail("eduardo.chassot@aluno.feliz.ifrs.edu.br");
	    loginPage.setSenha("ratones");
	    loginPage.logar();
	}
	
	@Before
	public void inicializar() {
		DriverFactory.getDriver().get("http://35.209.123.161/front");
		dsl = new DSL();
		
		page.irParaItens();
		page.inicarCriacao();
	}

	public static String gerarCodigo15Digitos() {
		Random rand = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 15; i++) {
			sb.append(rand.nextInt(10));
		}
		return sb.toString();
	}

	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{"I", "DG 046d9", "397", "Item " + gerarCodigo15Digitos(), "312", "1000", true, true, "Error: Já existe um Elemento com o mesmo código!"},
			{"I", gerarCodigo15Digitos(), "397", "Item Z", "407", "15", false, false, "Error: Já existe um Elemento com o mesmo nome!"},
			{"A", gerarCodigo15Digitos(), "397", "Item " + gerarCodigo15Digitos(), "407", "10", true, false, "Item cadastrado com sucesso!"}
		});
	}

	@Test
	public void deveValidarRegrasNegocioItens() throws IOException {

		System.out.println("Preenchendo nome...");
		page.setNome(nome);

		System.out.println("Setando status...");
		page.setStatus(status);

		System.out.println("Setando codigo...");
		page.setCodigo(codigo);
		
		System.out.println("Setando unidadeMedida...");
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

		System.out.println("Validando mensagem...");
		Assert.assertEquals(resposta, dsl.obterTexto(By.xpath(page.pathMensagem)));
		
	}
	
	@Test
	public void deveExcluir() throws IOException {
		
	    if (!resposta.equals("Item cadastrado com sucesso!")) {
	        System.out.println("Ignorando exclusão para itens não cadastrados");
	        return;
	    }
	    
	    page.irParaItens();
	    System.out.println(nome);
	    page.clicarExcluirPorNome(nome);
	    Assert.assertEquals("Elemento inativado com sucesso!", dsl.obterTexto(By.xpath(page.pathMensagem)));
	}
}
