package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import models.Professor;

@Named
@ViewScoped
public class RegistraProfessorController extends Controller<Professor> implements Serializable {

	private static final long serialVersionUID = -7304007504790667270L;

	private Professor professor = null;

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}	

	@Override
	public Professor getEntity() {
		if (entity == null) {
			entity = new Professor();
		}
		return getProfessor();
	}
	
}
