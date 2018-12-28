package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import metier.RechercheDoc;

@WebServlet("/SuppDoc")
public class SuppDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuppDoc() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Prof prof = (beans.Prof) session.getAttribute("prof");
		if(prof != null && prof.isValide()){
			// *****************  le code *****************
			System.out.println("////// SuppDoc  /////// ");
			metier.SuppDoc supp = new metier.SuppDoc(request);
			if(supp.suppDoc(prof) == true){
				session.setAttribute("msgSuppDoc", "suppression avec succèe");
			}else{
				session.setAttribute("msgSuppDoc", "la suppression a échoue");
			}
			
			// ********************************************
			response.sendRedirect("http://localhost:8080/projetLicence/prof");
		}else{
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionProf");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("http://localhost:8080/projetLicence/Prof");
	}

}
