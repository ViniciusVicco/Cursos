package controllers;
import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
	

}
