package br.edu.ifrs;

import org.openqa.selenium.By;

public class LocaisPage {
    								//html/body/app-root/app-container/main/div/app-local/p-toast/div/p-toastitem/div/div/div/div[1]
	private String pathMensagem = "/html/body/app-root/app-container/main/div/app-local/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathMensagemEditadoSucesso = "/html/body/app-root/app-container/main/div/app-local/p-toast/div/p-toastitem";
	private String msgSucesso = "Local cadastrado com sucesso!";
	
	private String msgDescricaoDuplicada = "Já existe um Local com o mesmo nome!";
	private String msgEditadoSucesso = "Local editado com sucesso!";
	public String msgDeleteSucesso = "Local inativado com sucesso!";
	
	private String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-local/div[1]/div/div/form/div[2]/button[2]";
	private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[3]";
	private String pathBotaoLocais = "//html/body/app-root/app-header/header/div/div/ul[1]/li[4]";
	
	private String pathFilter = "/html/body/app-root/app-container/main/div/app-local/form/div/div[2]/div/input";
	private String pathFilterButton = "/html/body/app-root/app-container/main/div/app-local/form/div/div[3]/div/button[2]";
	private String pathPrimeiroElementoFiltrado = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr[1]/td[2]";
	private String pathSegundoElementoFiltradoEdit = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr[2]/td[3]";
	private String pathPrimeiroElementoFiltradoEdit = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr[1]/td[3]";
	private String pathPrimeiroElementoFiltradoDelete = "/html/body/app-root/app-container/main/div/app-local/div[2]/table/tbody/tr[1]/td[4]";
	private String pathAcceptDeleteModal = "/html/body/app-root/app-container/main/div/app-local/app-confirm-modal/div/div/div/div[3]/button[2]";
	
	private DSL dsl = new DSL();
	
	public void setNome (String descricao) {
		dsl.escrever("nome", descricao);
	}
	
	//Acessar Página
	
	public void irParaMarcas() {
		dsl.clicarBotao(pathBotaoLocais);
	}

	//Botões
	
	public void salvar() {
		dsl.clicarBotao(pathBotaoSalvar);
	}
	
	public void novo() {
		dsl.clicarBotao(pathBotaoNovo);
	}
	
	public void editarDuplicado() {
		dsl.clicarBotao(pathSegundoElementoFiltradoEdit);
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

	//Mensagem
	
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
	
	//Get
	
	public String getNomeCadastrado() {
		return dsl.obterTexto(By.xpath(pathPrimeiroElementoFiltrado));
	}
	
	//Filtro
	
	public void filtrarPorNome(String nomeItem) {
		dsl.escrever(By.xpath(pathFilter), nomeItem);
		dsl.clicarBotao(pathFilterButton);
	}
	

	public void reload(){
		dsl.reload();
	}
	
}
