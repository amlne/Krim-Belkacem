package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Rubrique;
import dao.DaoFactory;
import dao.rubrique.RubriqueDao;

public class RechercheRubrique {

	private String sql;
	RubriqueDao rubriqueDao;

	public RechercheRubrique() {
		// TODO Auto-generated constructor stub
		super();
		this.sql = "SELECT * FROM rubrique order by date_ajout desc;";
	}

	public RechercheRubrique(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.rubriqueDao = daoFactory.getRubriqueDao();

		String titre = request.getParameter("titre");
		String categorie = request.getParameter("categorie");
		String page = request.getParameter("page");
		String sql = "SELECT * FROM rubrique";

		if (titre == null || titre.isEmpty()) {
			if (categorie == null || categorie.isEmpty()) {
				sql += ";";
			} else {
				sql += " WHERE categorie = \"" + categorie + "\";";
			}
		} else {
			if (categorie == null || categorie.isEmpty()) {
				sql += " WHERE titre = \"" + titre + "\" ;";
			} else {
				sql += " WHERE categorie = \"" + categorie + "\" AND titre = \"" + titre + "\" ;";
			}
		}

		this.sql = sql;

	}

	public List<Rubrique> listerRubrique() {
		return rubriqueDao.listerRubrique(sql);
	}

	public List<Rubrique> listerRubriqueAccueil() {
		return rubriqueDao.listerRubrique(sql);
	}

}
