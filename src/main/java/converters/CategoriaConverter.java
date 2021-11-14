package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import models.Categoria;
import repository.CategoriaRepository;

@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter<Categoria> {

	@Override
	public Categoria getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;

		CategoriaRepository repo = new CategoriaRepository();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Categoria categoria) {
		if (categoria == null || categoria.getId() == null)
			return null;

		return categoria.getId().toString();
	}

}
