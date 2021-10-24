package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.Session;
import Application.Util;
import models.Administrador;
import models.Aluno;
import models.Pessoa;
import models.Professor;

@Named
@ViewScoped
public class TemplateController implements Serializable {

	private static final long serialVersionUID = -7568915789390606615L;

	public void redirecionaListaCursos() {
		Util.redirect("listaCursos.xhtml");
	}

	public boolean checaSessao() {
		if (Session.getInstance().get("user") == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean checaAluno() {
		boolean isAluno = false;
		if (checaSessao())
			isAluno = Session.getInstance().get("user").getClass().equals(Aluno.class);
		return isAluno;

	}

	public boolean checaProfessor() {
		boolean isProfessor = false;

		if (checaSessao())
			isProfessor = Session.getInstance().get("user").getClass().equals(Professor.class);
		return isProfessor;

	}

	public boolean checaAdmin() {
		boolean isAdmin = false;

		if (checaSessao())
			isAdmin = Session.getInstance().get("user").getClass().equals(Administrador.class);
		return isAdmin;

	}

	public void redirecionaLogin() {
		Util.redirect("login.xhtml");
	}

	public void redirecionaPix() {
		Util.redirect("cadastraPix.xhtml");
	}

	public void redirecionaCartao() {
		Util.redirect("cadastraCartao.xhtml");
	}

	public void redirecionaCurso() {
		Util.redirect("cadastraCurso.xhtml");
	}

	public void sair() {
		Session.getInstance().set("user", null);
		Util.redirect("login.xhtml");
	}

}
