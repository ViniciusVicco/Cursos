package controllers;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Application.RepositoryException;
import models.FormaPagamento;
import models.Pix;
import repository.FormaPagamentoRepository;

@Named
@ViewScoped
public class PixController extends Controller<Pix> implements Serializable {

	private static final long serialVersionUID = 5290376624846202004L;

	private Pix pix;
	private List<FormaPagamento> listaPix;

	@Override
	public Pix getEntity() {
		if (entity == null)
			entity = new Pix();
		return getPix();
	}

	@Override
	public void salvar() {
		pix.setAtivo(true);
		super.salvar();
	}

	public List<FormaPagamento> getListaCartao() {
		FormaPagamentoRepository repository = new FormaPagamentoRepository();
		try {
			listaPix = repository.getPagamentos("P");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listaPix;

	}

	public Pix getPix() {

		if (pix == null)
			pix = new Pix();

		return pix;
	}

	public void setPix(Pix pix) {
		this.pix = pix;
	}

	public List<FormaPagamento> getListaPix() {
		if(listaPix == null) {
			listaPix = getListaCartao();
		}
		return listaPix;
	}

	public void setListaPix(List<FormaPagamento> listaPix) {
		this.listaPix = listaPix;
	}

}
