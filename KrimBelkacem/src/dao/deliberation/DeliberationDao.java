package dao.deliberation;

import java.util.List;

public interface DeliberationDao {
	public boolean ajout(beans.Deliberation deliberation,int trimestre);
	public List<beans.Deliberation> listerDeliberation(String sql);
	public boolean suppDeliberation(String id_del,String trimestre);
	public boolean suppDeliberation(String id_del);
}
