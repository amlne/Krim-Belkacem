package dao.rubrique;

import java.util.List;

import beans.Rubrique;

public interface RubriqueDao {
	public boolean ajoutRubrique(beans.Rubrique rubrique);
	public List<Rubrique> listerRubrique(String sql);
	public boolean suppRubrique(String id_rub);
	public String getKey();
}
