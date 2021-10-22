package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Application.RepositoryException;
import models.Curso;

public class CursoRepository extends Repository<Curso> {

	public List<Curso> findByNome(String nome) throws RepositoryException {
		List<Curso> resultList = null;
		try {

			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT  c FROM Curso c WHERE upper(c.nome) LIKE upper(:nome)");
			query.setParameter("nome", "%" + nome + "%");
			resultList = query.getResultList();

		} catch (Exception e) {
			new RepositoryException("Problema ao pesquisar usuários");
			e.printStackTrace();
		}

		return resultList;
	}
}
