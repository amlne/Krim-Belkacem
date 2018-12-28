package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import beans.RequestRechercheDoc;
import dao.DaoFactory;
import dao.prof.ProfDao;
import metier.RechercheDoc;

@WebServlet("/Prof")
public class Prof extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Prof() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Prof prof = (beans.Prof) session.getAttribute("prof");

		if (prof != null && prof.isValide()) {
			// ***************** le code *****************
			System.out.println("************");
			
			String msgValidationAjoutDoc = (String)session.getAttribute("msgValidationAjoutDoc");
			session.setAttribute("msgValidationAjoutDoc",null);
			
			RequestRechercheDoc requestPagination = new RequestRechercheDoc(request);
			
			String msgSuppDoc = (String)session.getAttribute("msgSuppDoc");
			session.setAttribute("msgSuppDoc", null);
			
			RechercheDoc rechercheDoc = new RechercheDoc(request, prof);
			
			request.setAttribute("docs", rechercheDoc.listerDoc());
			request.setAttribute("nombreDoc", rechercheDoc.nombreDoc(prof));
			request.setAttribute("msgValidationAjoutDoc", msgValidationAjoutDoc);
			request.setAttribute("maxPages", rechercheDoc.getMaxPages());
			request.setAttribute("requestPagination", requestPagination.getRequestDoGet());
			request.setAttribute("msgSuppDoc", msgSuppDoc);

			// ********************************************
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/prof.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/connectionProf");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Prof prof = (beans.Prof) session.getAttribute("prof");
		if (prof != null && prof.isValide()) {
			// ********* le code *******************
			System.out.println("************ ");
			System.out.println("la reponse est "+request.getAttribute("msgValidationAjoutDoc"));
			System.out.println("************ ");
			
			RechercheDoc rechercheDoc = new RechercheDoc(request, prof);
			request.setAttribute("docs", rechercheDoc.listerDoc());

			// ***************************************
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/prof.jsp").forward(request, response);
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionProf");
		}
	}
}
