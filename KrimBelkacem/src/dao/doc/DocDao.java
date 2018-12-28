package dao.doc;

import java.util.List;

import beans.NombreDoc;
import beans.Prof;

public interface DocDao {
	public String ajoutDoc(beans.Doc doc);
	public List<beans.Doc> listerDoc(String sql);
	public boolean suppDoc(String id_doc);
	public List<beans.Matiere> listerMatiere(String sql);
	public NombreDoc nombreDoc(Prof prof);
	public int numDocRequest(String sql);
}
