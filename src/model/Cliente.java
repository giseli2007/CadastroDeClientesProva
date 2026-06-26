package model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Cliente {
	
	private String nome;
	private String telefone;
	private String email;
	private String sexo;
	private int id;
	private String dataCadastro;
	private static final DateTimeFormatter dataformatada = DateTimeFormatter.ofPattern("yyyy-MM-dd");//B3-Q1, B5-Q3 e da formatacao da data pra ficar igual a do banco de dados
	public Cliente(String nome, String telefone, String email, String sexo) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.sexo = sexo;
		this.dataCadastro = LocalDate.now().format(dataformatada);//B3-Q1
	}
	
	public Cliente(int id, String nome, String telefone, String email, String sexo) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.sexo = sexo;
		this.id = id;
		this.dataCadastro = LocalDate.now().format(dataformatada);//B3-Q1
	}
	public Cliente(int id, String nome, String telefone, String email, String sexo, String dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
		this.sexo = sexo;
		//nesse construtor precisa pega o valor direto do banco de dados, se nao na tabela só vai mostrar todos os usuarios com a data de hoje
		this.dataCadastro = dataCadastro != null ? dataCadastro : LocalDate.now().format(dataformatada);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	

}