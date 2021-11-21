package controllers;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Session;
import application.Util;
import models.Pessoa;
import repository.PessoaRepository;
import repository.Repository;

@Named
@ViewScoped
public class LoginController extends Controller<Pessoa> implements Serializable {

	private static final long serialVersionUID = 8433058250165055965L;

	private Pessoa pessoa = null;
	private String email;
	private String senha;

	@Override
	public Pessoa getEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	public void fazLogin() {
		PessoaRepository repository = new PessoaRepository();
		if (email == null)
			return;
		pessoa = repository.efetuaLogin(email, Util.hash(email + senha));
		System.out.println(pessoa);
		if (pessoa != null) {
			Session.getInstance().set("user", pessoa);
			email = null;
			senha = null;
			Util.redirect("listaCursos.xhtml");
		} else {
			Util.addErrorMessage("Não foi possível efetuar login");
		}

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void redirecionaCadastro() {
		Util.redirect("registroRedireciona.xhtml");
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
