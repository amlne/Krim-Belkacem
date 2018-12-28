package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadFicheDeVoeux {

	private String erreur;
	private static final String CHEMIN_FICHIER_LETTRE = "/home/fateh/projetLicenceSauv/ficheDeVoeux/lettre.pdf";
	private static final String CHEMIN_FICHIER_SCIENCE = "/home/fateh/projetLicenceSauv/ficheDeVoeux/science.pdf";

	private String filiere;
	private HttpServletResponse response;

	public DownloadFicheDeVoeux(HttpServletRequest request, HttpServletResponse response) {
		this.filiere = request.getParameter("filiere");
		this.response = response;
	}

	public boolean telecharge() throws IOException {
		boolean valideTele = false;
		String chemin;
		
		if (this.filiere == null || this.filiere.isEmpty()) {
			return false;
		}
		if(filiere.equals("TCL")){
			 chemin = CHEMIN_FICHIER_LETTRE;
		}else 
			if(filiere.equals("TCST")){
				chemin = CHEMIN_FICHIER_SCIENCE;
			}else{
				return false;
			}

		File fichier = new File(chemin);				
		
		response.reset();
		response.setBufferSize(10240);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Length", String.valueOf(fichier.length()));
		response.setHeader("Content-Disposition", "attachment; filename=\"fiche de voeux\"");
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

		return valideTele;
	}
}
