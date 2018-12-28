package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DownloadPlanningEleve")
public class DownloadPlanningEleve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadPlanningEleve() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		metier.DownloadPlanning download = new metier.DownloadPlanning(request,response);
		if(download.telecharge(download.getSql()) == true){
			request.setAttribute("msgDownload", "telechargement reussit");
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("DownloadPlanning", download.getErreur());
			response.sendRedirect("http://localhost:8080/projetLicence/eleve");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
