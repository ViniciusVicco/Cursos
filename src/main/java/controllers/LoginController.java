package controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.Session;
import Application.Util;
import models.Pessoa;
import repository.PessoaRepository;
import repository.Repository;

@Named
@ViewScoped
public class LoginController extends Controller<Pessoa> implements Serializable {

	private static final long serialVersionUID = 8433058250165055965L;

	private Pessoa pessoa = null;
	private String login;
	private String senha;

	@Override
	public Pessoa getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	public void fazLogin() {
		PessoaRepository repository = new PessoaRepository();
		if (login == null)
			return;
		pessoa = repository.buscaPessoa(login, Util.hash(login + senha));
		System.out.println(pessoa);
		if (pessoa != null) {
			Session.getInstance().set("user", pessoa);
			login = null;
			senha = null;
			Util.redirect("listaCursos.xhtml");
		} else {
			Util.addErrorMessage("Não foi possível efetuar login");
		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

}
