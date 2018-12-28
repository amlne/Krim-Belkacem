package metier;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Rubrique;
import dao.DaoFactory;
import dao.rubrique.RubriqueDao;

public class SuppRubrique {
	
	private String sql;
	private String id_rub = "0";
	private RubriqueDao rubriqueDao;
	private String erreur;
	
	public SuppRubrique(HttpServletRequest request){
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.rubriqueDao = daoFactory.getRubriqueDao();

		String id_rub = request.getParameter("id_rub");
		if (id_rub != null && !id_rub.isEmpty()) {
			this.id_rub = id_rub;
			sql = "SELECT * FROM rubrique WHERE id_rub = " + id_rub;
		}
	}
	public boolean suppRubrique(beans.Admin admin) {

		boolean valideSupp = false;
		List<Rubrique> rubriques =rubriqueDao.listerRubrique(sql);

		if (rubriques.size() == 1) {
			Rubrique rubrique = rubriques.get(0);
			// on teste si l'admin est connecte
			if (admin != null) {
				boolean suppRubriqueBdd = rubriqueDao.suppRubrique(id_rub);
				if (suppRubriqueBdd == true) {
					// on supprime dans le disque
					if (suppFichier(rubrique) == true) {
						valideSupp = true;
					} else {
						valideSupp = false;
						erreur = "la suppresion de fichier dans le disque n'a pas pu se terminer ";
					}

				} else {
					valideSupp = false;
					this.erreur = "un probleme est servenu dans la bdd";
				}
			} else {
				this.erreur = "vous ne pouvez pas supprimer le document ";
				valideSupp = false;
			}
		} else {
			valideSupp = false;
			this.erreur = "le document n'est pas valide";
		}

		return valideSupp;

	}
	public boolean suppFichier(Rubrique rubrique){
		boolean valideSuppFichier = false;
		try {
			File f = new File(rubrique.getUrl());
			f.delete();
			valideSuppFichier = true;			
		} catch (Exception e) {
			valideSuppFichier = false;
		}
		return valideSuppFichier;
	}


}
