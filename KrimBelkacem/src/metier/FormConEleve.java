package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.eleve.EleveDao;

public class FormConEleve {
	private EleveDao eleveDao;
	private beans.Eleve eleve;
	
	private boolean valide = false;
	private String msgErreur = "combinaison pseudo mdp invalide";
	
	public FormConEleve(){
		super();
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.eleveDao = daoFactory.getEleveDao();
	}
	public boolean isValide() {
		return valide;
	}
	public void controleForm(HttpServletRequest request){
		String pseudo = request.getParameter("pseudoEleve");
		String mdp = request.getParameter("mdpEleve");
		
		List<beans.Eleve> eleves = eleveDao.eleveCon(pseudo, mdp);
		
		if(pseudo != null && !pseudo.equals("") && mdp != null && !mdp.equals("")){
			if(eleves.size() ==1){
				if(eleves.get(0).getNom().equals(pseudo) && mdp.equals(eleves.get(0).getMdp())){
					valide = true;
					this.eleve = eleves.get(0);
				}else{
					valide = false;
				}
			}else{
				valide = false;
			}
		}else{
				valide = false;
		}
	}
	public void setValide(boolean valide) {
		this.valide = valide;
	}
	public String getMsgErreur() {
		return msgErreur;
	}
	public void setMsgErreur(String msgErreur) {
		this.msgErreur = msgErreur;
	}
	public beans.Eleve getEleve() {
		return eleve;
	}
	public void setEleve(beans.Eleve eleve) {
		this.eleve = eleve;
	}

}
