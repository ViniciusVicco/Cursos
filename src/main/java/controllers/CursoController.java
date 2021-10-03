package controllers;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Application.JPAUtil;
import Application.RepositoryException;
import Application.Util;
import models.Curso;
import repository.PessoaRepository;

@Named
@ViewScoped
public class CursoController extends Controller<Curso> implements Serializable {

	// Diferença entre flush e commit -> ambos enviam os registros ao banco, ambos
	// vem do entitymanager

	private static final long serialVersionUID = -5954417187950125424L;
	private List<Curso> listaCursos;
	private Curso curso = new Curso();

	public Curso getCurso() {
		if (curso == null) {
			curso = new Curso();
		}
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void pesquisar() {
		// Aqui vai buscar resultados pelo primeiro nome
		PessoaRepository repo = new PessoaRepository();

	}

	public void confirm() {
		Util.addInfoMessage("You have accepted");
	}

	public List<Curso> getListaCursos() {

		if (listaCursos == null) {
			listaCursos = new ArrayList<Curso>();

		} else {
			listaCursos = recuperaCursos();
		}
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public void editar(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		Curso c = em.find(Curso.class, id);
		setEntity(c);
		setCurso(c);
	}

	public List<Curso> recuperaCursos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Curso> query = (TypedQuery<Curso>) em.createQuery("SELECT c FROM Curso as c ORDER by c.id");
		List<Curso> results = query.getResultList();
		return results;
	}

	@Override
	public Curso getEntity() {
		if (entity == null)
			entity = new Curso();
		return getCurso();
	}

	@Override
	public void limpar() {
		super.limpar();
		listaCursos = null;
		setCurso(null);
	}

}
