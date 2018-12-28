package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.GestionPreinscription;

@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Index() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		metier.RechercheRubrique rech = new metier.RechercheRubrique(request);
		request.setAttribute("rubriques", rech.listerRubriqueAccueil());
		
		GestionPreinscription pre = new GestionPreinscription();
		
		System.out.println("l'etat de preinscription est "+pre.ouvert());
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/accueil.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}