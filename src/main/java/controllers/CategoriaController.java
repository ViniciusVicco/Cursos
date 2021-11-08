package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import listing.CategoriaListingSQL;
import models.Categoria;
import models.Curso;

@Named
@ViewScoped
public class CategoriaController extends Controller<Categoria> implements Serializable {

	private static final long serialVersionUID = -6573502416484895290L;

	private Categoria categoria;

	public Categoria getCategoria() {
		if (categoria == null)
			categoria = new Categoria();
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public void abrirCategoriaListing() {
		CategoriaListingSQL listing = new CategoriaListingSQL();
	}

	@Override
	public Categoria getEntity() {
		if (entity == null)
			entity = new Categoria();
		// TODO Auto-generated method stub
		return getCategoria();
	}

	@Override
	public void salvar() {
		super.salvar();
		limpar();
	}

	public void limpar() {
		setCategoria(null);
	}

}
