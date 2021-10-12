package controllers;

import java.io.Serializable;

import models.Pix;

public class PixController extends Controller<Pix> implements Serializable {


	private static final long serialVersionUID = 5290376624846202004L;
	
	
	private Pix pix;

	@Override
	public Pix getEntity() {
		if(entity == null)
			entity = new Pix();
		return null;
	}
	
	@Override
	public void salvar() {
		getEntity().setAtivo(true);
		pix.setAtivo(true);
		getPix().setAtivo(true);
		super.salvar();
	}
	
	public Pix getPix() {
	
		if(pix == null)
			pix = new Pix();
		return pix;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
	}

}
