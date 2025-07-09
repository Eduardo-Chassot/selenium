package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.datafaker.Faker;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TesteUnidadesMedida {
	private static UnidadesMedidaPage page = new UnidadesMedidaPage();
	
	@Parameter
	public String descricao;
	@Parameter(value=1)
	public String msgSucesso;
	@Parameter(value=2)
	public String msgDescDuplicada;
	@Parameter(value=3)
	public String msgEditadoSucesso;

	@BeforeClass
	public static void setUpClass() {
	Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
	}
	
	@Before
	public void logar(){
		page.reload();
		page.irParaUnidadesMedida();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		Faker faker = new Faker();
		String descricaoGerada = faker.commerce().productName();
		return Arrays.asList(new Object[][] {
			{descricaoGerada, page.msgSucesso(), page.msgDescricaoDuplicada(), page.msgEditadoSucesso()},
		});
	}
	
	@Test
	public void deveCadastrarNovoUnidade() throws IOException, InterruptedException {
		page.novo();
		page.setDescricao(descricao);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsgSucesso());
		Assert.assertEquals(msgSucesso, page.getMsgSucesso());
	}
	
	@Test
	public void deveCadastrarComDescricaoDuplicada() throws IOException {
		page.novo();
		page.setDescricao(descricao);
		page.salvar();	
		System.out.println("mensagemDuplicada: " + page.getMsgDescricaoDuplicada());
		Assert.assertEquals(msgDescDuplicada, page.getMsgDescricaoDuplicada());
	}
	
	@Test
	public void deveEditaUnidadea() throws IOException {
		page.setDescricaoCadastrada();
		page.editar();
		System.out.println(page.getDescricaoCadastrada());
		page.setDescricao(page.getDescricaoCadastrada() + "EDIT");
		page.salvar();	
		System.out.println("mensagemEditadoSucesso: " + page.getMsgEditadoSucesso());
		Assert.assertEquals(msgEditadoSucesso, page.getMsgEditadoSucesso());
	}
	
	public void t07_deveExcluirUnidade() throws IOException {
		page.excluir(descricao + "EDIT");
		Assert.assertEquals(page.getMsgDeleteSucesso(), page.getMsg());
	}
	
	@AfterClass
	public static void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
	

	
}

