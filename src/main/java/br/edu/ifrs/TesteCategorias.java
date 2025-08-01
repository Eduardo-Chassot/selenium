package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.datafaker.Faker;

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

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TesteCategorias {
	
	private static MarcasPage page = new MarcasPage();
	public static Faker faker = new Faker();
	public static String nomeDuplicado = faker.lorem().characters(10, 13) + "_BASE";
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String msgSucesso;
	@Parameter(value=2)
	public String msgNomeDuplicado;
	@Parameter(value=3)
	public String msgEditadoSucesso;

	@BeforeClass
	public static void setup() {
		
		DriverFactory.getDriver().get("http://35.209.123.161/front");
		Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
		page.irParaMarcas();
		page.novo();
		page.setNome(nomeDuplicado);
		page.salvar();
		
	}
	
	@Before
	public void logar(){
		page.reload();
		page.irParaMarcas();
	}
	
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		String nomeGerado = faker.commerce().productName();
		return Arrays.asList(new Object[][] {
			{nomeGerado, page.msgSucesso(), page.msgDescricaoDuplicada(), page.msgEditadoSucesso()},
		});
	}
	
	@Test
	public void t01_deveCastrarNovaCategoria() throws IOException, InterruptedException {
		page.novo();
		page.setNome(nome);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsg());
		Assert.assertEquals(msgSucesso, page.getMsg());
	}
	
	@Test
	public void t02_validaCadastroCategoria() throws IOException, InterruptedException {
		page.filtrarPorNome(nome);
		Assert.assertEquals(page.getNomeCadastrado(), nome);
	}
	
	@Test
	public void t03_deveCadastrarComNomeDuplicado() throws IOException {
		page.novo();
		page.setNome(nome);
		page.salvar();
		Assert.assertEquals(msgNomeDuplicado, page.getMsg());
	}
	
	@Test
	public void t04_deveEditarMarcaDuplicando() throws IOException {

		page.editar(nome);
		page.setNome(nomeDuplicado);
		page.salvar();	
		Assert.assertEquals(msgNomeDuplicado, page.getMsg());

	}
	
	@Test
	public void t05_deveEditarCategoria() throws IOException {

		page.editar(nome);
		page.setNome(nome + "EDIT");
		page.salvar();	
		System.out.println("mensagemEditadoSucesso: " + page.getMsg());
		Assert.assertEquals(msgEditadoSucesso, page.getMsg());
	}
	
	public void t06_validaCadastroCategoriaEdit() throws IOException, InterruptedException {
		page.filtrarPorNome(nome + "EDIT");
		Assert.assertEquals(page.getNomeCadastrado(), nome + "EDIT");
	}
	
	
	public void t07_deveExcluirCategoria() throws IOException {
		page.excluir(nome + "EDIT");
		Assert.assertEquals(page.msgDeleteSucesso, page.getMsg());
	}
	
	
	@AfterClass
	public static void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
	

	
}

