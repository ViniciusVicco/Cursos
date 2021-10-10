package controllers;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import models.Cartao;
import models.Curso;
import models.Pix;

@Named
@ViewScoped
public class CartaoController extends Controller<Cartao> implements Serializable {

	private static final long serialVersionUID = 6027078781434152972L;
	

	private Cartao cartao;
	
	
	public Cartao getCartao() {
		if(cartao == null)
			cartao = new Cartao();
		return cartao;
	}
	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	@Override
	public Cartao getEntity() {
		if (entity == null)
			entity = new Cartao();
		return getCartao();
	}
	

}
