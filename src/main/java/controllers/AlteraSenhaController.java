package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Util;
import models.Pessoa;
import models.RecuperaSenha;
import repository.AlteraSenhaRepository;
import repository.PessoaRepository;
import repository.RecuperaSenhaRepository;

@Named
@ViewScoped
public class AlteraSenhaController implements Serializable {

	private static final long serialVersionUID = 5113783876566650415L;

	private String codigo;
	private String senha;
	private String confirmaSenha;

	public RecuperaSenha buscaAlteraSenhaEntity() {
		AlteraSenhaRepository repo = new AlteraSenhaRepository();
		RecuperaSenha entity = repo.findByCodigo(codigo);
		return entity;
	}

	public Pessoa recuperaPessoa(int id) {
		PessoaRepository repo = new PessoaRepository();
		Pessoa p = repo.findById(id);
		return p;

	}

	public void trocaSenha() {
		if (confirmaSenha.equals(senha)) {
			RecuperaSenha senhaModel = buscaAlteraSenhaEntity();
			if (senhaModel != null) {
				PessoaRepository pessoaRepo = new PessoaRepository();

				try {
					Pessoa pessoa = new Pessoa();

					senhaModel.getPessoa().setSenha(Util.hash(senhaModel.getPessoa().getEmail() + senha));
					pessoa = senhaModel.getPessoa();
					pessoaRepo.save(pessoa);
					Util.addInfoMessage("Senha atualizada com sucesso");
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Util.addErrorMessage("Ocorreu algum erro");
				}
			} else {
				Util.addErrorMessage("Não foi possível processar o código");
			}
		} else {
			Util.addErrorMessage("Senhas são diferentes");
		}

	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmaSenha() {
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
