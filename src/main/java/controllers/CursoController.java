package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import Application.JPAUtil;
import models.Curso;

@Named
@ViewScoped
public class CursoController implements Serializable {

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

	public void printaCurso() {

		System.out.println(getCurso().toString());

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

	public void insereCurso() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Curso");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(getCurso());
		em.getTransaction().commit();
		System.out.print("Finalizou");
		getListaCursos();
	}

	public void removeCurso() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Curso");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.remove(getCurso());
		em.getTransaction().commit();
		System.out.print("Finalizou");
		getListaCursos();
	}

	public void limparCurso() {
		setCurso(null);
	}

	public void editar(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		setCurso(em.find(Curso.class, id));
	}

	public void atualizar() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Curso");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.merge(getCurso());
		em.getTransaction().commit();
		System.out.print("Finalizou");
	}

	public List<Curso> recuperaCursos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Curso");
		EntityManager em = emf.createEntityManager();
		TypedQuery<Curso> query = (TypedQuery<Curso>) em.createQuery("SELECT c FROM Curso as c ORDER by c.id");
		List<Curso> results = query.getResultList();
		return results;
	}

}
