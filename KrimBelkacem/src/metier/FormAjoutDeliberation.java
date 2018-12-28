package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.Admin;
import beans.Deliberation;
import dao.DaoFactory;
import dao.deliberation.DeliberationDao;

public class FormAjoutDeliberation {
	private boolean valide = false;
	private String erreur;
    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/home/fateh/projetLicenceSauv/deliberation/";
    
    private DeliberationDao deliberationDao;
    private Admin admin = null;
    
    public FormAjoutDeliberation(Admin admin) {
    	DaoFactory daoFactory = DaoFactory.getInstance();
    	this.deliberationDao = daoFactory.getDeliberationDao();
    	this.admin = admin;
	}
    
    public void controleform(HttpServletRequest request) throws IllegalStateException, IOException, ServletException{
    	String trimestre = request.getParameter("trimestre");
		String classe = request.getParameter("classe");
		// On r�cup�re le champ du fichier
		Part part = request.getPart("fichier");
		// On v�rifie qu'on a bien re�u un fichier
		String nomFichier = "";
		if (part != null) {
			nomFichier = getNomFichier(part);
		} else {
			System.out.println("part de fichier est vide");
		}
		// les controles

		if (trimestre != null && classe != null && nomFichier != null) {
			if (!trimestre.isEmpty() && !classe.isEmpty() && !nomFichier.isEmpty()) {
				// ****************
				// *** ecrire dans la bdd
				// ****************

				String chemin_fichier = CHEMIN_FICHIERS + classe + "/" + trimestre + "/";

				Deliberation deliberation = new Deliberation();
				int trim = 0;
				deliberation.getClasse().setId_cls(classe);
				if (trimestre.equals("1")) {
					deliberation.setTrimestre1(chemin_fichier + nomFichier);
					trim = 1;
				}
				if (trimestre.equals("2")) {
					deliberation.setTrimestre2(chemin_fichier + nomFichier);
					trim = 2;
				}
				if (trimestre.equals("3")) {
					deliberation.setTrimestre3(chemin_fichier + nomFichier);
					trim = 3;
				}
				if (deliberationDao.ajout(deliberation,trim) == true) {
					// *****************
					String nomChamp = part.getName();
					// Corrige un bug du fonctionnement d'Internet Explorer
					nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1)
							.substring(nomFichier.lastIndexOf('\\') + 1);
					// On �crit d�finitivement le fichier sur le disque
					ecrireFichier(part, nomFichier, chemin_fichier);
					request.setAttribute(nomChamp, nomFichier);
					// *****************
					valide = true;
				} else {
					valide = false;
				}

			} else {
				valide = false;
				erreur = "il ya des champs vides";
			}
		} else {
			valide = false;
			erreur = "il ya des champs vides";
		}
        
	}
    
    private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
    private void ecrireFichier( Part part, String nomFichier, String chemin) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try{
        	File f = new File(chemin);
        	f.mkdirs();
        }catch(Exception e){
        	System.out.println("erreur de chemin");
        }
        try {
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            File fff = new File(chemin + nomFichier);
            FileOutputStream fout = null;
            try{
            	fout = new FileOutputStream(fff);
            }catch(Exception e){
            	System.out.println("file outputStrim exception ");
            	e.printStackTrace();
            }
            sortie = new BufferedOutputStream(fout, TAILLE_TAMPON);
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }catch(NullPointerException e){
            	System.out.println("null pointer Exception ");
            	e.printStackTrace();
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }
    public String getErreur() {
		return erreur;
	}
	public void setErreur(String erreur) {
		this.erreur = erreur;
	}
	public boolean isValide() {
		return valide;
	}
   

}
