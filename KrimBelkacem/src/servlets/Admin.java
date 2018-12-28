package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.GestionPreinscription;
import metier.RechercheDeliberation;
import metier.RecherchePlanning;
import metier.RechercheRubrique;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Admin() {
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

			String recherche = request.getParameter("recherche");
			if (recherche == null) {
				RecherchePlanning recherchePlanning = new RecherchePlanning(request);
				request.setAttribute("plannings", recherchePlanning.listerPlanning());

				RechercheRubrique rechercheRubrique = new RechercheRubrique(request);
				request.setAttribute("rubriques", rechercheRubrique.listerRubrique());

				RechercheDeliberation rechercheDeliberation = new RechercheDeliberation(request);
				request.setAttribute("deliberations", rechercheDeliberation.listerDeliberation());

			} else if (recherche.equals("rubrique")) {
				RechercheRubrique rechercheRubrique = new RechercheRubrique(request);
				request.setAttribute("rubriques", rechercheRubrique.listerRubrique());
			} else if (recherche.equals("planning")) {
				RecherchePlanning recherchePlanning = new RecherchePlanning(request);
				request.setAttribute("plannings", recherchePlanning.listerPlanning());
			} else if (recherche.equals("deliberation")) {
				RechercheDeliberation rechercheDeliberation = new RechercheDeliberation(request);
				request.setAttribute("deliberations", rechercheDeliberation.listerDeliberation());
			}else{
				RecherchePlanning recherchePlanning = new RecherchePlanning(request);
				request.setAttribute("plannings", recherchePlanning.listerPlanning());

				RechercheRubrique rechercheRubrique = new RechercheRubrique(request);
				request.setAttribute("rubriques", rechercheRubrique.listerRubrique());

				RechercheDeliberation rechercheDeliberation = new RechercheDeliberation(request);
				request.setAttribute("deliberations", rechercheDeliberation.listerDeliberation());
			}
			String del = (String) request.getAttribute("DownloadDeliberation");

			// ********************************************

			String ajoutDeliberation = (String) session.getAttribute("AjoutDeliberation");
			session.setAttribute("AjoutDeliberation", null);

			String ajoutPlanning = (String) session.getAttribute("AjoutPlanning");
			session.setAttribute("AjoutPlanning", null);

			String ajoutRubrique = (String) session.getAttribute("AjoutRubrique");
			session.setAttribute("AjoutRubrique", null);

			String ouvrirPreinscription = (String) session.getAttribute("msgOuvrirPreinscription");
			session.setAttribute("msgOuvrirPreinscription", null);

			String msgSuppPlanning = (String) session.getAttribute("msgSuppPlanning");
			session.setAttribute("msgSuppPlanning", null);

			String msgSuppRubrique = (String) session.getAttribute("msgSuppRubrique");
			session.setAttribute("msgSuppRubrique", null);

			String msgSuppDeliberation = (String) session.getAttribute("msgSuppDeliberation");
			session.setAttribute("msgSuppDeliberation", null);

			request.setAttribute("AjoutDeliberation", ajoutDeliberation);
			request.setAttribute("AjoutPlanning", ajoutPlanning);
			request.setAttribute("AjoutRubrique", ajoutRubrique);
			request.setAttribute("msgOuvrirPreinscription", ouvrirPreinscription);
			request.setAttribute("msgSuppPlanning", msgSuppPlanning);
			request.setAttribute("msgSuppRubrique", msgSuppRubrique);
			request.setAttribute("msgSuppDeliberation", msgSuppDeliberation);

			GestionPreinscription gestion = new GestionPreinscription();
			request.setAttribute("preinscriptionOuvert", gestion.ouvert());

			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/admin.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionAdmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Admin admin = (beans.Admin) session.getAttribute("admin");
		if (admin != null && admin.isValide()) {
			// ***************** le code *****************
			// ********************************************
			System.out.println("on est dans dopostde admin ");
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/admin.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/connectionAdmin");
		}
	}

}
