package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.DaoFactory;
import dao.admin.AdminDao;

public class FormConAdmin {
	private AdminDao adminDao;
	private beans.Admin admin;

	private boolean valide = false;
	private String msgErreur = "combinaison pseudo mdp invalide";

	public FormConAdmin(){
		super();
		System.out.println("4");
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.adminDao = daoFactory.getAdminDao();
	}

	public boolean isValide() {
		return valide;
	}

	public void controleForm(HttpServletRequest request) {
		String pseudo = request.getParameter("pseudoAdmin");
		String mdp = request.getParameter("mdpAdmin");

		List<beans.Admin> admins = adminDao.adminCon(pseudo, mdp);

		if (pseudo != null && !pseudo.equals("") && mdp != null && !mdp.equals("")) {
			if (admins.size() == 1) {
				if (admins.get(0).getNom().equals(pseudo) && mdp.equals(admins.get(0).getMdp())) {
					valide = true;
					this.admin = admins.get(0);
				} else {
					valide = false;
				}
			} else {
				valide = false;
			}
		} else {
			valide = false;
		}
		System.out.println("5");
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

	public beans.Admin getAdmin() {
		return admin;
	}

	public void setAdmin(beans.Admin admin) {
		this.admin = admin;
	}

}
