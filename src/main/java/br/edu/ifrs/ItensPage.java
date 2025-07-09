package br.edu.ifrs;

import org.openqa.selenium.By;

//import com.gargoylesoftware.htmlunit.javascript.host.Console;

public class ItensPage {

	public String pathBotaoItem = "/html/body/app-root/app-header/header/div/div/ul[1]/li[2]/a";
	public String pathMensagem = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/p-toast/div/p-toastitem/div/div/div/div[2]";
	public String pathBotaoIniciarCriacao = "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/a";
	public String pathBotaoSubmit = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[2]/button";
	
	public String inputCodigo            = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[2]/input";
	public String inputNome              = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[3]/input";
	public String inputQuantidadeMinima  = "/html/body/app-root/app-container/main/div/app-cadastro-elemento/form/div[1]/div[1]/div/div/div[6]/input";
	public String pathAcceptModal 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/app-confirm-modal/div/div/div/div[3]/button[2]";
	public String pathToast 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/p-toast/div";
	public String pathFilterName 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[3]/div/input";
	public String pathFilterBtn 			= "/html/body/app-root/app-container/main/div/app-lista-elemento/form/div/div[4]/div/button[2]";

	
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
		this.filtrarPorNome(nomeItem);
        String xpathBotaoExcluir = "//td[contains(normalize-space(.), '" + nomeItem + "')]/parent::tr//i[contains(@class, 'fa-ban')]";
        dsl.clicarBotao(xpathBotaoExcluir);
        dsl.clicarBotao(pathAcceptModal);
    }
	
	public void clicarEditarPorNome(String nomeItem){
		this.filtrarPorNome(nomeItem);
        String xpathBotaoEditar = "/html/body/app-root/app-container/main/div/app-lista-elemento/div/table/tbody/tr[1]/td[4]";
        dsl.clicarBotao(xpathBotaoEditar);
	}
	
	private void filtrarPorNome(String nomeItem) {
		dsl.escrever(By.xpath(pathFilterName), nomeItem);
		dsl.clicarBotao(pathFilterBtn);
	}
	
	public String obterValorNome() {
		return dsl.obterValorCampo(By.xpath(inputNome));
	}

	public String obterValorCodigo() {
		return dsl.obterValorCampo(By.xpath(inputCodigo));
	}

	public String obterValorUnidadeMedida() {
		return dsl.obterValueCombo("unidadeMedidaId");
	}

	public String obterValorCategoria() {
		return dsl.obterValueCombo("categoriaId");
	}

	public String obterValorMinimo() {
		return dsl.obterValorCampo(By.xpath(inputQuantidadeMinima));
	}

	public boolean isPoliciaFederalMarcado() {
		return dsl.isCheckMarcado("monitoradoPF");
	}

	public boolean isExercitoMarcado() {
		return dsl.isCheckMarcado("exercito");
	}
	
	public void reload(){
		dsl.reload();
	}
	
	
}
