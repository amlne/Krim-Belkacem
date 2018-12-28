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

@WebServlet("/DownloadFicheDeVoeux")
public class DownloadFicheDeVoeux extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadFicheDeVoeux() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		metier.DownloadFicheDeVoeux down = new metier.DownloadFicheDeVoeux(request, response);
		if(down.telecharge()){
			//telechargement correct
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("DownloadFicheDeVoeux", "erreur de telechargemnt");
			response.sendRedirect("http://localhost:8080/projetLicence/eleve");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
