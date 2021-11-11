package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

@Entity
public class Categoria {
	@Id
	@GeneratedValue
	private Integer id;
	@Column
	@NotNull
	private String nome;
	@Column
	private boolean isRemovido;
	@Column
	@NotNull
	private String descricao;
	@OneToMany(mappedBy = "categoria")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	private List<Curso> cursos = new ArrayList<Curso>();

	public Integer getId() {
		return id;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public boolean isRemovido() {
		return isRemovido;
	}

	public void setRemovido(boolean isRemovido) {
		this.isRemovido = isRemovido;
	}
}
