package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.GestionPreinscription;

@WebServlet("/OuvrirPreinscription")
public class OuvrirPreinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OuvrirPreinscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Admin admin = (beans.Admin) session.getAttribute("admin");

		if (admin != null && admin.isValide()) {
			// ***************** le code *****************
			String msgOuvrirPreinscription = "";
			String ouvrir = request.getParameter("ouvrir");
			if (ouvrir != null && !ouvrir.isEmpty()) {
				GestionPreinscription gestion = new GestionPreinscription();
				if (ouvrir.equals("0")) {
					if (gestion.fermerPreinscription()) {
						msgOuvrirPreinscription = "preinscription ferme";
					} else {
						msgOuvrirPreinscription = "une erreur est survenue a la fermeture des preinscription";
					}
				} else {
					if (gestion.ouvrirPreinscription()) {
						msgOuvrirPreinscription = "preinscription ouverte";
					} else {
						msgOuvrirPreinscription = "une erreur est survenue a l'ouverteur des preinscription";
					}
				}
				session.setAttribute("msgOuvrirPreinscription", msgOuvrirPreinscription);
				response.sendRedirect("http://localhost:8080/projetLicence/admin");
			} else {
				response.sendRedirect("http://localhost:8080/projetLicence/admin");
			}

		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionAdmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
