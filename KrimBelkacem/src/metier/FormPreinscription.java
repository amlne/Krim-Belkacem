package metier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.Admin;
import beans.Deliberation;
import dao.DaoFactory;
import dao.preinscription.PreinscriptionDao;

public class FormPreinscription {
	private boolean valide = false;
	private String erreur;
	
	private PreinscriptionDao preinscriptionDao;
    private Admin admin = null;
    
    public FormPreinscription() {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	preinscriptionDao = daoFactory.getPreinscriptionDao();
	}
    
    public boolean controleform(HttpServletRequest request) throws IllegalStateException, IOException, ServletException{
    	String nom = request.getParameter("nom");
    	String prenom = request.getParameter("prenom");
    	String date_nais = request.getParameter("date_nais");
    	String lieu_nais = request.getParameter("lieu_nais");
    	String adresse = request.getParameter("adresse");
    	
    	beans.FormPreinscription form = new beans.FormPreinscription();
    	if(nom != null && !nom.isEmpty()){
    		if(prenom != null && !prenom.isEmpty()){
    			if(date_nais != null && !date_nais.isEmpty())
    				if(lieu_nais != null && !lieu_nais.isEmpty()){
    					if(adresse != null && !adresse.isEmpty()){
    						form.setNom(nom);
    						form.setPrenom(prenom);
    						form.setDate_nais(date_nais);
    						form.setLieu_nais(lieu_nais);
    						form.setAdresse(adresse);
    						if(preinscriptionDao.ajoutFormPreinscription(form)){
    							return true;
    						}else{
    							return false;
    						}
    					}else{
    						return false;		
    					}
    				}else{
    					return false;
    				}
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
    	return false;
	}

}
