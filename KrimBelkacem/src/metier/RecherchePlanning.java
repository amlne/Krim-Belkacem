package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Planning;
import dao.DaoFactory;
import dao.planning.PlanningDao;

public class RecherchePlanning {

	private String sql;
	PlanningDao planningDao;

	public RecherchePlanning(HttpServletRequest request) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.planningDao = daoFactory.getPlanningDao();

		String trimestre = request.getParameter("trimestre");
		String classe = request.getParameter("classe");
		String page = request.getParameter("page");
		String sql = "SELECT * FROM plannings";

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

	public List<Planning> listerPlanning() {
		return planningDao.listerPlanning(sql);
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

}
