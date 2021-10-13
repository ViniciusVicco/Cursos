package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.Session;
import Application.Util;
import models.Administrador;
import models.Aluno;
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
		if (Session.getInstance().get("user") != null) {
			if (Session.getInstance().get("user") instanceof Aluno) {
				isAluno = true;
			}
		}

		return isAluno;
	}

	public boolean checaProfessor() {
		boolean isProfessor = false;
		if (Session.getInstance().get("user") != null) {
			if (Session.getInstance().get("user") instanceof Professor) {
				isProfessor = true;
			}
		}

		return isProfessor;
	}

	public boolean checaAdmin() {
		boolean isAdmin = false;
		if (Session.getInstance().get("user") != null) {
			if (Session.getInstance().get("user") instanceof Administrador) {
				isAdmin = true;
			}
		}

		return isAdmin;
	}

	public void redirecionaLogin() {
		Util.redirect("loginRedireciona.xhtml");
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
		Util.redirect("listaCursos.xhtml");
	}

}
