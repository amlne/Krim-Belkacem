package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import metier.FormConProf;
import beans.Prof;

@WebServlet("/ConnectionProf")
public class ConnectionProf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ConnectionProf() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/connectionProf.jsp").forward(request,
					response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		FormConProf control = new FormConProf();
		control.controleForm(request);
		if (control.isValide()) {
			request.setAttribute("msg", "mdp valide");
			HttpSession session = request.getSession();
			Prof prof = control.getProf();
			prof.setValide(true);
			session.setAttribute("prof", prof);
			response.sendRedirect("http://localhost:8080/projetLicence/prof");
		} else {
			request.setAttribute("msg", control.getMsgErreur());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/connectionProf.jsp").forward(request,
					response);
		}

	}

}
