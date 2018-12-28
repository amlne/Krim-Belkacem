package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.RechercheDoc;
import metier.RechercheProfClasse;

@WebServlet("/Eleve")
public class Eleve extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Eleve() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Eleve eleve = (beans.Eleve) session.getAttribute("eleve");

		if (eleve != null && eleve.isValide()) {

			RechercheDoc rechercheDoc = new RechercheDoc(request, eleve);
			request.setAttribute("docs", rechercheDoc.listerDoc());
			
			request.setAttribute("eleve", eleve);
			request.setAttribute("maxPages", rechercheDoc.getMaxPages());
			
			RechercheProfClasse rechercheProfClasse = new RechercheProfClasse(eleve);
			request.setAttribute("profs", rechercheProfClasse.listerProf());
			
			String downloadDeliberation = (String)session.getAttribute("DownloadDeliberation");
			session.setAttribute("DownloadDeliberation", null);
			
			String downloadPlanning = (String)session.getAttribute("DownloadPlanning");
			session.setAttribute("DownloadPlanning", null);
			
			String downloadFicheDeVoeux = (String)session.getAttribute("DownloadFicheDeVoeux");
			session.setAttribute("DownloadFicheDeVoeux", null);
			
			request.setAttribute("DownloadDeliberation",downloadDeliberation);
			request.setAttribute("DownloadPlanning",downloadPlanning);
			request.setAttribute("DownloadFicheDeVoeux",downloadFicheDeVoeux);

			
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/eleve.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/accueil");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Eleve eleve = (beans.Eleve) session.getAttribute("eleve");

		if (eleve != null && eleve.isValide()) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/eleve.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/accueil");
		}
	}

}
