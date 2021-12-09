package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import application.Session;
import models.Aluno;
import models.FormaPagamento;
import models.Pix;
import modelsEnum.Genero;
import modelsEnum.TipoPix;
import repository.FormaPagamentoRepository;

@Named
@ViewScoped
public class PixController extends Controller<Pix> implements Serializable {

	private static final long serialVersionUID = 5290376624846202004L;

	private Pix pix;
	private List<FormaPagamento> listaPix;

	public TipoPix[] getListaTipoPix() {
		return TipoPix.values();
	}

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
		Aluno aluno = (Aluno) Session.getInstance().get("user");
		if(aluno != null) {
			
		
		FormaPagamentoRepository repository = new FormaPagamentoRepository();
		try {
			listaPix = repository.getPagamentos("P");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else { listaPix = new ArrayList<FormaPagamento>();}
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

	public void remover(Pix paramPix) {
		setPix(paramPix);
		setEntity(paramPix);
		excluir();
	}

	public List<FormaPagamento> getListaPix() {
		if (listaPix == null) {
			listaPix = new ArrayList<FormaPagamento>();
			listaPix = getListaCartao();
		}
		return listaPix;
	}

	public void setListaPix(List<FormaPagamento> listaPix) {
		this.listaPix = listaPix;
	}

}
