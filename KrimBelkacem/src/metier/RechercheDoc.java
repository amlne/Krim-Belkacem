package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.doc.DocDao;

public class RechercheDoc {
	private String sql;
	private DocDao docDao;
	private String sqlPagenation;
	private String page;

	public RechercheDoc(HttpServletRequest request, beans.Prof prof) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.docDao = daoFactory.getDocDao();

		String numero = request.getParameter("numero");
		String titre = request.getParameter("titre");
		String date_creation = request.getParameter("dateCreation");
		String filiere = request.getParameter("filiere");
		String niveau = request.getParameter("annee");
		String page = request.getParameter("page");
		this.page = page;
		String sql = "SELECT * FROM doc WHERE id_prof = " + prof.getId_prf() + " ";
		String sqlPagenation = "SELECT COUNT(id_doc) FROM doc WHERE id_prof = " + prof.getId_prf();
		if (numero != null && !numero.isEmpty()) {
			sql += " AND id_doc = \"" + numero + "\" ";
			sqlPagenation += " AND id_doc = \"" + numero + "\" ";
		}
		if (titre != null && !titre.isEmpty()) {
			sql += " AND Titre  LIKE(\"%" + titre + "%\") ";
			sqlPagenation += " AND Titre  LIKE(\"%" + titre + "%\") ";
		}
		if (date_creation != null && !date_creation.isEmpty()) {
			sql += " AND DATE(date_up) = \"" + date_creation + "\" ";
			sqlPagenation += " AND DATE(date_up) = \"" + date_creation + "\" ";
		}
		if (filiere != null && !filiere.isEmpty() && !filiere.equals("")) {
			sql += " AND id_fil = \"" + filiere + "\"";
			sqlPagenation += " AND id_fil = \"" + filiere + "\" ";
		}
		if (niveau != null && !niveau.isEmpty()) {
			sql += " AND annee = " + niveau + " ";
			sqlPagenation += " AND annee = " + niveau + " ";
		}
		this.sql = sql;
		this.sqlPagenation = sqlPagenation;
		this.getRequestSqlDocProf();
	}

	public RechercheDoc(HttpServletRequest request, beans.Eleve eleve) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.docDao = daoFactory.getDocDao();
		
		String page = request.getParameter("page");
		this.page = page;
		// String sql = "SELECT * FROM doc WHERE id_prof =
		// \""+prof.getId_prf()+"\"";
		String sql = "select matiere.* ,doc.* from doc,matiere where doc.id_fil=\""+eleve.getFiliere()+"\" AND  doc.id_mat = matiere.id_mat ";
		
		this.sql = sql;
		this.sqlPagenation = "SELECT COUNT(id_doc) from doc,matiere where doc.id_fil=\""+eleve.getFiliere()+"\" AND  doc.id_mat = matiere.id_mat ";
		this.getRequestSqlDocProf();
	}

	public RechercheDoc(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.docDao = daoFactory.getDocDao();

		String titre = request.getParameter("titre");
		String filiere = request.getParameter("filiere");
		String niveau = request.getParameter("annee");
		String page = request.getParameter("page");
		this.page = page;
		
		String sql = "SELECT * FROM doc ";
		String sqlPagenation = "SELECT COUNT(id_doc) FROM doc ";

		if (niveau != null && !niveau.isEmpty()) {
			sql += "WHERE annee = \"" + niveau + "\"";
			sqlPagenation += "WHERE annee = \"" + niveau + "\"";
		}

		if (titre != null && !titre.isEmpty()) {
			sql += " AND Titre  LIKE(\"%" + titre + "%\") ";
			sqlPagenation += " AND Titre  LIKE(\"%" + titre + "%\") ";
		}

		if (filiere != null && !filiere.isEmpty()) {
			sql += "AND id_fil = \"" + filiere + "\"";
			sqlPagenation += "AND id_fil = \"" + filiere + "\"";
		}
		this.sql = sql;
		this.sqlPagenation = sqlPagenation;
		this.getRequestSqlDocProf();
	}

	public List<beans.Doc> listerDoc() {

		return docDao.listerDoc(sql);
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public beans.NombreDoc nombreDoc(beans.Prof prof) {
		return docDao.nombreDoc(prof);

	}

	public void getRequestSqlDocProf() {

		String sqlP = this.sqlPagenation;
		String sql = this.sql;

		int num = docDao.numDocRequest(sqlP);
		int max_num_page = (num / 8) + 1;

		if (page != null && !page.isEmpty()) {
			int page = 0;
			try {
				page = Integer.valueOf(this.page).intValue();
			} catch (Exception e) {
				page = 0;
			}
			if (page <= 0 || page > max_num_page) {
				sql += "LIMIT 0 ,8 ;";
			} else {
				sql += "LIMIT " + (page - 1) * 8 + " , 8 ;";
			}
		} else {
			sql += "LIMIT 0 ,8 ;";
		}
		this.sql = sql;
	}

	public int getMaxPages(){
		int num = docDao.numDocRequest(this.sqlPagenation);
		return (num/8)+1;
	}
}
