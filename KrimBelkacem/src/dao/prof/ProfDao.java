package dao.prof;

import java.util.List;

import beans.Prof;

public interface ProfDao {
	List<Prof> profCon(String pseudo , String mdp);
	List<Prof> listerProf(String sql);
}