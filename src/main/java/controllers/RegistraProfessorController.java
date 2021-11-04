package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;
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
		if (professor == null)
			professor = new Professor();
		return professor;
	}

	public void registra() {
		String senha = Util.hash(getProfessor().getLogin() + getProfessor().getSenha());
		getProfessor().setSenha(senha);
		System.out.println("Professor:" + getProfessor());
		salvar();
		limpar();
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		super.salvar();
		setProfessor(null);
		confirmaSenha = null;
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
