package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Eleve;
import metier.FormConEleve;

@WebServlet("/ConnectionEleve")
public class ConnectionEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ConnectionEleve() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.sendRedirect("http://localhost:8080/projetLicence/accueil");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FormConEleve control = new FormConEleve();
		control.controleForm(request);
		if(control.isValide()){
			request.setAttribute("msg","mdp valide");
			HttpSession session = request.getSession();
			Eleve eleve = control.getEleve();
			eleve.setValide(true);
			session.setAttribute("eleve", eleve);
			response.sendRedirect("http://localhost:8080/projetLicence/eleve");
		}else{
			request.setAttribute("msg",control.getMsgErreur());
			response.sendRedirect("http://localhost:8080/projetLicence/eleve");
		}
	}

}
