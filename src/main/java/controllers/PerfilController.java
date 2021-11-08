package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Util;

@Named
@ViewScoped
public class PerfilController implements Serializable {
	private static final long serialVersionUID = 6013022457394572677L;

	public void redirecionaFormasDePagamento() {

		Util.redirect("paginaFormasDePagamento.xhtml");
	}

	public void redirecionaHistoricoDeCompras() {
		Util.redirect("");
	}

	public void redirecionaDadosPessoais() {
		Util.redirect("");
	}

}
