package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import models.Pessoa;

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
		Pessoa pessoa = (Pessoa) Session.getInstance().get("user");
		if (pessoa != null) {
			EsqueceuSenhaController auxController = new EsqueceuSenhaController();
			auxController.setEmail(pessoa.getEmail());
			auxController.enviarCodigoPerfil();
			Util.redirect("dadosPessoais.xhtml");
		} else {
			Util.redirect("login.xhtml");
		}
	}

}
