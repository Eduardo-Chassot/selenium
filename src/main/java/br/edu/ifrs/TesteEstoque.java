package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.datafaker.Faker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TesteEstoque {
	private static EstoquePage page = new EstoquePage();

    @Parameter
	public String descricao;
	@Parameter(value=1)
	public String msgSucesso;
	@Parameter(value=2)
	public String msgDescDuplicada;
	@Parameter(value=3)
	public String msgEditadoSucesso;

	
	@Before
	public void logar(){
	    Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
		page.irParaUnidadesMedida();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		Faker faker = new Faker();
		String descricaoGerada = faker.commerce().productName();
		return Arrays.asList(new Object[][] {
			{descricaoGerada, page.msgSucesso(), page.msgSucesso(), page.msgEditadoSucesso()},
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
		System.out.println("mensagemDuplicada: " + page.getMsgSucesso());
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
	
	@After
	public void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
	

	
}
