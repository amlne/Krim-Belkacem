package metier;

import java.util.List;

import beans.Admin;
import beans.Preinscription;
import dao.DaoFactory;
import dao.preinscription.PreinscriptionDao;

public class GestionPreinscription {
	
	private String erreur;
	private Admin admin;
	private PreinscriptionDao preinscriptionDao;
	
	public GestionPreinscription() {
		super();
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.preinscriptionDao = daoFactory.getPreinscriptionDao();
		
	}
	
	public boolean ouvert(){
		
		List<Preinscription> preinscriptions = preinscriptionDao.listerPreinscription("select * from etat_preinscription;");
		if(preinscriptions.size() == 1){
			Preinscription preinscription = preinscriptions.get(0);
			if(preinscription.getOuvert() == 0){
				return false;
			}else{
				return true;
			}
		}else{
			erreur = "erreur da la bdd";
		}
		
		return false;
		
	}

	public boolean ouvrirPreinscription(){
		return preinscriptionDao.ouvrirPreinscription();
	}
	public boolean fermerPreinscription(){
		return preinscriptionDao.fermerPreinscription();
	}
}
