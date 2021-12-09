package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import models.Venda;

@Named
@ViewScoped
public class VendaController extends Controller<Venda> implements Serializable {

	
	private static final long serialVersionUID = 1848262081420386949L;
	private Venda venda;

	@Override
	public Venda getEntity() {
		if (venda == null)
			venda = new Venda();
		return null;
	}

	public Venda getVenda() {
		if (venda == null)
			venda = new Venda();
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
