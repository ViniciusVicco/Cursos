package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import application.Util;
import models.Aluno;
import models.Compra;
import models.Curso;
import repository.CompraRepository;

@Named
@ViewScoped
public class CompraController extends Controller<Compra> implements Serializable {

	private static final long serialVersionUID = 920933608624075565L;

	private List<Compra> listaCompra;
	private Compra compra;
	private String total;

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
		for(Compra compra : listaCompra) {
			
		}
		Util.addInfoMessage("Compra finalizada");
	}

	public void setTotal(String total) {
		this.total = total;
	}

}
