package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Application.JPAUtil;
import Application.Session;
import Application.Util;
import models.Aluno;

import repository.PessoaRepository;

@Named
@ViewScoped
public class LoginAlunoController extends Controller<Aluno> implements Serializable {

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

	public void efetuaLogin() {
		Aluno al = null;
		PessoaRepository repo = new PessoaRepository();
		if (login != null && password != null) {
			String hashPassword = Util.hash(getLogin() + getPassword());

			al = repo.login(login, hashPassword);
			setAluno(al);
		}
		if (al != null) {
			if (al.isStatus()) {
				Util.addInfoMessage("Login efetuado com sucesso");
				Session.getInstance().set("user", al);
				System.out.println(Session.getInstance().get("user"));
				Util.redirect("listaCursos.xhtml");
			} else {
				Util.addErrorMessage("Usuário inativado ou bloqueado");

			}

		} else {
			Util.addErrorMessage("Não foi possivel efetuar login, verifique suas credências");

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

	public void redirecionaRegistro() {
		Util.redirect("registroRedireciona.xhtml");
	}

	@Override
	public void limpar() {
		// TODO Auto-generated method stub

	}

}
