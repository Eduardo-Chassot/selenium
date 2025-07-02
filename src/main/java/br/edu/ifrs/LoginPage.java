package br.edu.ifrs;

public class LoginPage {

	private String pathMensagemSenhaIncorreta = "/html/body/app-root/app-container/div/app-login/div/div/div[2]/div/div";
	private String pathBotaoLogin = "/html/body/app-root/app-container/div/app-login/div/div/div[2]/div/form/button";
	private String msgSenhaIncorreta = "Usuário ou senha inválidos.";
	private DSL dsl = new DSL();
	
	public void setEmail(String email) {
		dsl.escrever("email", email);
	}
	
	public void setSenha(String senha) {
		dsl.escrever("senha", senha);
	}
	
	public String getEmail() {
		return dsl.obterValorCampo("email");
	}
	
	public String getSenha() {
		return dsl.obterValorCampo("senha");
	}
	
	public String getPathMensagemSenhaIncorreta() {
		return pathMensagemSenhaIncorreta;
	}
	
	public String getPathBotaoLogin() {
		return pathBotaoLogin;
	}
	
	public String getMsgSenhaIncorreta() {
		return msgSenhaIncorreta;
	}
	
	public void logar() {
		dsl.clicarBotao(pathBotaoLogin);
	}	
}
