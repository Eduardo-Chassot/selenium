package br.edu.ifrs;

import org.openqa.selenium.By;

//import net.datafaker.Faker;

public class UnidadesMedidaPage {
	private String pathMensagem = "/html/body/app-root/app-container/main/div/app-categoria/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathMensagemDescricaoDuplicada = "/html/body/app-root/app-container/main/div/app-unidade-medida/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathMensagemSucesso = "/html/body/app-root/app-container/main/div/app-unidade-medida/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathMensagemEditadoSucesso = "/html/body/app-root/app-container/main/div/app-unidade-medida/p-toast/div/p-toastitem/div/div/div/div[2]";
	private String pathBotaoSalvar = "/html/body/app-root/app-container/main/div/app-unidade-medida/div[1]/div/div/form/div[2]/button[2]";
	private String pathBotaoNovo = "/html/body/app-root/app-container/main/div/app-unidade-medida/form/div/div[3]/div/button[3]";
	private String pathBotaoUnidadesMedida = "/html/body/app-root/app-header/header/div/div/ul[1]/li[3]/a";
	private String pathUnidadeCadastrada = "/html/body/app-root/app-container/main/div/app-unidade-medida/div[2]/table/tbody/tr[13]/td[3]/i";
	private String pathPrimeiroElementoFiltradoDelete = "/html/body/app-root/app-container/main/div/app-unidade-medida/div[2]/table/tbody/tr[1]/td[4]";
	private String pathAcceptDeleteModal = "/html/body/app-root/app-container/main/div/app-unidade-medida/app-confirm-modal/div/div/div/div[3]/button[2]";
	private String pathFilter = "/html/body/app-root/app-container/main/div/app-unidade-medida/form/div/div[2]/div/input";
	private String pathFilterButton = "/html/body/app-root/app-container/main/div/app-unidade-medida/form/div/div[3]/div/button[2]";
	
	private String msgSucesso = "Medida cadastrada com sucesso!";
	private String msgDescricaoDuplicada = "Já existe uma Unidade de Medida com a mesma descrição!";
	private String msgEditadoSucesso = "Medida editada com sucesso!";
	private String msgDeleteSucesso = "Categoria inativada com sucesso!";
	
	private DSL dsl = new DSL();
	
	public void reload(){
		dsl.reload();
	}
	
	public void setDescricao (String descricao) {
		dsl.escrever("descricao", descricao);
	}

	public String getPathMensagemDescricaoDuplicada() {
		return pathMensagemDescricaoDuplicada;
	}
	
	public void salvar() {
		dsl.clicarBotao(pathBotaoSalvar);
	}
	
	public void novo() {
		dsl.clicarBotao(pathBotaoNovo);
	}
	
	public void filtrarPorNome(String nomeItem) {
		dsl.escrever(By.xpath(pathFilter), nomeItem);
		dsl.clicarBotao(pathFilterButton);
	}
	
	public void excluir(String nome) {
		this.filtrarPorNome(nome);
		dsl.clicarBotao(pathPrimeiroElementoFiltradoDelete);
		dsl.clicarBotao(pathAcceptDeleteModal);
	}

	public String getMsgSucesso() {
		return dsl.obterTexto(By.xpath(pathMensagemSucesso));
	}
	
	public String getMsgDescricaoDuplicada() {
		return dsl.obterTexto(By.xpath(pathMensagemDescricaoDuplicada));
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
	
	public String getMsg() {
		return dsl.obterTexto(By.xpath(pathMensagem));
	}
	
	public String msgEditadoSucesso() {
		return msgEditadoSucesso;
	}
	
	public String getMsgDeleteSucesso() {
		return msgDeleteSucesso;
	}

	public void setMsgDeleteSucesso(String msgDeleteSucesso) {
		this.msgDeleteSucesso = msgDeleteSucesso;
	}

	public void irParaUnidadesMedida() {
		dsl.clicarBotao(pathBotaoUnidadesMedida);
	}
	
	public void editar() {
		dsl.clicarBotao(pathUnidadeCadastrada);	
	}
	
	public String setDescricaoCadastrada() {
		return dsl.obterTexto(By.xpath("/html/body/app-root/app-container/main/div/app-unidade-medida/div[2]/table/tbody/tr[13]/td[2]"));
	}
	
	public String getDescricaoCadastrada() {
		return setDescricaoCadastrada();
	}



	
}
