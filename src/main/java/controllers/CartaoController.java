package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import Application.JPAUtil;
import Application.RepositoryException;
import Application.Util;
import models.Cartao;
import models.Curso;
import models.FormaPagamento;
import models.Pix;
import repository.FormaPagamentoRepository;

@Named
@ViewScoped
public class CartaoController extends Controller<Cartao> implements Serializable {

	private static final long serialVersionUID = 6027078781434152972L;

	private Cartao cartao;
	private List<FormaPagamento> listaCartao;

	public Cartao getCartao() {
		if (cartao == null)
			cartao = new Cartao();
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	@Override
	public void salvar() {
		cartao.setAtivo(true);
		// TODO Auto-generated method stub
		super.salvar();
	}

	@Override
	public Cartao getEntity() {
		if (entity == null)
			entity = new Cartao();
		return getCartao();
	}

	public List<FormaPagamento> getListaCartao() {
		FormaPagamentoRepository repository = new FormaPagamentoRepository();
		try {
			listaCartao = repository.getPagamentos("C");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaCartao;

	}

	public void setListaCartao(List<FormaPagamento> listaCartao) {
		this.listaCartao = listaCartao;
	}

}
