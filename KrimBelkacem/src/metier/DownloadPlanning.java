package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Planning;
import dao.DaoFactory;
import dao.planning.PlanningDao;

public class DownloadPlanning {

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

	private PlanningDao planningDao;
	private String erreur;
	private String trimestre;

	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

	HttpServletResponse response;
	HttpServletRequest request;

	public DownloadPlanning(HttpServletRequest request, HttpServletResponse response) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.planningDao = daoFactory.getPlanningDao();
		this.response = response;
		this.request = request;
		this.trimestre = request.getParameter("trimestre");
	}

	public String getId_plan() {
		String id_plan = request.getParameter("id_plan");
		if (id_plan != null && !id_plan.isEmpty()) {
			return "SELECT * FROM plannings WHERE id_plan = \"" + id_plan + "\";";
		} else {
			return null;
		}
	}

	public String getSql() {
		String classe = request.getParameter("classe");
		if (classe != null && !classe.isEmpty() && trimestre != null && !trimestre.isEmpty()) {
			return "SELECT * FROM plannings WHERE id_cls = \"" + classe +"\";";
		}
		return null;
	}

	public boolean telecharge(String sql) throws IOException {
		boolean valideTele = false;

		if (sql == null || sql.isEmpty()) {
			return false;
		}if(this.trimestre == null || this.trimestre.isEmpty())
			return false;
		List<beans.Planning> plannings = planningDao.listerPlanning(sql);
		if (plannings.size() == 1) {
			beans.Planning planning = plannings.get(0);
			String trimestre1 = planning.getTrimestre1();
			String trimestre2 = planning.getTrimestre2();
			String trimestre3 = planning.getTrimestre3();
			String annuel = planning.getAnnuel();
			File fichier = null;
			if (this.trimestre.equals("1")) {
				try{
					fichier = new File(trimestre1);
				}catch(Exception e){
					erreur = "le palanning n'est pas prete";
					e.printStackTrace();
					return false;
				}
			}
			if (this.trimestre.equals("2")) {
				try{
					fichier = new File(trimestre2);
				}catch(Exception e){
					erreur = "le planning n'est pas prete";
					e.printStackTrace();
					return false;
				}
			}
			if (this.trimestre.equals("3")) {
				try{
					fichier = new File(trimestre3);
				}catch(Exception e){
					erreur = "le planning n'est pas prete";
					e.printStackTrace();
					return false;
				}
			}
			if (this.trimestre.equals("annuel")) {
				try{
					fichier = new File(annuel);
				}catch(Exception e){
					erreur = "le planning n'est pas prete";
					e.printStackTrace();
					return false;
				}
			}
			if (fichier == null || !fichier.exists()) {
				valideTele = false;
				this.erreur = "le chemin de fichier set incorrect";
			} else {
				response.reset();
				response.setBufferSize(10240);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Length", String.valueOf(fichier.length()));
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fichier.getName() + "\"");
				BufferedInputStream entree = null;
				BufferedOutputStream sortie = null;
				try {
					entree = new BufferedInputStream(new FileInputStream(fichier), 10240);
					sortie = new BufferedOutputStream(response.getOutputStream(), 10240);
					byte[] tampon = new byte[10240];
					int longueur;
					while ((longueur = entree.read(tampon)) > 0) {
						sortie.write(tampon, 0, longueur);
					}
					valideTele = true;
				} catch (Exception e) {
					valideTele = false;
					this.erreur = "erreur de telechargement";
				} finally {
					try {
						sortie.close();
						entree.close();
					} catch (Exception e) {
						valideTele = false;
						this.erreur = "erreur de telechargement";
					}
				}
			}

		} else {
			valideTele = false;
			this.erreur = "fichier introuvable";
		}
		return valideTele;
	}
}
