package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.FormPreinscription;

@WebServlet("/Preinscription")
public class Preinscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Preinscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/preinscription.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FormPreinscription form = new FormPreinscription();
		if(form.controleform(request)){
			request.setAttribute("AjoutPreinscription", "ajout de preinscription");
		}else{
			request.setAttribute("AjoutPreinscription", "erreur de preinscription");
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/preinscription.jsp").forward(request, response);
	}

}
