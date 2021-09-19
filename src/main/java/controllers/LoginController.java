package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Application.JPAUtil;
import Application.Util;
import models.Aluno;

import repository.PessoaRepository;

@Named
@ViewScoped
public class LoginController extends Controller<Aluno> implements Serializable {

	private static final long serialVersionUID = 7929357021320392268L;

	private Aluno aluno = new Aluno();

	private String login;
	private String password;

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public String efetuaLogin() {
		Aluno al = null;
		PessoaRepository repo = new PessoaRepository();
		if (login != null && password != null) {
			String hashPassword = Util.hash(getLogin() + getPassword());

			al = repo.login(login, hashPassword);
			setAluno(al);
		}
		if (al != null) {
			Util.addInfoMessage("Login efetuado com sucesso");
			return "index.xhtml";
		} else {
			Util.addInfoMessage("Não foi possivel efetuar login");
			return null;
		}

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Aluno getEntity() {
		if (entity == null) {
			entity = new Aluno();
		}
		return getAluno();
	}

	public String redirecionaRegistro() {
		return "index.xhtml";
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub

	}

}
