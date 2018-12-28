package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.deliberation.DeliberationDao;

public class RechercheDeliberation {
	private String sql;
	private DeliberationDao deliberationDao;
	
	public RechercheDeliberation(HttpServletRequest request) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		deliberationDao = daoFactory.getDeliberationDao();
		
		String trimestre = request.getParameter("trimestre");
		String classe = request.getParameter("classe");
		String page = request.getParameter("page");
		String sql = "SELECT * FROM deliberation";

		if (classe == null || classe.isEmpty()) {
			if (trimestre == null || trimestre.isEmpty()) {
				sql += ";";
			} else {
				sql += " WHERE trimestre = \"" + trimestre + "\" ;";
			}
		} else {
			if (trimestre == null || trimestre.isEmpty()) {
				sql += " WHERE id_cls = \"" + classe + "\" ;";
			} else {
				sql += " WHERE id_cls = \"" + classe + "\" AND trimestre = \"" + trimestre + "\" ;";
			}
		}

		this.sql = sql;
	}
	public List<beans.Deliberation> listerDeliberation(){
		return deliberationDao.listerDeliberation(sql);
	}
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
}