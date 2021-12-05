package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.InheritanceUtil;
import application.RepositoryException;
import application.Session;
import application.Util;
import models.Pessoa;
import models.RecuperaSenha;
import repository.AlteraSenhaRepository;
import repository.PessoaRepository;
import repository.RecuperaSenhaRepository;

@Named
@ViewScoped
public class DadosPessoaisController extends Controller<Pessoa> implements Serializable {

	private static final long serialVersionUID = -6030205310762297385L;

	private Pessoa pessoa;

	private String confirmaSenha;

	private String codigo;

	@Override
	public Pessoa getEntity() {
		if (entity == null)

			entity = new Pessoa();
		return getPessoa();
	}

	public Pessoa getPessoa() {
		if (pessoa == null)
			buscarPessoa();
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public void buscarPessoa() {
		Pessoa p = (Pessoa) Session.getInstance().get("user");

		if (p != null) {
			PessoaRepository pessoaRepo = new PessoaRepository();

			setPessoa((Pessoa) InheritanceUtil.checkInheritance(pessoaRepo.findById(p.getId()), p));
			getPessoa().setSenha(null);
			System.out.println(getPessoa());

		} else {
			Util.redirect("login.xhtml");
			Util.addFatalMessage("Erro ao buscar usuário logado");
			setPessoa(new Pessoa());
		}

	}

	public void reenviarCodigo() {
		if (pessoa != null) {
			EsqueceuSenhaController auxController = new EsqueceuSenhaController();
			auxController.setEmail(pessoa.getEmail());
			auxController.enviarCodigoPerfil();
			Util.redirect("dadosPessoais.xhtml");
		} else {
			Util.redirect("login.xhtml");
		}
	}

	public void atualizarDados() {
		if (getPessoa().getSenha().equals(confirmaSenha)) {
			AlteraSenhaRepository repo = new AlteraSenhaRepository();
			RecuperaSenha recuperaSenha = repo.findByCodigo(codigo);
			if (recuperaSenha != null) {
				int pessoaId = recuperaSenha.getPessoa().getId();
				pessoa.setId(pessoaId);
				pessoa.setSenha(Util.hash(pessoa.getEmail() + pessoa.getSenha()));
				setEntity(pessoa);
				setPessoa(pessoa);
				salvar();
				getPessoa().setSenha("");
				RecuperaSenhaRepository repoRecuperaSenha = new RecuperaSenhaRepository();

				try {
					recuperaSenha.setAtivo(false);
					repoRecuperaSenha.save(recuperaSenha);
				} catch (RepositoryException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} else {
			Util.addErrorMessage("Senhas não conferem");
		}
	}

	public String getCodigo() {

		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getConfirmaSenha() {
		if (confirmaSenha == null)
			confirmaSenha = "";
		return confirmaSenha;
	}

	public void setConfirmaSenha(String confirmaSenha) {
		this.confirmaSenha = confirmaSenha;
	}

}
