package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadDeliberation")
public class DownloadDeliberation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadDeliberation() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		metier.DownloadDeliberation download = new metier.DownloadDeliberation(request,response);
		if(download.telecharge(download.getId_del()) == true){
			System.out.println("1");
			request.setAttribute("DownloadDeliberation", "telechargement reussit");
			response.sendRedirect("http://localhost:8080/projetLicence/admin");
		}else{
			System.out.println("2");
			request.setAttribute("DownloadDeliberation", download.getErreur());
			response.sendRedirect("http://localhost:8080/projetLicence/admin");			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
