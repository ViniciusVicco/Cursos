package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;
import models.Aluno;
import models.Pessoa;
import modelsEnum.Genero;

@Named
@ViewScoped
public class RegistraAlunoController extends Controller<Aluno> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2594098763517561690L;
	private Aluno aluno = new Aluno();

	public Genero[] getListaTipoPeso() {
		return Genero.values();
	}

	private String confirmaSenha;
	
	public void registra() {
		Aluno alunoRef = new Aluno();
		alunoRef = getAluno();
		alunoRef.setStatus(true);
		alunoRef.setSenha(Util.hash(alunoRef.getLogin() + alunoRef.getSenha()));
		setAluno(alunoRef);
		salvar();
		limpar();
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

	@Override
	public Aluno getEntity() {
		if (entity == null)
			entity = new Aluno();
		return getAluno();
	}

	@Override
	public void limpar() {
		super.limpar();
		setAluno(null);
		confirmaSenha = null;

	}

	public Aluno getAluno() {
		if (aluno == null)
			aluno = new Aluno();
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

}
