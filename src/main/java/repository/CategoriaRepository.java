package repository;

import java.util.List;

import javax.persistence.EntityManager;

import application.RepositoryException;
import models.Categoria;
import javax.persistence.Query;

public class CategoriaRepository extends Repository<Categoria> {

	public List<Categoria> findByNome(String nome, Integer maxResults) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			// JPQL ou SQL
			Query query = em.createQuery("SELECT  c FROM Categoria c WHERE upper(c.nome) LIKE upper(:nome) AND isremovido = false ");
			query.setParameter("nome", "%" + nome + "%");
			if (maxResults != null)
				query.setMaxResults(maxResults);
			return query.getResultList();
		} catch (Exception e) {
			// mandando pro console o exception gerado
			e.printStackTrace();
			// repassando a excecao para quem vai executar o metodo
			throw new RepositoryException("Problema ao pesquisar categorias.");
		}
	}

	public List<Object[]> findByNomeSql(String nome) throws RepositoryException {
		try {
			EntityManager em = getEntityManager();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT ");
			sql.append(" c.id, c.nome, c.descricao ");
			sql.append(" FROM ");
			sql.append("  categoria c ");
			sql.append("WHERE ");
			sql.append(" upper(c.nome) LIKE upper(:nome) ");
			sql.append("ORDER BY c.nome");
			Query query = em.createNativeQuery(sql.toString());
			query.setParameter("nome", "%" + nome + "%");
			System.out.print(query.getResultList().get(0));
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RepositoryException("Problema ao pesquisar categorias");
		}

	}

}
