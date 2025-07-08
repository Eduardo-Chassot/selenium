package br.edu.ifrs;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

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
	public String subitem;
	@Parameter(value=3)
	public String tipo;

	@BeforeClass
	public static void setup() {
	    Login.login("eduardo.chassot@aluno.feliz.ifrs.edu.br", "ratones");
	}
	
	@Before
	public void logar(){
		page.reload();
		page.irParaEstoque();
	}
	
	
	@Parameters
	public static Collection<Object[]> getCollection() {
		return Arrays.asList(new Object[][] {
			{page.getItem(), page.msgSucesso(), page.getSubitem(), page.getTipo()},
		});
	}
	
	@Test
	public void t01_deveCadastrarNovoEstoqueEntrada() throws IOException, InterruptedException {
		page.novo();
		page.setItem(item);
		page.setSubitem(subitem);
		page.setTipo(tipo);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsg());
		Assert.assertEquals(msgSucesso, page.getMsg());
	}
	
	
	@Test
	public void t02_deveCadastrarNovoEstoqueEntrada() throws IOException, InterruptedException {
		page.novo();
		page.setItem(item);
		page.setSubitem(subitem);
		page.setTipo(tipo);
		page.salvar();	
		System.out.println("mensagem: " + page.getMsg());
		Assert.assertEquals(msgSucesso, page.getMsg());
	}

	public void t03_deveExcluirEstoque() throws IOException {
		page.excluir(item);
		System.out.println("mensagem delet: " + page.getMsgDeleteSucesso());
		Assert.assertEquals(page.getMsgDeleteSucesso(), page.getMsg());
	}
	
	@After
	public void fechar() throws InterruptedException{
		DriverFactory.killDriver();
	}
	

	
}
