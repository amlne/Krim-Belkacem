package metier;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.Deliberation;
import dao.DaoFactory;
import dao.deliberation.DeliberationDao;

public class SuppDeliberation {
	private String sql;
	private String id_del = "0";
	private DeliberationDao deliberationDao;
	private String erreur;
	private String trimestre;

	public SuppDeliberation(HttpServletRequest request) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.deliberationDao = daoFactory.getDeliberationDao();

		String id_del = request.getParameter("id_del");
		this.trimestre = request.getParameter("trimestre");
		if (id_del != null && !id_del.isEmpty()) {
			this.id_del = id_del;
			sql = "SELECT * FROM deliberation WHERE id_del = " + id_del;
		}
	}

	public boolean suppDeliberation(beans.Admin admin) {

		boolean valideSupp = false;
		List<Deliberation> deliberations = deliberationDao.listerDeliberation(sql);

		if (deliberations.size() == 1) {
			Deliberation deliberation = deliberations.get(0);
			// on teste si l'admin est connecte
			if (admin != null) {
				if (this.trimestre == null) {
					boolean suppDeliberationBdd = deliberationDao.suppDeliberation(id_del);
					if (suppDeliberationBdd == true) {
						// on supprime dans le disque
						if (suppFichier(deliberation) == true) {
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
					boolean suppDeliberationBdd = deliberationDao.suppDeliberation(id_del, trimestre);
					if (suppDeliberationBdd == true) {
						// on supprime dans le disque
						if (suppFichier(deliberation, trimestre) == true) {
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

	public boolean suppFichier(Deliberation deliberation) {
		boolean valideSuppFichier = false;
		try {
			if (deliberation.getTrimestre1() != null) {
				File f1 = new File(deliberation.getTrimestre1());
				f1.delete();
			}
			if (deliberation.getTrimestre2() != null) {
				File f2 = new File(deliberation.getTrimestre2());
				f2.delete();
			}
			if (deliberation.getTrimestre3() != null) {
				File f3 = new File(deliberation.getTrimestre3());
				f3.delete();
			}
			valideSuppFichier = true;
		} catch (Exception e) {
			valideSuppFichier = false;
			System.out.println("erreur dans suppPlanning");
			e.printStackTrace();
		}
		return valideSuppFichier;
	}

	public boolean suppFichier(Deliberation deliberation, String trimestre) {
		boolean valideSuppFichier = false;
		try {
			if (trimestre != null && trimestre.equals("1") && deliberation.getTrimestre1() != null) {
				File f1 = new File(deliberation.getTrimestre1());
				f1.delete();
			}
			if (trimestre != null && trimestre.equals("2") && deliberation.getTrimestre2() != null) {
				File f2 = new File(deliberation.getTrimestre2());
				f2.delete();
			}
			if (deliberation.getTrimestre3() != null && trimestre != null && trimestre.equals("3")) {
				File f3 = new File(deliberation.getTrimestre3());
				f3.delete();
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
