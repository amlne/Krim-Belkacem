package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DownloadRubrique")
public class DownloadRubrique extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadRubrique() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		metier.DownloadRubrique download = new metier.DownloadRubrique(request, response);
		if(download.telecharge() == true){
			request.setAttribute("msgDownload", "telechargement reusit");
			
		}else{
			request.setAttribute("msgDownload", download.getErreur());			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
