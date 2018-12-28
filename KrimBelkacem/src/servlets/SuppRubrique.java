package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SuppRubrique")
public class SuppRubrique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuppRubrique() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Admin admin = (beans.Admin) session.getAttribute("admin");
		if (admin != null && admin.isValide()) {
			// ***************** le code *****************
			
			metier.SuppRubrique supp = new metier.SuppRubrique(request);
			if(supp.suppRubrique(admin) == true){
				session.setAttribute("msgSuppRubrique","suppression avec succèe");
			}else{
				session.setAttribute("msgSuppRubrique", "la suppression a échoue");
			}
			
			// ********************************************
			response.sendRedirect("http://localhost:8080/projetLicence/admin");
		} else {
			response.sendRedirect("http://localhost:8080/projetLicence/ConnectionAdmin");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
