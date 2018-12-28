package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Rubrique;
import dao.DaoFactory;
import dao.rubrique.RubriqueDao;

public class DownloadRubrique {
	private String id_rubrique;
	private RubriqueDao rubriqueDao;
	private String erreur;

	HttpServletResponse response;
	HttpServletRequest request;

	public DownloadRubrique(HttpServletRequest request, HttpServletResponse response) {

		DaoFactory daoFactory = DaoFactory.getInstance();
		this.rubriqueDao = daoFactory.getRubriqueDao();
		this.response = response;
		this.request = request;
	}

	public boolean telecharge() throws IOException {
		boolean valideTele = false;

		String id_rubrique = request.getParameter("id");
		if (id_rubrique != null && !id_rubrique.isEmpty()) {
			this.id_rubrique = id_rubrique;
		} else {
			valideTele = false;
			return valideTele;
		}

		String sql = "SELECT * FROM rubrique WHERE id_rub = \"" + this.id_rubrique + "\";";
		List<beans.Rubrique> rubriques = rubriqueDao.listerRubrique(sql);
		if (rubriques.size() == 1) {
			beans.Rubrique rubrique = rubriques.get(0);
			String chemin = rubrique.getUrl();
			File fichier = new File(chemin);
			if (fichier == null || !fichier.exists()) {
				valideTele = false;
				this.erreur = "le chemin de fichier set incorrect";
				fichier = new File("/home/fateh/projetLicenceSauv/rubrique/dossier.jpg");
			}
			response.reset();
			response.setBufferSize(10240);
			response.setContentType("image/png");
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

		} else {
			valideTele = false;
			this.erreur = "fichier introuvable";
		}
		return valideTele;
	}

	public String getErreur() {
		return erreur;
	}

	public void setErreur(String erreur) {
		this.erreur = erreur;
	}

}
