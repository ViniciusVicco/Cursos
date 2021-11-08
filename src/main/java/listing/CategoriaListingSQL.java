package listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import application.RepositoryException;
import models.Categoria;
import repository.CategoriaRepository;

@Named
@ViewScoped
public class CategoriaListingSQL extends ListingSQL<Categoria> {
	
	private String filtro;

	public CategoriaListingSQL() {
		super("categoriaListing", new CategoriaRepository());
	}

	private static final long serialVersionUID = 5848225807967836675L;

	@Override
	public void pesquisar() {
		CategoriaRepository repo = new CategoriaRepository();
		try {
			setList(repo.findByNomeSql(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<Object[]>());
		}

	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

}
