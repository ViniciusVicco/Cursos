package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.RepositoryException;
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
			new RepositoryException("Problema ao pesquisar cursos");
			e.printStackTrace();
		}

		return resultList;
	}

	public boolean removeCurso(int id) throws RepositoryException {

		try {
			EntityManager em = getEntityManager();

			Query query = em.createQuery("DELETE FROM Curso c WHERE c.id = :id");
			query.setParameter("id",  id );

			return true;
		} catch (Exception e) {
			new RepositoryException("Problema ao remover curso");
			e.printStackTrace();
			return false;
		}

	}
}
