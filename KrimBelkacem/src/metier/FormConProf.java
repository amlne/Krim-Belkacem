package metier;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.prof.ProfDao;

public class FormConProf {
	private ProfDao profDao;
	private beans.Prof prof;
	
	private boolean valide = false;
	private String msgErreur = "combinaison pseudo mdp invalide";
	
	public FormConProf(){
		super();
		DaoFactory daoFactory = DaoFactory.getInstance();
        this.profDao = daoFactory.getProfDao();
	} 
	
	public boolean isValide() {
		return valide;
	}
	
	public void controleForm(HttpServletRequest request){
		String pseudo = request.getParameter("pseudoProf");
		String mdp = request.getParameter("mdpProf");
		
		List<beans.Prof> profs = profDao.profCon(pseudo, mdp);
		
		if(pseudo != null && !pseudo.equals("") && mdp != null && !mdp.equals("")){
			if(profs.size() ==1){
				if(profs.get(0).getNom().equals(pseudo) && mdp.equals(profs.get(0).getMdp())){
					valide = true;
					this.prof = profs.get(0);
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
	public beans.Prof getProf(){
		return prof;
	}
	
}
