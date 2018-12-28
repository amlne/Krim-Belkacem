package dao.doc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Doc;
import beans.Matiere;
import beans.NombreDoc;
import dao.DaoFactory;

public class DocDaoImpl implements DocDao {
	private DaoFactory daoFactory;
	private String erreur;
	
	public DocDaoImpl(DaoFactory daoFactory){
		this.daoFactory = daoFactory;
	}
	
	@Override
	public String ajoutDoc(Doc doc) {
		// TODO Auto-generated method stub
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO doc (type,titre,annee,id_fil,id_mat,id_prof,date_up,url,nom_fichier) VALUES(?,?,?,?,?,?,NOW(),?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, doc.getType());
            preparedStatement.setString(2, doc.getTitre());
            preparedStatement.setString(3, doc.getAnnee());
            preparedStatement.setString(4, doc.getFiliere());
            preparedStatement.setString(5, doc.getId_mat());
            preparedStatement.setString(6, doc.getProf().getId_prf());
            preparedStatement.setString(7, doc.getUrl());
            preparedStatement.setString(8, doc.getNom_fichier());
            preparedStatement.executeUpdate();
            ResultSet resultat = preparedStatement.getGeneratedKeys();
            String key = "";
            if(resultat.next()){
            	key = resultat.getString(1);
            	System.out.println("le resultat : "+key);
            }
            return key;
            
        } catch (SQLException e) {
        	System.out.println("catch ");
            e.printStackTrace();
            return "";
        }	
		
	}

	@Override
	public List<Doc> listerDoc(String sql) {
		// TODO Auto-generated method stub
		
		List<Doc> docs = new ArrayList<Doc>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);

            while (resultat.next()) {
            	int id_doc = resultat.getInt("doc.id_doc");
                String titre = resultat.getString("doc.titre");
                String type = resultat.getString("doc.type");
                String date = resultat.getString("doc.date_up");
                String annee = resultat.getString("doc.annee");
                String url = resultat.getString("doc.url");
                String nom_fichier = resultat.getString("doc.nom_fichier");
                String filiere = resultat.getString("doc.id_fil");
                String id_prof = resultat.getString("doc.id_prof");
                //String matiere = resultat.getString("matiere.intitulé");
                
                //reste prof
                Doc doc = new Doc();
            	
            	doc.setId_doc(id_doc);
            	doc.setTitre(titre);
            	doc.setType(type);
            	doc.setDate(date);
            	doc.setAnnee(annee);;
            	doc.setUrl(url+"/"+id_doc+"/"+nom_fichier);
            	doc.setFiliere(filiere);
            	doc.getProf().setId_prf(id_prof);
            	doc.setNom_fichier(nom_fichier);
            	//doc.setMatiere(matiere);
                docs.add(doc);
            }
        } catch (SQLException e) {
        	System.out.println("catch xxx doc doa");
            e.printStackTrace();
        }
        return docs;
	}

	@Override
	public boolean suppDoc(String id_doc) {
		// TODO Auto-generated method stub
		boolean valideSupp = false;
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
        	connexion = daoFactory.getConnection();
        	preparedStatement = connexion.prepareStatement("DELETE FROM doc WHERE id_doc = ?;");
        	preparedStatement.setString(1, id_doc);
        	preparedStatement.executeUpdate();
        	valideSupp = true;
		} catch (Exception e) {
			valideSupp = false;
		}
		
		return valideSupp;
	}

	public List<beans.Matiere> listerMatiere(String sql){
		List<Matiere> matieres = new ArrayList<Matiere>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);

            while (resultat.next()) {
            	String id_mat = resultat.getString("id_mat");
                String intitule = resultat.getString("intitulé");
                String filiere = resultat.getString("filiere");
                String annee = resultat.getString("annee");
            	Matiere mat = new Matiere();
            	
            	mat.setId_mat(id_mat);
            	mat.setAnnee(annee);
            	mat.setFiliere(filiere);
            	mat.setIntitule(intitule);
            	
            	matieres.add(mat);
            }
        } catch (SQLException e) {
        	System.out.println("catch doc doa");
            e.printStackTrace();
        }
        return matieres;
	}
	
	public beans.NombreDoc nombreDoc(beans.Prof prof){
		beans.NombreDoc nbrDoc = new beans.NombreDoc();
		
		String sqlTotal = "SELECT COUNT(id_doc) FROM doc WHERE id_prof = \""+prof.getId_prf()+"\";";
		String sqlcour = "SELECT COUNT(id_doc) FROM doc WHERE id_prof = \""+prof.getId_prf()+"\" AND type = \"cour\" ;";
		String sqlexam = "SELECT COUNT(id_doc) FROM doc WHERE id_prof = \""+prof.getId_prf()+"\" AND type = \"exam\" ;";

		Connection connexion = null;
        Statement statement = null;
        Statement statement2 = null;
        Statement statement3 = null;
        ResultSet resultat = null;
        ResultSet resultat2 = null;
        ResultSet resultat3 = null;
        
        try{
        	connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            statement2 = connexion.createStatement();
            statement3 = connexion.createStatement();
            resultat = statement.executeQuery(sqlTotal);
            String nbrTotal = "0";
            String nbrCour = "0";
            String nbrExam = "0";
            if(resultat.next()){
            	nbrTotal = resultat.getString(1);
            }
            nbrDoc.setNbrDoc(nbrTotal);
            
            resultat2 = statement2.executeQuery(sqlcour);
            if(resultat2.next()){
            	nbrCour = resultat2.getString(1);
            }
            nbrDoc.setNbrCour(nbrCour);
            
            resultat3 = statement3.executeQuery(sqlexam);
            if(resultat3.next()){
            	nbrExam = resultat3.getString(1);
            }
            nbrDoc.setNbrExam(nbrExam);
        	
        }catch(Exception e){
        	System.out.println("catch nombreDoc");
        	e.printStackTrace();
        }
		
		return nbrDoc;
	}
	
	public int numDocRequest(String sql){
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);

            if(resultat.next()){
            	return resultat.getInt(1);
            }else{
            	return 1;
            }
        } catch (SQLException e) {
        	System.out.println("catch xxx doc doa");
            e.printStackTrace();
            return 1;
        }
	}
}
