package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import models.Compra;
import models.Curso;
@Named
@ViewScoped
public class CompraController extends Controller<Compra> implements Serializable {


	private static final long serialVersionUID = 920933608624075565L;
	
	private Compra compra;

	@Override
	public Compra getEntity() {
		if(entity == null) {
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



}
