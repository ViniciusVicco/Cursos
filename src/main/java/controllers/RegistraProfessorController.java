package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.Util;
import models.Aluno;
import models.Professor;
import modelsEnum.Genero;

@Named
@ViewScoped
public class RegistraProfessorController extends Controller<Professor> implements Serializable {

	private static final long serialVersionUID = -7304007504790667270L;

	private Professor professor = new Professor();
	private String confirmaSenha;
	
	public Genero[] getlistaGenero() {
		return Genero.values();
	}

	public Professor getProfessor() {
		if(professor == null)
			professor = new Professor();
		return professor;
	}
	
	public void registra() {
		Professor prof = new Professor();
		prof = getProfessor();
		prof.setStatus(true);
		prof.setSenha(Util.hash(prof.getLogin() + prof.getSenha()));
		setProfessor(prof);
		salvar();
		limpar();
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

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}
	
}