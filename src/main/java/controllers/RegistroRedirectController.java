package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.Util;

@Named
@ViewScoped
public class RegistroRedirectController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4499151531639498606L;

	public void redirecionarEstudante() {
		Util.redirect("registraAluno.xhtml");
	}

	public void redirecionarProfessor() {
		Util.redirect("registraProfessor.xhtml");

	}

}
