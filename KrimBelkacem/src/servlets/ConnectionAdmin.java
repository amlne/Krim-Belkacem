package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Admin;
import metier.FormConAdmin;

@WebServlet("/ConnectionAdmin")
public class ConnectionAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConnectionAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/connectionAdmin.jsp").forward(request, response);;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FormConAdmin control = new FormConAdmin();
		control.controleForm(request);
		if(control.isValide()){
			request.setAttribute("msg","mdp valide");
			HttpSession session = request.getSession();
			Admin admin = control.getAdmin();
			admin.setValide(true);
			session.setAttribute("admin", admin);
			response.sendRedirect("http://localhost:8080/projetLicence/admin");
		}else{
			request.setAttribute("msg",control.getMsgErreur());
			this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/connectionAdmin.jsp").forward(request, response);;
		}
	}

}
