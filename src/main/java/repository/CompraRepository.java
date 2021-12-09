package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.RepositoryException;
import models.Compra;
import models.Curso;

public class CompraRepository extends Repository<Compra> {
//	public List<Curso> findByNome(String nome) throws RepositoryException {
//		List<Curso> resultList = null;
//		try {
//
//			EntityManager em = getEntityManager();
//			Query query = em.createQuery("SELECT  c FROM Curso c WHERE upper(c.nome) LIKE upper(:nome)");
//			query.setParameter("nome", "%" + nome + "%");
//			resultList = query.getResultList();
//
//		} catch (Exception e) {
//			new RepositoryException("Problema ao pesquisar cursos");
//			e.printStackTrace();
//		}
//
//		return resultList;
//	}

	
	public List<Compra> getAllComprasByUserId(int id) throws RepositoryException {
		List<Compra> resultList = null;
		try {
			EntityManager em = getEntityManager();
			Query query  = em.createQuery("SELECT c FROM Compra c WHERE c.aluno.id = :id");
			query.setParameter("id", id);
			resultList = query.getResultList();
		} catch (Exception e) {
			new RepositoryException("Ocorreu um erro ao buscar seu carrinho");
			e.printStackTrace();
		}
		return resultList;
	}
}


