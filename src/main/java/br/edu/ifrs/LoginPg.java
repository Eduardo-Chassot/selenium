package br.edu.ifrs;

public class LoginPg {

	private String pathBotaoLogin = "/html/body/app-root/app-container/div/app-login/div/div/div[2]/div/form/button";
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
	
	public String getPathBotaoLogin() {
		return pathBotaoLogin;
	}
	
	public void logar() {
		dsl.clicarBotao(pathBotaoLogin);
	}	
}
