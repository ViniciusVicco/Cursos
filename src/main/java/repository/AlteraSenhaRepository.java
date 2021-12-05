package repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.Util;
import controllers.CursoController;
import models.Pessoa;
import models.RecuperaSenha;

public class AlteraSenhaRepository extends Repository<CursoController> {

	public RecuperaSenha findByCodigo(String codigo) {
		// Buscar o recupera senha
		// Buscar a pessoa que tem o id associado
		// Atualizar a senha que foi digitada na view
		RecuperaSenha recuperaSenha;
		try {
			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT r FROM RecuperaSenha r where r.codigo = :codigo and ativo = true");
			query.setParameter("codigo", codigo);
			recuperaSenha = (RecuperaSenha) query.getSingleResult();
			System.out.println("Recuperador encontrado" + recuperaSenha.toString());
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar o código, pode estar inativo ou inexistente");
			return null;
		}

		return recuperaSenha;

	}
}
