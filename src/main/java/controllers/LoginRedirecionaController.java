package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;
@Named
@ViewScoped
public class LoginRedirecionaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6313448850815045133L;

	public void redirecionarEstudante() {
		Util.redirect("login.xhtml");
	}
}
