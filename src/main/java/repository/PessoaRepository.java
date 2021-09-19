package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import Application.RepositoryException;
import models.Aluno;
import models.Curso;

public class PessoaRepository extends Repository<Curso> {

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

	public Aluno login(String login, String password) {
		Aluno aluno = null;
		try {
			EntityManager em = getEntityManager();
			Query query = em
					.createQuery("SELECT al FROM Aluno as al where al.login = :login and al.senha = :password");
			query.setParameter("login", login);
			query.setParameter("password", password);
			aluno = (Aluno) query.getResultList().get(0);
		} catch (Exception e) {
			new RepositoryException("Login ou senha incorretos");
			e.printStackTrace();
		}
		return aluno;

	}

}
