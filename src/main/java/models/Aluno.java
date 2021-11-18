package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Pessoa {
	
	public List<String> permissoes() {
		ArrayList<String> paginasComPermissao = new ArrayList<String>();
		paginasComPermissao.add("/Cursos/listaCursos.xhtml");
		return null;
	}

}
