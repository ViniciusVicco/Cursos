package controllers;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.Email;
import application.RepositoryException;
import application.Util;
import models.Pessoa;
import models.RecuperaSenha;
import repository.PessoaRepository;
import repository.RecuperaSenhaRepository;

@Named
@ViewScoped
public class EsqueceuSenhaController implements Serializable {

	private static final long serialVersionUID = -2292447205917756602L;

	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void enviar() {
		PessoaRepository repo = new PessoaRepository();
		Pessoa usuario;

		usuario = repo.findByEmail(email);
		if (usuario != null) {
			RecuperaSenha recuperaSenhaModel = new RecuperaSenha();
			String codigo = geraCodigoRandomico();
			recuperaSenhaModel.setCodigo(codigo);
			recuperaSenhaModel.setPessoa(usuario);
			recuperaSenhaModel.setAtivo(true);
			LocalDateTime date = pegaDataMaisUmDia();
			recuperaSenhaModel.setDataLimite(date);
			if (salvarRecuperacaoSenha(recuperaSenhaModel)) {

				Email email = new Email(usuario.getEmail(), "Recuperar Senha",
						"Siga as nossas instru��es para recuperar sua senha, utilize o c�digo " + codigo
								+ " V�lido at�: " + date.toString());
				if (!email.enviar()) {
					Util.addErrorMessage("Ops, n�o foi poss�vel enviar cheque se o e-mail est� correto");
				}
			} else {
				Util.addErrorMessage("Ocorreu um erro ao salvar o registro do c�digo");
			}
		}
	}

	public void enviarCodigoPerfil() {
		PessoaRepository repo = new PessoaRepository();
		Pessoa usuario;

		usuario = repo.findByEmail(email);
		if (usuario != null) {
			RecuperaSenha recuperaSenhaModel = new RecuperaSenha();
			String codigo = geraCodigoRandomico();
			recuperaSenhaModel.setCodigo(codigo);
			recuperaSenhaModel.setPessoa(usuario);
			recuperaSenhaModel.setAtivo(true);
			LocalDateTime date = pegaDataMaisUmDia();
			recuperaSenhaModel.setDataLimite(date);
			if (salvarRecuperacaoSenha(recuperaSenhaModel)) {

				Email email = new Email(usuario.getEmail(), "Atualiza��o do Perfil",
						"Siga as nossas instru��es para atualizar seu perfil, utilize o c�digo " + codigo
								+ " V�lido at�: " + date.toString());
				if (!email.enviar()) {
					Util.addErrorMessage("Ops, n�o foi poss�vel enviar cheque se o e-mail est� correto");
				}
			} else {
				Util.addErrorMessage("Ocorreu um erro ao salvar o registro do c�digo");
			}
		}
	}

	public boolean salvarRecuperacaoSenha(RecuperaSenha recuperaSenha) {
		RecuperaSenhaRepository repoRecuperar = new RecuperaSenhaRepository();
		boolean success = false;
		try {
			repoRecuperar.save(recuperaSenha);
			success = true;
		} catch (Exception e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	public String geraCodigoRandomico() {
		Random r = new Random();
		DecimalFormat format = new DecimalFormat("C-000000");
		String codigo = format.format(r.nextInt(100000));
		return codigo;

	}

	public LocalDateTime pegaDataMaisUmDia() {
		LocalDateTime date = LocalDateTime.now();
		date.plusDays(1);
		return date;
	}

}
