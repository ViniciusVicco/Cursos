package models;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Curso {
	@Id @GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private int valor;
	@Column(nullable = false)
	private String nome;
	@Column(nullable = false)
	private String descricao;
	@Transient
	private String nomeDescricao;
	private int horas;
	private LocalDate datacriacao;
	
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

	public String getNomeDescricao() {
		return getNome() + "" + getDescricao();

	}

	public void setNomeDescricao(String nomeDescricao) {
		this.nomeDescricao = nomeDescricao;
	}

	public LocalDate getDatacriacao() {
		return datacriacao;
	}

	public void setDatacriacao(LocalDate datacriacao) {
		this.datacriacao = datacriacao;
	}


}
