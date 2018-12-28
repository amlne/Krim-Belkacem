package dao.preinscription;

import java.util.List;

import beans.FormPreinscription;
import beans.Preinscription;

public interface PreinscriptionDao {
	public List<Preinscription> listerPreinscription(String sql);
	public boolean ajoutFormPreinscription(FormPreinscription formPreinscription);
	public boolean ouvrirPreinscription();
	public boolean fermerPreinscription();
}
