package repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import application.RepositoryException;
import application.Util;
import models.Aluno;
import models.Curso;
import models.Pessoa;

public class PessoaRepository extends Repository<Pessoa> {

	List<Pessoa> pessoaList = null;
	Pessoa pessoa = null;

//	public Pessoa login(String login, String password) {
//
//		try {
//			System.out.print("Chegou aqui");
//			EntityManager em = getEntityManager();
//			Query query = em.createQuery("SELECT p FROM Pessoa as p where p.login = :login and p.senha = :password");
//			query.setParameter("login", login);
//			query.setParameter("password", password);
//			pessoa = (Pessoa) query.getResultList().get(0);
//			System.out.print("Chegou aqui");
//		} catch (Exception e) {
//			System.out.print("Chegou aqui");
//			new RepositoryException("Login ou senha incorretos");
//			e.printStackTrace();
//		}
//		return pessoa;
//
//	}

	public Pessoa findByEmail(String email)  {
		try {
			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT p FROM Pessoa p where p.email = :email");
			query.setParameter("email", email);
			pessoa = (Pessoa) query.getSingleResult();
			Util.addInfoMessage("Verifique sua caixa de e-mail");
		} catch (Exception e) {
			e.printStackTrace();
			Util.addErrorMessage("Não foi possível encontrar este e-mail");
			return null;
		}

		return pessoa;

	}

	public Pessoa efetuaLogin(String email, String senha) {

		try {
			EntityManager em = getEntityManager();
			Query query = em.createQuery("SELECT p FROM Pessoa p where p.email = :email and p.senha = :senha");
			query.setParameter("email", email);
			query.setParameter("senha", senha);

			pessoa = (Pessoa) query.getResultList().get(0);

		} catch (Exception e) {
			e.printStackTrace();

		}
		System.out.println(pessoa);
		return pessoa;
	}

}
