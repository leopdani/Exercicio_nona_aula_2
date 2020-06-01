package ex_2.model;

public class Usuario {

	private int id;
	private String nome;
	private String senha;
	private String login;
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getSenha() {
		return senha;
	}
	public String getLogin() {
		return login;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setLogin(String login) {
		this.login = login;
	}
}