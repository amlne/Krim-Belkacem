package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.FormAjoutPlanning;

@WebServlet("/AjoutPlanning")
public class AjoutPlanning extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjoutPlanning() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("http://localhost:8080/projetLicence/admin");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Admin admin = (beans.Admin) session.getAttribute("admin");
		if (admin != null && admin.isValide()) {
			// ***************** le code *****************
			FormAjoutPlanning formAjout = new FormAjoutPlanning(admin);
			formAjout.controleform(request);
			if (formAjout.isValide()) {
				session.setAttribute("AjoutPlanning", "Ajout avec succès");
				this.getServletContext().getRequestDispatcher("/admin");
				response.sendRedirect("http://localhost:8080/projetLicence/admin");
			} else {
				String erreur = formAjout.getErreur();
				session.setAttribute("AjoutPlanning",erreur);
				response.sendRedirect("http://localhost:8080/projetLicence/admin");
			}

		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionAdmin");
		}
	}

}
