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
public class TesteLocais {
	
	private static LocaisPage page = new LocaisPage();
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
		 Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
		
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
	public void t01_deveCastrarNovoLocal() throws IOException, InterruptedException {
		page.novo();
		page.setNome(nome);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsg());
		System.out.println("Nome: " + nome);
		Assert.assertEquals(msgSucesso, page.getMsg());
	}
	
	@Test
	public void t02_validaCadastroLocal() throws IOException, InterruptedException {
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
	public void t04_deveEditarLocalDuplicando() throws IOException {

		page.editarDuplicado();
		page.setNome(nome);
		page.salvar();	
		System.out.println("t04: " + page.getMsg());
		Assert.assertEquals(msgNomeDuplicado, page.getMsg());

	}
	
	@Test
	public void t05_deveEditarLocal() throws IOException {

		page.editar(nome);
		page.setNome(nome + "EDIT");
		page.salvar();	
		System.out.println("mensagemEditadoSucesso: " + page.getMsg());
		Assert.assertEquals(msgEditadoSucesso, page.getMsg());
	}
	
	@Test
	public void t06_deveExcluirlocal() throws IOException {
		page.excluir(nome + "EDIT");
		Assert.assertEquals(page.msgDeleteSucesso, page.getMsg());
	}
	
	
	@AfterClass
	public static void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
    
	
	
}
