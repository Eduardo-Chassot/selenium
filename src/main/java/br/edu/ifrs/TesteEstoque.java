package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import net.datafaker.Faker;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TesteEstoque {
	private static EstoquePage page = new EstoquePage();

    @Parameter
	public String item;
	@Parameter(value=1)
	public String msgSucesso;
	@Parameter(value=2)
	public String msgDescDuplicada;
	@Parameter(value=3)
	public String msgEditadoSucesso;
	@Parameter(value=4)
	public String subitem;
	@Parameter(value=5)
	public String tipo;

	@BeforeClass
	public static void setup() {
	    Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
		page.irParaEstoque();
	}
	
	@Before
	public void logar(){
		page.reload();
		page.irParaEstoque();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{page.getItem(), page.msgSucesso(), page.msgSucesso(), page.msgEditadoSucesso(), page.getSubitem(), page.getTipo()},
		});
	}
	
	@Test
	public void t01_deveCadastrarNovoEstoque() throws IOException, InterruptedException {
		page.novo();
		page.setItem(item);
		page.setSubitem(subitem);
		page.setTipo(tipo);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsgSucesso());
		Assert.assertEquals(msgSucesso, page.getMsgSucesso());
	}

	@Test
	public void t02_validaCadastroEstoque() throws IOException, InterruptedException {
		page.filtrarPorNome(nome);
		Assert.assertEquals(page.getNomeCadastrado(), nome);
	}
	
	/**@Test
	public void deveCadastrarComDescricaoDuplicada() throws IOException {
		page.novo();
		page.setItem(item);
		page.setSubitem(subitem);
		page.setTipo(tipo);
		page.salvar();	
		System.out.println("mensagemDuplicada: " + page.getMsgSucesso());
		Assert.assertEquals(msgDescDuplicada, page.getMsgDescricaoDuplicada());
	}
	**/
	@Test
	public void t03_deveEditaEstoque() throws IOException {
		page.setDescricaoCadastrada();
		page.editar();
		System.out.println(page.getDescricaoCadastrada());
		page.setDescricao(page.getDescricaoCadastrada() + "EDIT");
		page.salvar();	
		System.out.println("mensagemEditadoSucesso: " + page.getMsgEditadoSucesso());
		Assert.assertEquals(msgEditadoSucesso, page.getMsgEditadoSucesso());
	}

	public void t04_deveExcluirEstoque() throws IOException {
		page.excluir(nome + "EDIT");
		System.out.println("mensagem delet: " + page.msgDeleteSucesso());
		Assert.assertEquals(page.msgDeleteSucesso, page.getMsg());
	}
	
	@After
	public void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
	

	
}
