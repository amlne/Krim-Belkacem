package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import metier.FormAjoutDocProf;

@WebServlet("/AjoutDoc")
public class AjoutDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AjoutDoc() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("http://localhost:8080/projetLicence/Prof");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		beans.Prof prof = (beans.Prof) session.getAttribute("prof");
		if(prof != null && prof.isValide()){
			FormAjoutDocProf formAjout = new FormAjoutDocProf(prof);
			formAjout.controleform(request);
			if(formAjout.isValide()){
				session.setAttribute("msgValidationAjoutDoc","c'est valide");
				response.sendRedirect("http://localhost:8080/projetLicence/prof");
			}else{
				String erreur = formAjout.getErreur();
				session.setAttribute("msgValidationAjoutDoc", erreur);
				response.sendRedirect("http://localhost:8080/projetLicence/prof");
			}
		}else{
			response.sendRedirect("http://localhost:8080/projetLicence/connectionProf");
		}
	}

}
