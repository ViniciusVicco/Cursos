package models;

public class Curso {

	private Integer id;
	private int valor;
	private String nome;
	private String descricao;
	private int horas;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	public int getHoras() {
		return horas;
	}

	public void setHoras(int horas) {
		this.horas = horas;
	}



	@Override
	public String toString() {
		return "Curso [id=" + id + ", valor=" + valor + ", nome=" + nome + ", descrição=" + descricao + ", horas="
				+ horas + "]";
	}
}
