package beans;

import javax.servlet.http.HttpServletRequest;

public class RequestRechercheDoc {
	HttpServletRequest request;
	public RequestRechercheDoc(HttpServletRequest request) {
		// TODO Auto-generated constructor stub
		super();
		this.request = request;
	}
	public String getRequestDoGet(){
		String numero = request.getParameter("numero");
		String titre = request.getParameter("titre");
		String date_creation = request.getParameter("dateCreation");
		String filiere = request.getParameter("filiere");
		String niveau = request.getParameter("annee");
		
		if(numero == null)
			numero = "";
		if(titre == null)
			titre = "";
		if(date_creation == null)
			date_creation = "";
		if(filiere == null)
			filiere = "";
		if(niveau == null)
			niveau = "";
		
		return "?numero="+numero+"&titre="+titre+"&dateCreation"+date_creation+"&filiere="+filiere+"&annee="+niveau;
	}

	public String getRequestDoGetCourExam(){
		String titre = request.getParameter("titre");
		String filiere = request.getParameter("filiere");
		String niveau = request.getParameter("annee");
		
		if(titre == null)
			titre = "";
		if(filiere == null)
			filiere = "";
		if(niveau == null)
			niveau = "";
		
		return "?titre="+titre+"&filiere="+filiere+"&annee="+niveau;
	}
		
}
