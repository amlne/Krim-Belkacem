package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.SysexMessage;

import dao.DaoFactory;
import dao.doc.DocDao;

public class Download {
	private String id_doc;
	private DocDao docDao;
	private String erreur;
	
	HttpServletResponse response;
	HttpServletRequest request;
	
	public Download(HttpServletRequest request,HttpServletResponse response){
		
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.docDao = daoFactory.getDocDao();
		this.response = response;
		this.request = request;
	}
	public boolean telecharge() throws IOException{
		boolean valideTele = false;
		
		String id_doc = request.getParameter("id");
		if(id_doc != null && !id_doc.isEmpty()){
			this.id_doc = id_doc;
		}else{
			valideTele = false;
			return valideTele;
		}
		
		String sql = "SELECT * FROM doc WHERE id_doc = \""+this.id_doc+"\";";
		List<beans.Doc> docs = docDao.listerDoc(sql);
		if(docs.size() == 1){
			beans.Doc doc = docs.get(0);
			String chemin = doc.getUrl();
			File fichier = new File(chemin);
			if(!fichier.exists()){
				valideTele = false;
				this.erreur = "le chemin de fichier set incorrect";
			}else{
				response.reset();
				response.setBufferSize(10240);
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Length", String.valueOf(fichier.length()));
				response.setHeader( "Content-Disposition", "attachment; filename=\"" + fichier.getName()+ "\"" );
				BufferedInputStream entree = null;
		        BufferedOutputStream sortie = null;
		        try{
		        	entree = new BufferedInputStream( new FileInputStream( fichier ), 10240 );
		            sortie = new BufferedOutputStream( response.getOutputStream(), 10240 );
		            byte[] tampon = new byte[10240];
		            int longueur;
		            while ( ( longueur = entree.read( tampon ) ) > 0 ) {
		                sortie.write( tampon, 0, longueur );
		            }
		            valideTele = true;
		        }catch(Exception e){
		        	valideTele = false;
		        	this.erreur = "erreur de telechargement";
		        }finally {
		        	try{
		        		sortie.close();
			            entree.close();
		        	}catch(Exception e){
		        		valideTele = false;
		        		this.erreur = "erreur de telechargement";
		        	}
		        }
			}
			
		}else{
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
