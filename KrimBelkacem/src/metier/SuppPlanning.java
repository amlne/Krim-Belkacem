package metier;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Planning;
import dao.DaoFactory;
import dao.planning.PlanningDao;

public class SuppPlanning {
	private String sql;
	private String id_plan = "0";
	private PlanningDao planningDao;
	private String erreur;
	private String trimestre;

	public SuppPlanning(HttpServletRequest request) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.planningDao = daoFactory.getPlanningDao();

		String id_plan = request.getParameter("id_plan");
		this.trimestre = request.getParameter("trimestre");
		if (id_plan != null && !id_plan.isEmpty()) {
			this.id_plan = id_plan;
			sql = "SELECT * FROM plannings WHERE id_plan = " + id_plan;
		}
	}

	public boolean suppPlanning(beans.Admin admin) {

		boolean valideSupp = false;
		List<Planning> plannings = planningDao.listerPlanning(sql);

		if (plannings.size() == 1) {
			Planning planning = plannings.get(0);
			// on teste si l'admin est connecte
			if (admin != null) {
				if (this.trimestre == null) {
					boolean suppPlanningBdd = planningDao.suppPlanning(id_plan);
					if (suppPlanningBdd == true) {
						// on supprime dans le disque
						if (suppFichier(planning) == true) {
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
					boolean suppPlanningBdd = planningDao.suppPlanning(id_plan,trimestre);
					if (suppPlanningBdd == true) {
						// on supprime dans le disque
						if (suppFichier(planning,trimestre) == true) {
							valideSupp = true;
						} else {
							valideSupp = false;
							erreur = "la suppresion de fichier dans le disque n'a pas pu se terminer ";
						}

					} else {
						valideSupp = false;
						this.erreur = "un probleme est servenu dans la bdd";
					}
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

	public boolean suppFichier(Planning planning) {
		boolean valideSuppFichier = false;
		try {
			if (planning.getTrimestre1() != null) {
				File f1 = new File(planning.getTrimestre1());
				f1.delete();
			}
			if (planning.getTrimestre2() != null) {
				File f2 = new File(planning.getTrimestre2());
				f2.delete();
			}
			if (planning.getTrimestre3() != null) {
				File f3 = new File(planning.getTrimestre3());
				f3.delete();
			}
			if (planning.getAnnuel() != null) {
				File f4 = new File(planning.getAnnuel());
				f4.delete();
			}
			valideSuppFichier = true;
		} catch (Exception e) {
			valideSuppFichier = false;
			System.out.println("erreur dans suppPlanning");
			e.printStackTrace();
		}
		return valideSuppFichier;
	}
	public boolean suppFichier(Planning planning,String trimestre) {
		boolean valideSuppFichier = false;
		try {
			if (trimestre != null && trimestre.equals("1") && planning.getTrimestre1() != null) {
				File f1 = new File(planning.getTrimestre1());
				f1.delete();
			}
			if (trimestre != null && trimestre.equals("2") && planning.getTrimestre2() != null) {
				File f2 = new File(planning.getTrimestre2());
				f2.delete();
			}
			if (planning.getTrimestre3() != null && trimestre != null && trimestre.equals("3")) {
				File f3 = new File(planning.getTrimestre3());
				f3.delete();
			}
			if (planning.getAnnuel() != null && trimestre != null && trimestre.equals("annuel")) {
				File f4 = new File(planning.getAnnuel());
				f4.delete();
			}
			valideSuppFichier = true;
		} catch (Exception e) {
			valideSuppFichier = false;
			System.out.println("erreur dans suppPlanning");
			e.printStackTrace();
		}
		return valideSuppFichier;
	}


}
