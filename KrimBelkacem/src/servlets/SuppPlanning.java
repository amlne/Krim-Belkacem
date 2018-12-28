package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/SuppPlanning")
public class SuppPlanning extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SuppPlanning() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		beans.Admin admin = (beans.Admin) session.getAttribute("admin");
		if (admin != null && admin.isValide()) {
			// ***************** le code *****************
			
			metier.SuppPlanning supp = new metier.SuppPlanning(request);
			if(supp.suppPlanning(admin) == true){
				session.setAttribute("msgSuppPlanning", "suppression avec succèe");
				// la suppression est ok
			}else{
				session.setAttribute("msgSuppPlanning", "suppression a échoue");
				// la suppression a echoue
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
