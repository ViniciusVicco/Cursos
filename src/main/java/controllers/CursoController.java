package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import application.InheritanceUtil;
import application.JPAUtil;
import application.RepositoryException;
import application.Session;
import application.Util;
import models.Aluno;
import models.Categoria;
import models.Compra;
import models.Curso;
import models.DefaultEntity;
import models.Professor;
import repository.CategoriaRepository;
import repository.CursoRepository;
import repository.PessoaRepository;

@Named
@ViewScoped
public class CursoController extends Controller<Curso> implements Serializable {

	// Diferença entre flush e commit -> ambos enviam os registros ao banco, ambos
	// vem do entitymanager

	private static final long serialVersionUID = -5954417187950125424L;
	private List<Curso> listaCursos;
	private List<Curso> listaCursosFiltrados;
	private Curso curso;

	private InputStream fotoInputStream = null;

	public void upload(FileUploadEvent event) {
		// Checar porque o inputstream não funciona
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				setFotoInputStream(uploadFile.getInputStream());
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}

	public Curso getCurso() {
		if (curso == null) {
			curso = new Curso();
		}
		return curso;
	}

	public List<Categoria> completeCategorias(String query) {
		if (query == null) {
			return new ArrayList<Categoria>();
		}
		CategoriaRepository repo = new CategoriaRepository();
		try {
			return repo.findByNome(query, null);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Categoria>();
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public void pesquisar() {
		// Aqui vai buscar resultados pelo primeiro nome
		PessoaRepository repo = new PessoaRepository();

	}

	public void confirm() {
		Util.addInfoMessage("You have accepted");
	}

	public List<Curso> getListaCursos() {

		if (listaCursos == null) {
			listaCursos = new ArrayList<Curso>();

		} else {
			listaCursos = recuperaCursos();
		}
		return listaCursos;
	}

	public void setListaCursos(List<Curso> listaCursos) {
		this.listaCursos = listaCursos;
	}

	public void editar(Integer id) {
		EntityManager em = JPAUtil.getEntityManager();
		Curso c = em.find(Curso.class, id);
		setEntity(c);
		setCurso(c);

	}

	public void salvaCursoEmCarrinho(Curso paramCurso) {
		Aluno aluno = (Aluno) Session.getInstance().get("user");

		System.out.println(paramCurso);
		CompraController compraController = new CompraController();
		Compra compra = new Compra();

		for (Compra compraInterator : compraController.getListaCompra()) {
			if (compraInterator.getCurso().getId() == paramCurso.getId()) {
				Util.addWarnMessage("Você já adicionou este curso ao carrinho");
				return;
			}
		}

		compra.setAluno(null);
		compra.setCurso(paramCurso);
		compra.setDataPagamento(LocalDate.now());
		compra.setValorTotal(paramCurso.getValor());
		compra.setAluno(aluno);
		compraController.setCompra(compra);

		compraController.setEntity(compra);
		compraController.salvar();
		// Fazer Create-Drop
		// Ajustar relacionamentos
		// Ajustar como fará a venda

	}

	public void carregaCurso(Curso paramCurso) {
		setCurso(paramCurso);
		setEntity(paramCurso);
	}

	public List<Curso> recuperaCursosFiltrado() {
		Professor professor = (Professor) Session.getInstance().get("user");
		if (professor != null) {
			EntityManager em = JPAUtil.getEntityManager();
			TypedQuery<Curso> query = (TypedQuery<Curso>) em.createQuery("SELECT c FROM Curso as c ORDER by c.id");
			List<Curso> results = query.getResultList();
			List<Curso> filteredResults = new ArrayList<Curso>();
			for (Curso curso : results) {
				if (curso.getProfessor().getId() == professor.getId()) {
					filteredResults.add(curso);
				}
			}
			return filteredResults;
		} else {
			return new ArrayList<Curso>();
		}
	}

	public List<Curso> recuperaCursos() {
		EntityManager em = JPAUtil.getEntityManager();
		TypedQuery<Curso> query = (TypedQuery<Curso>) em.createQuery("SELECT c FROM Curso as c ORDER by c.id");
		List<Curso> results = query.getResultList();
		return results;
	}

	@Override
	public void salvar() {
		// TODO Auto-generated method stub
		LocalDate localDate = LocalDate.now();
		Professor professor = (Professor) Session.getInstance().get("user");
		if (professor != null) {
			try {
				curso.setProfessor(professor);
				curso.setDatacriacao(localDate);
				curso = salvarPrincipal();

				System.out.println(getFotoInputStream());
				if (getFotoInputStream() != null) {
					System.out.println(getEntity().getId());
					if (!Util.salvaCapaCurso(fotoInputStream, "png", curso.getId())) {
						Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem");
						return;
					}
				} else {
					System.out.println("Imagem Vazia");
				}
				limpar();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}

			if (getFotoInputStream() != null) {

			}
		} else {
			Util.addWarnMessage("É necessário estar logado como professor para salvar!");
		}
		getListaCursos();
	}

	public void redirecionaParaLogin() {
		Util.redirect("login.xhtml");
	}

	@Override
	public Curso getEntity() {
		if (entity == null)
			entity = new Curso();
		return getCurso();
	}

	public void excluirCurso() {
		CursoRepository repo = new CursoRepository();
		try {
			repo.removeCurso(getCurso().getId());
		} catch (RepositoryException e) {
			Util.addErrorMessage("Ocorreu um erro ao tentar remover o curso");
			e.printStackTrace();
		}
		getListaCursos();
	}

	@Override
	public void limpar() {
		super.limpar();
		listaCursos = null;
		setCurso(null);
	}

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}

	public void redirecionaParaEdicao() {
		Util.redirect("meusCursosProfessor.xhtml");

	}

	public Boolean checaSeProfessorPossuiCurso(Curso paramCurso) {

		if (Session.getInstance().get("user") != null) {
			if (Session.getInstance().get("user").getClass().equals(Professor.class)) {
				Professor professor = (Professor) Session.getInstance().get("user");
				if (paramCurso.getProfessor().getId() == professor.getId()) {
					return true;
				}
			}
		}
		return false;
	}

	public List<Curso> getListaCursosFiltrados() {
		if (listaCursosFiltrados == null) {
			listaCursosFiltrados = recuperaCursosFiltrado();
		}
		return listaCursosFiltrados;
	}

	public void setListaCursosFiltrados(List<Curso> listaCursosFiltrados) {
		this.listaCursosFiltrados = listaCursosFiltrados;
	}

}
