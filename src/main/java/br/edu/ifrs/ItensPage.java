package br.edu.ifrs;

import org.openqa.selenium.By;

import com.gargoylesoftware.htmlunit.javascript.host.Console;

public class ItensPage {

	private String pathBotaoItem = "/html/body/app-root/app-header/header/div/div/ul[1]/li[2]/a";
	public String pathMensagem = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathBotaoIniciarCriacao = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/a";
	private String pathBotaoSubmit = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[2]/button";
	
	private String inputCodigo            = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[2]/input";
	private String inputNome              = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[3]/input";
	private String inputQuantidadeMinima  = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[6]/input";
	private String pathAcceptModal 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/app-confirm-modal/div/div/div/div[3]/button[2]";
	private String pathFilterName 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[3]/div/input";
	private String pathFilterBtn 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/button[2]";

	
	private DSL dsl = new DSL();
	
	public void setCodigo(String codigo) {
		dsl.escrever(By.xpath(inputCodigo), codigo);
	}
	
	public void setNome(String nome) {
		dsl.escrever(By.xpath(inputNome), nome);
	}
	
	public void setStatus(String status) {
		dsl.selecionarComboByValue("status", status);
	}
	
	public void setUnidadeMedida(String unidadeMedida) {
		dsl.selecionarComboByValue("unidadeMedidaId", unidadeMedida);
	}
	
	public void setCategoria(String categoria) {
		dsl.selecionarComboByValue("categoriaId", categoria);
	}
	
	public void setValorMinimo(String valorMinimo) {
		dsl.escrever(By.xpath(inputQuantidadeMinima), valorMinimo);
	}
	
	public void clickPoliciaFederal() {
		dsl.clicarCheck("monitoradoPF");
	}
	
	public void clickExercito() {
		dsl.clicarCheck("exercito");
	}
	
	public void irParaItens() {
		dsl.clicarBotao(pathBotaoItem);
	}
	
	public void inicarCriacao() {
		dsl.clicarBotao(pathBotaoIniciarCriacao);
	}
	
	public void enviarItem() {
		dsl.clicarBotao(pathBotaoSubmit);
	}
	
	public void clicarExcluirPorNome(String nomeItem) {
		dsl.escrever(By.xpath(pathFilterName), nomeItem);
		dsl.clicarBotao(pathFilterBtn);
        String xpathBotaoExcluir = "//td[contains(normalize-space(.), '" + nomeItem + "')]/parent::tr//i[contains(@class, 'fa-ban')]";
        System.out.println(xpathBotaoExcluir);
        dsl.clicarBotao(xpathBotaoExcluir);
        dsl.clicarBotao(pathAcceptModal);
    }
	
	
}
