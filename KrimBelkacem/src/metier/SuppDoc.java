package metier;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Doc;
import dao.DaoFactory;
import dao.doc.DocDao;

public class SuppDoc {
	private String sql;
	private String id_doc = "0";
	private DocDao docDao;
	private String erreur;
	
	public SuppDoc(HttpServletRequest request){
		
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.docDao = daoFactory.getDocDao();
		
		String id_doc = request.getParameter("id");
		if(id_doc != null && !id_doc.isEmpty()){
			this.id_doc = id_doc;
			sql = "SELECT * FROM doc WHERE id_doc = "+id_doc;
		}
	}

	public boolean suppDoc(beans.Prof prof){
		boolean valideSupp = false;
		//recherche le doc
		List<Doc> docs = docDao.listerDoc(sql);
		if(docs.size() == 1){
			Doc doc = docs.get(0);
			if(doc.getProf().getId_prf().equals(prof.getId_prf())){
				boolean suppDocBdd = docDao.suppDoc(id_doc);
				if(suppDocBdd == true){
					// on supprime dans le disque
					if(suppFichier(doc) == true){
						valideSupp = true;
					}else{
						valideSupp = false;
						erreur = "la suppresion de fichier dans le disque n'a pas pu se terminer ";
					}
					
				}else{
					valideSupp = false;
					this.erreur = "un probleme est servenu dans la bdd";
				}
			}else{
				this.erreur = "vous ne pouvez pas supprimer le document ";
				valideSupp = false;
			}
		}else{
			valideSupp = false;
			this.erreur = "le document n'est pas valide";
		}
		
		return valideSupp;
	}
	public boolean suppFichier(Doc doc){
		boolean valideSuppFichier = false;
		try {
			File f = new File(doc.getUrl());
			f.delete();
			valideSuppFichier = true;			
		} catch (Exception e) {
			valideSuppFichier = false;
		}
		return valideSuppFichier;
	}
}
