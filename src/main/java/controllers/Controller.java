package controllers;

import application.RepositoryException;
import application.Util;
import repository.Repository;

public abstract class Controller<T> {

	protected T entity = null;
	private Repository<T> repository = new Repository<T>();

	public Controller() {
		super();
		repository = new Repository<T>();
	}

	public abstract T getEntity();

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public void limpar() {
		setEntity(null);
	}

	public void salvar() {
		try {
			getRepository().save(getEntity());
			limpar();
			Util.addInfoMessage("Registro realizado com sucesso.");
		} catch (RepositoryException e) {

			Util.addErrorMessage("Problema ao salvar, tente novamente ou entre em contato com a TI.");
		}

	}

	public void registrar() {
		try {
			getRepository().register(getEntity());
			limpar();
			Util.addInfoMessage("Registro bem sucedido!");

		} catch (Exception e) {
			Util.addErrorMessage("E-mail já está em uso, tente recuperar sua senha");

			e.printStackTrace();
			// TODO: handle exception
		}
	}

	public void excluir() {
		try {
			getRepository().remove(getEntity());
			System.out.println(getEntity());
			limpar();
			Util.addInfoMessage("Registro removido com sucesso.");
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao remover, tente novamente ou entre em contato com a TI.");
		}

	}

	protected Repository<T> getRepository() {
		return repository;
	}

}