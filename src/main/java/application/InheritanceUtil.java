package application;

import models.Administrador;
import models.Aluno;
import models.Pessoa;
import models.Professor;
import modelsEnum.Genero;

public class InheritanceUtil {

	Pessoa p;

	// Recebe um objeto qualquer
	static public Object checkInheritance(Object objectUnknown, Pessoa pessoa) {
		// Verifica o tipo do objeto com a classe que deseja alterar a propriedade,
		// altera o objeto com a propriedade desejada
		if (objectUnknown.getClass() == Aluno.class) {
			((Aluno) objectUnknown).setNome(pessoa.getNome());
			((Aluno) objectUnknown).setCpf(pessoa.getCpf());
			((Aluno) objectUnknown).setEmail(pessoa.getEmail());
			((Aluno) objectUnknown).setGenero(pessoa.getGenero());

			return objectUnknown;
		}
		if (objectUnknown.getClass() == Professor.class) {
			((Professor) objectUnknown).setNome(pessoa.getCpf());
			((Professor) objectUnknown).setCpf(pessoa.getCpf());
			((Professor) objectUnknown).setEmail(pessoa.getEmail());
			((Professor) objectUnknown).setGenero(pessoa.getGenero());
			return objectUnknown;
		}
		if (objectUnknown.getClass() == Administrador.class) {
			((Administrador) objectUnknown).setNome(pessoa.getCpf());
			((Administrador) objectUnknown).setCpf(pessoa.getCpf());
			((Administrador) objectUnknown).setEmail(pessoa.getEmail());
			((Administrador) objectUnknown).setGenero(pessoa.getGenero());
			return objectUnknown;
		}
		return null;
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) {
		Aluno aluno = new Aluno();
		aluno.setCpf("329329");
		aluno.setEmail("vinicius");
		aluno.setGenero(Genero.FEMININO);
		aluno.setNome("Vinicius");

		Professor aluno2 = new Professor();
		aluno2.setCpf("464168468");
		aluno2.setEmail("Teste");
		aluno2.setGenero(Genero.OUTRO);
		aluno2.setNome("Teste");

		System.out.println("Aluno antes" + aluno2 + " Instancia de: " + aluno2.getClass().toString());

		System.out.println("Aluno agora é:" + checkInheritance(aluno2, aluno) + aluno + " Instancia de: "
				+ checkInheritance(aluno2, aluno).getClass());
	}
}
