package br.edu.ifrs;

import org.openqa.selenium.By;

public class EstoquePage {
	private String pathMensagem = "/html/body/app-root/app-container/main/div/app-movimentacao/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathMensagemEditadoSucesso = "/html/body/app-root/app-container/main/div/app-movimentacao/p-toast/div/p-toastitem";
	private String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[2]/button[2]";
	private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-movimentacao/form/div/div[5]/div/button[3]";
	private String pathBotaoEstoque = "/html/body/app-root/app-header/header/div/div/ul[1]/li[7]";
	private String pathCampoItem = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[1]/div[1]/select";
	private String pathCampoSubitem = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[1]/div[2]/select";
	private String pathCampoTipo = "/html/body/app-root/app-container/main/div/app-movimentacao/div[1]/div/div/form/div[1]/div[2]/div[1]/select";
	//private String pathFilter = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[2]/div/input";
	//private String pathFilterButton = "/html/body/app-root/app-container/main/div/app-marca/form/div/div[3]/div/button[2]";
	//private String pathPrimeiroElementoFiltrado = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[2]";
	//private String pathPrimeiroElementoFiltradoEdit = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[3]";
	//private String pathPrimeiroElementoFiltradoDelete = "/html/body/app-root/app-container/main/div/app-marca/div[2]/table/tbody/tr[1]/td[4]";
	//private String pathAcceptDeleteModal = "/html/body/app-root/app-container/main/div/app-marca/app-confirm-modal/div/div/div/div[3]/button[2]";
	//private String msgSucesso = "Marca cadastrada com sucesso!";
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
	
	
	public String getSubitemtem () {
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

	public String getMsg() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}
	
	public String getMsgEditadoSucesso() {
		return dsl.obterTexto(By.xpath(pathMensagemEditadoSucesso));
	}	
	
	public String msgDescricaoDuplicada() {
		return msgDescricaoDuplicada;
	}
	
	public String msgSucesso() {
		return msgSucesso;
	}
	
	public String msgEditadoSucesso() {
		return msgEditadoSucesso;
	}
	
	public void irParaMarcas() {
		dsl.clicarBotao(pathBotaoUnidadesMedida);
	}
	
	public void editar(String nome) {
		this.filtrarPorNome(nome);
		dsl.clicarBotao(pathPrimeiroElementoFiltradoEdit);
	}
	
	public void excluir(String nome) {
		this.filtrarPorNome(nome);
		dsl.clicarBotao(pathPrimeiroElementoFiltradoDelete);
		dsl.clicarBotao(pathAcceptDeleteModal);
	}
	
	public String getNomeCadastrado() {
		return dsl.obterTexto(By.xpath(pathPrimeiroElementoFiltrado));
	}
	
	public void filtrarPorNome(String nomeItem) {
		dsl.escrever(By.xpath(pathFilter), nomeItem);
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