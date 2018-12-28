package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.RequestRechercheDoc;
import metier.RechercheDoc;

@WebServlet("/CourExam")
public class CourExam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CourExam() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RechercheDoc rechercheDoc = new RechercheDoc(request);
		RequestRechercheDoc requestPagination = new RequestRechercheDoc(request);
		
		request.setAttribute("docs", rechercheDoc.listerDoc());
		request.setAttribute("maxPages", rechercheDoc.getMaxPages());
		request.setAttribute("requestPagination", requestPagination.getRequestDoGetCourExam());
		this.getServletContext().getRequestDispatcher("/WEB-INF/pagesJsp/courExam.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
