package dao.eleve;

import java.util.List;

import beans.Eleve;

public interface EleveDao {
	List<Eleve> eleveCon(String pseudo , String mdp);
}
