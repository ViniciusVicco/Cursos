package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import application.Session;
import models.Administrador;
import models.Aluno;
import models.Professor;

@WebFilter(filterName = "SecurityFilter", urlPatterns = { "/pages/*" })
public class SecurityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		// deixando o fluxo seguir
		// Para desabilitar o filtro descomente as duas proximas linhas e comente o
		// restante
//		chain.doFilter(request, response);
//		return;

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		String endereco = servletRequest.getRequestURI();
		System.out.println(endereco);

//		// retorna uma sessao corrente (false - nao cria uma sessao)
		HttpSession session = servletRequest.getSession(false);

		session.getAttribute("user");
		if (checaSessao()) {
			Professor professor = null;
			Aluno aluno = null;
			Administrador admin = null;
			if (checaAluno()) {
				if (aluno.permissoes().contains(endereco)) {
					chain.doFilter(request, response);
					return;
				}
			}
		} else {
			// Redireciona pro login
			((HttpServletResponse) response).sendRedirect("/cursos/login.xhtml");
		}

	}

//				}

	public boolean checaSessao() {
		if (Session.getInstance().get("user") == null) {
			return false;
		} else {
			return true;
		}

	}

	public boolean checaAluno() {
		boolean isAluno = false;
		if (checaSessao())
			isAluno = Session.getInstance().get("user").getClass().equals(Aluno.class);
		return isAluno;

	}

	public Professor checaProfessor() {
		boolean isProfessor = false;

		if (checaSessao())
			isProfessor = Session.getInstance().get("user").getClass().equals(Professor.class);
		if (isProfessor)
			return (Professor) Session.getInstance().get("user");
		else
			return null;

	}

	public Administrador checaAdmin() {
		boolean isAdmin = false;

		if (checaSessao())
			isAdmin = Session.getInstance().get("user").getClass().equals(Professor.class);
		if (isAdmin)
			return (Administrador) Session.getInstance().get("user");
		else
			return null;

	}
//		
//		Usuario usuario = null;
//		if (session != null)
//			usuario = (Usuario) session.getAttribute("usuarioLogado");
//		
//		if (usuario == null) {
//			((HttpServletResponse) response).sendRedirect("/salaaula/login.xhtml");
//		} else {
//			
//			if (usuario.getPerfil().getPaginasComPermissao().contains(endereco)) {
//				chain.doFilter(request, response);
//				return;
//			} else 
//				((HttpServletResponse) response).sendRedirect("/salaaula/sempermissao.xhtml");
//		}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("Security inicializado ...");

		Filter.super.init(filterConfig);
	}

}