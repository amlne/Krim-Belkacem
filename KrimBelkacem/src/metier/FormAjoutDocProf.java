package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import beans.Doc;
import dao.DaoFactory;
import dao.doc.DocDao;

public class FormAjoutDocProf {
	private boolean valide = false;
	private String erreur;
    public static final int TAILLE_TAMPON = 10240;
    public static final String CHEMIN_FICHIERS = "/home/fateh/projetLicenceSauv/fichiers/";
    
    private DocDao docDao;
    private beans.Prof prof;
    
    public FormAjoutDocProf(beans.Prof prof){
    	super();
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.docDao = daoFactory.getDocDao();
        this.prof = prof;
    }
	
	public void controleform(HttpServletRequest request) throws IllegalStateException, IOException, ServletException{
		String titre = request.getParameter("titre");
		String filiere = request.getParameter("filiere");
		String type = request.getParameter("type");
		String annee = request.getParameter("annee");
		// On r�cup�re le champ du fichier
        Part part = request.getPart("fichier");
        // On v�rifie qu'on a bien re�u un fichier
        String nomFichier = "";
        if(part != null){
        	nomFichier = getNomFichier(part);
        }else{
        }
        // les controles
        
        if(titre != null && filiere != null && type != null && annee != null && nomFichier != null){
        	if(!titre.isEmpty() && !filiere.isEmpty() && !type.isEmpty() && !annee.isEmpty() && !nomFichier.isEmpty())
        	{
        		//****************
        		//*** ecrire dans la bdd
        		//****************
        		
        		String chemin_fichier = CHEMIN_FICHIERS+prof.getId_prf()+"/";
        		
        		List<beans.Matiere> matieres = docDao.listerMatiere("SELECT matiere.* FROM prof_mat,matiere WHERE prof_mat.id_prof = "+prof.getId_prf()+" AND prof_mat.id_mat = matiere.id_mat AND matiere.filiere = \""+filiere+"\";");
        		String id_mat = "";
        		if(matieres != null && matieres.size() == 1){
        			id_mat = matieres.get(0).getId_mat();
        			System.out.println("matiere ==> "+id_mat);
        		}else{
        			System.out.println("erreur de la matiere");
        			valide = false;
        			return;
        		}
        		
        		Doc doc = new Doc();
        		doc.setTitre(titre);
        		doc.setFiliere(filiere);
        		doc.setType(type);
        		doc.setAnnee(annee);
        		doc.setId_mat(id_mat);
        		doc.setUrl(chemin_fichier);
        		doc.setNom_fichier(nomFichier);
        		doc.setProf(prof);
        		
        		String key = docDao.ajoutDoc(doc);
        		if(key == null ||key.isEmpty()){
        			valide = false;
        			return;
        		}
        		
        		//*****************
                String nomChamp = part.getName();
                // Corrige un bug du fonctionnement d'Internet Explorer
                nomFichier = nomFichier.substring(nomFichier.lastIndexOf('/') + 1).substring(nomFichier.lastIndexOf('\\') + 1);
                // On �crit d�finitivement le fichier sur le disque
                System.out.println(" ==> "+prof.getId_prf());
                ecrireFichier(part, nomFichier, chemin_fichier+"/"+key+"/");
                request.setAttribute(nomChamp, nomFichier);
        		//*****************
        		valide = true;
        	}else{
        		valide = false;
        		erreur = "il ya des champs vides";
        	}
        }else{
        	valide = false;
        	erreur = "il ya des champs vides";
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
	private static String getNomFichier( Part part ) {
        for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
            if ( contentDisposition.trim().startsWith( "filename" ) ) {
                return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 ).trim().replace( "\"", "" );
            }
        }
        return null;
    }
	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {
        BufferedInputStream entree = null;
        BufferedOutputStream sortie = null;
        try {
        	File f = new File(chemin);
        	f.mkdir();
            entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
            sortie = new BufferedOutputStream(new FileOutputStream(new File(chemin + nomFichier)), TAILLE_TAMPON);
            byte[] tampon = new byte[TAILLE_TAMPON];
            int longueur;
            while ((longueur = entree.read(tampon)) > 0) {
                sortie.write(tampon, 0, longueur);
            }
        } finally {
            try {
                sortie.close();
            } catch (IOException ignore) {
            }
            try {
                entree.close();
            } catch (IOException ignore) {
            }
        }
    }

}
