package metier;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.deliberation.DeliberationDao;

public class DownloadDeliberation {
	private DeliberationDao deliberationDao;
	private String erreur;
	
	HttpServletRequest request;
	HttpServletResponse response;
	private String trimestre;
	
	public DownloadDeliberation(HttpServletRequest request,HttpServletResponse response) {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.deliberationDao = daoFactory.getDeliberationDao();
		this.response = response;
		this.request = request;
		this.trimestre = request.getParameter("trimestre");
	}
	
	public String getId_del(){
		String id_del = request.getParameter("id_del");
		if (id_del != null && !id_del.isEmpty()) {
			return "SELECT * FROM deliberation WHERE id_del = \"" + id_del + "\";";
		} else {
			return null;
		}
	}
	
	public String getSql(){
		String classe = request.getParameter("classe");
		String trimestre = request.getParameter("trimestre");
				
		if(classe != null && !classe.isEmpty() && trimestre != null && !trimestre.isEmpty()){
			return "SELECT * FROM deliberation WHERE id_cls = \"" + classe+"\";";
		}
		return null;
	}
	
	public boolean telecharge(String sql) throws IOException {
		boolean valideTele = false;

		if (sql == null || sql.isEmpty()) {
			return false;
		}if(this.trimestre == null || this.trimestre.isEmpty()){
			return false;
		}
		List<beans.Deliberation> deliberations = deliberationDao.listerDeliberation(sql);
		if (deliberations.size() == 1) {
			beans.Deliberation deliberation = deliberations.get(0);
			String trimestre1 = deliberation.getTrimestre1();
			String trimestre2 = deliberation.getTrimestre2();
			String trimestre3 = deliberation.getTrimestre3();
			File fichier = null;
			if (this.trimestre.equals("1")) {
				try{
					fichier = new File(trimestre1);
				}catch(Exception e){
					erreur = "la deliberation n'est pas prete";
					e.printStackTrace();
					return false;
				}
			}
			if (this.trimestre.equals("2")) {
				try{
					fichier = new File(trimestre2);
				}catch(Exception e){
					e.printStackTrace();
					erreur = "la deliberation n'est pas prete";
					return false;
				}
			}
			if (this.trimestre.equals("3")) {
				try{
					fichier = new File(trimestre3);
				}catch(Exception e){
					e.printStackTrace();
					erreur = "la deliberation n'est pas prete";
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
	public String getErreur(){
		return this.erreur;
	}
	public String getTrimestre() {
		return trimestre;
	}

	public void setTrimestre(String trimestre) {
		this.trimestre = trimestre;
	}

}
