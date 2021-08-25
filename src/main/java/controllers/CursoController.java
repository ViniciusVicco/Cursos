package controllers;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import models.Curso;
@Named
@ViewScoped
public class CursoController implements Serializable {
	
	private static final long serialVersionUID = -5954417187950125424L;
	
	private Curso curso = new Curso();

	public Curso getCurso() {
		if(curso == null) {
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
	
	public void insertCurso() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Curso");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(getCurso());
		em.getTransaction().commit();
		System.out.print("Finalizou");
	}
	

}
