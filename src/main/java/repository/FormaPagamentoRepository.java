package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.RepositoryException;
import models.Curso;
import models.FormaPagamento;

public class FormaPagamentoRepository extends Repository<FormaPagamento> {

	public List<FormaPagamento> getPagamentos(String tipo) throws RepositoryException {
		List<FormaPagamento> resultList = null;
		try {

			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT f FROM FormaPagamento f WHERE f.tipo LIKE :tipo");
			query.setParameter("tipo", "%" + tipo + "%");
			resultList = query.getResultList();

		} catch (Exception e) {
			new RepositoryException("Problema ao pesquisar usuários");
			e.printStackTrace();
		}

		return resultList;
	}

}
