package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/exemplo")
public class ImgCursoServlet extends HttpServlet {
	private static final long serialVersionUID = 6454227011010889246L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter writer = response.getWriter();
		writer.print("<html>");
		writer.print("<head>");
		writer.print("</head>");
		writer.print("<body>");
		writer.print("<h1> Teste Servlet </h1>");
		writer.print("</body>");
		writer.print("</html>");
		// TODO Auto-generated method stub
		super.doGet(request, response);
	}

}
