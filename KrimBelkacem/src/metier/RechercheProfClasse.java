package metier;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import beans.Eleve;
import beans.Prof;
import dao.DaoFactory;
import dao.prof.ProfDao;

public class RechercheProfClasse {
	private String id_classe;
	private ProfDao profDao;
	
	public RechercheProfClasse(Eleve eleve){
		this.id_classe = eleve.getClasse();
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.profDao = daoFactory.getProfDao();
	}
	
	public List<Prof> listerProf(){
		
		String sql = "select * from prof_classe, prof where prof_classe.id_classe = \""+id_classe+"\" AND prof_classe.id_prof = prof.id_prf ;";
		System.out.println(sql);
		return profDao.listerProf(sql);
	}

}
