package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import application.Util;
import models.Aluno;
import models.Cartao;
import models.Compra;
import models.Curso;
import models.Pix;
import models.Venda;
import repository.CompraRepository;

@Named
@ViewScoped
public class CompraController extends Controller<Compra> implements Serializable {

	private static final long serialVersionUID = 920933608624075565L;

	private List<Compra> listaCompra;
	private Compra compra;
	private String total;
	private Pix pix;
	private Cartao cartao;

	public Pix getPix() {
		if (pix == null)
			pix = new Pix();
		return pix;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
		if (pix != null) {
			cartao = null;
		}
	}

	public Cartao getCartao() {
		if (cartao == null)
			cartao = new Cartao();
		return cartao;
	}

	public void setCartao(Cartao cartao) {

		this.cartao = cartao;
		if (cartao != null) {
			pix = null;
		}
	}

	@Override
	public Compra getEntity() {
		if (entity == null) {
			entity = new Compra();
		}
		return entity;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public List<Compra> obtemListaCompra() {
		Aluno aluno = (Aluno) Session.getInstance().get("user");
		if (aluno != null) {
			CompraRepository repo = new CompraRepository();
			try {
				listaCompra = repo.getAllComprasByUserId(aluno.getId());
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return listaCompra;

	}

	public List<Compra> getListaCompra() {
		listaCompra = obtemListaCompra();
		if (listaCompra == null) {
			listaCompra = new ArrayList<Compra>();
			listaCompra = obtemListaCompra();

		}
		return listaCompra;
	}

	public void setListaCompra(List<Compra> listaCompra) {
		this.listaCompra = listaCompra;
	}

	public boolean carrinhoComItens() {
		if (listaCompra == null) {
			return false;

		} else if (listaCompra.size() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	public String valorTotal() {
		Integer soma = 0;

		if (listaCompra != null) {
			for (Compra compra : listaCompra) {
				soma += compra.getCurso().getValor();
			}

		}

		return "Total R$: " + soma.toString();

	}

	public void removerItem(Compra obj) {
		setCompra(obj);
		setEntity(obj);
		excluir();
		listaCompra = getListaCompra();
		getTotal();

	}

	public String getTotal() {

		total = valorTotal();

		return total;
	}

	public void finalizarCompra() {
		VendaController vendaController = new VendaController();
		Venda venda = new Venda();

		if (pix == null) {
			venda.setFormaPagamento(cartao);
		}
		if (cartao == null) {
			venda.setFormaPagamento(pix);
		}
		vendaController.setEntity(venda);
		vendaController.setVenda(venda);

		vendaController.salvar();

		for (Compra compra : listaCompra) {
			// Aqui dentro, você vai colocar a lista de compra, cada uma será salva com id
			// da venda
		}
		Util.addInfoMessage("Compra finalizada");
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public Boolean existePagamentoCadastrado() {
		PixController pixController = new PixController();
		CartaoController cartaoController = new CartaoController();
		if (pixController.getListaPix().size() >= 1 || cartaoController.getListaCartao().size() >= 1) {
			return true;
		}
		return false;
	}

	public Boolean naoExistePagamentoCadastrado() {
		PixController pixController = new PixController();
		CartaoController cartaoController = new CartaoController();
		if (!(pixController.getListaPix().size() >= 1 || cartaoController.getListaCartao().size() >= 1)) {
			return false;
		}
		return true;
	}

	public Boolean usuarioLogado() {
		if (Session.getInstance().get("user") != null) {
			return true;
		} else {
			return false;
		}
	}

	public void redirecionaPagamento() {
		Util.redirect("paginaFormasDePagamento.xhtml");
	}

	public void salvaPixPagamento(Pix paramPix) {
		setPix(paramPix);

	}

	public void salvaCartaoPagamento(Cartao paramCartao) {
		setCartao(paramCartao);
	}

}
