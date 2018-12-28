package dao.planning;

import java.util.List;

public interface PlanningDao {
	public boolean ajoutPlanning(beans.Planning planning ,int trimestre);
	public List<beans.Planning> listerPlanning(String sql);
	public boolean suppPlanning(String id_plan);
	public boolean suppPlanning(String id_plan,String trimestre);
}
