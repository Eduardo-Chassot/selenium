package br.edu.ifrs;

public class Login {

    public static void login(String email, String senha) {
        DriverFactory.getDriver().get("http://35.209.123.161/front");
        LoginPg login = new LoginPg();
        login.setEmail(email);
        login.setSenha(senha);
        login.logar();
    }
}