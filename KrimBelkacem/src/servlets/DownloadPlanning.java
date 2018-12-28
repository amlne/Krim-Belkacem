package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadPlanning")
public class DownloadPlanning extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadPlanning() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		metier.DownloadPlanning download = new metier.DownloadPlanning(request,response);
		if(download.telecharge(download.getId_plan()) == true){
			System.out.println("1");
			request.setAttribute("msgDownload", "telechargement reussit");
			request.getServletContext().getRequestDispatcher("/admin").forward(request, response);
		}else{
			System.out.println("2");
			request.setAttribute("msgDownload", download.getErreur());
			response.sendRedirect("http://localhost:8080/projetLicence/admin");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
