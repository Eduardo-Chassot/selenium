package br.edu.ifrs;

import org.openqa.selenium.By;

public class EstoquePage {
	private String pathMensagem = "/html/body/app-root/app-container/main/div/app-movimentacao/p-toast/div/p-toastitem/div/div/div/div[2]";
	//private String pathMensagemEditadoSucesso = "/html/body/app-root/app-container/main/div/app-movimentacao/p-toast/div/p-toastitem";
	private String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[2]/button[2]";
	private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[5]/div/button[3]";
	private String pathBotaoEstoque = "/html/body/app-root/app-header/header/div/div/ul[1]/li[7]";
	private String pathCampoItem = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[1]/div[1]/select";
	private String pathCampoSubitem = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[1]/div[2]/select";
	private String pathCampoTipo = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[2]/div[1]/select";
	private String pathFilter = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[2]/div/select";
	private String pathFilterButton = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[5]/div/button[2]";
	//private String pathPrimeiroElementoFiltrado = "/html/body/app-root/app-container/main/div/app-movimentacao/div[2]/table/tbody/tr[1]/td[1]";
	private String pathPrimeiroElementoFiltradoVisu = "/html/body/app-root/app-container/main/div/app-movimentacao/div[2]/table/tbody/tr[1]/td[7]";
	private String pathPrimeiroElementoFiltradoDelete = "/html/body/app-root/app-container/main/div/app-movimentacao/div[2]/table/tbody/tr[1]/td[8]";
	private String pathAcceptDeleteModal = "/html/body/app-root/app-container/main/div/app-movimentacao/app-confirm-modal/div/div/div/div[3]/button[2]";
	private String msgSucesso = "Movimentação cadastrada com sucesso!";
	//private String msgDescricaoDuplicada = "Já existe uma Marca com o mesmo nome!";
	//private String msgEditadoSucesso = "Marca editada com sucesso!";
	public String msgDeleteSucesso = "Estoque inativada com sucesso!";
	
	private DSL dsl = new DSL();
	
	public void irParaEstoque() {
		dsl.clicarBotao(pathBotaoEstoque);
	}
	
	//Campos
	
	public String getItem () {
		return dsl.obterValueCombo(pathCampoItem);
	}
	public void setItem(String item) {
		dsl.selecionarComboXPath(pathCampoItem, item);
	}
	
	
	public String getSubitem () {
		return dsl.obterValueCombo(pathCampoSubitem);
	}
	public void setSubitem(String subitem) {
		dsl.selecionarComboXPath(pathCampoItem, subitem);
	}
	
	public String getTipo () {
		return dsl.obterValueCombo(pathCampoTipo);
	}
	public void setTipo(String tipo) {
		dsl.selecionarComboXPath(pathCampoItem, tipo);
	}


	//Botões
	
	public void salvar() {
		dsl.clicarBotao(pathBotaoSalvar);
	}
	
	public void novo() {
		dsl.clicarBotao(pathBotaoNovo);
	}
	
	public void visualizar(String Elemento) {
		this.filtrarPorElemento(Elemento);
		dsl.clicarBotao(pathPrimeiroElementoFiltradoVisu);
	}

	public void excluir(String Elemento) {
		this.filtrarPorElemento(Elemento);
		dsl.clicarBotao(pathPrimeiroElementoFiltradoDelete);
		dsl.clicarBotao(pathAcceptDeleteModal);
	}

	//msg
	
	public String getMsg() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}

	/**
	public String getMsgEditadoSucesso() {
		return dsl.obterTexto(By.xpath(pathMensagemEditadoSucesso));
	}	
	
	public String msgDescricaoDuplicada() {
		return msgDescricaoDuplicada;
	}
	
	public String msgEditadoSucesso() {
		return msgEditadoSucesso;
	}
	**/
	
	public String msgSucesso() {
		return msgSucesso;
	}
	
	/**
	public String getElementoCadastrado() {
		return dsl.obterTexto(By.xpath(pathPrimeiroElementoFiltrado));
	}
	**/
	public void filtrarPorElemento(String ElementoItem) {
		dsl.escrever(By.xpath(pathFilter), ElementoItem);
		dsl.clicarBotao(pathFilterButton);
	}
	

	public void reload(){
		dsl.reload();
	}

	public String getMsgDeleteSucesso() {
		return msgDeleteSucesso;
	}

	public void setMsgDeleteSucesso(String msgDeleteSucesso) {
		this.msgDeleteSucesso = msgDeleteSucesso;
	}
	
}