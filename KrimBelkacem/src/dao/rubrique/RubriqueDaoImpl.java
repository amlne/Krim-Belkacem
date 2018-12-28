package dao.rubrique;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Rubrique;
import dao.DaoFactory;

public class RubriqueDaoImpl implements RubriqueDao {

	private DaoFactory daoFactory;
	private String erreur;
	private String key;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public RubriqueDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	@Override
	public boolean ajoutRubrique(beans.Rubrique rubrique) {
		
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO rubrique (titre,categorie,date_ajout,contenu,url_img) VALUES (?,?,NOW(),?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, rubrique.getTitre());
            preparedStatement.setString(2, rubrique.getCategorie());
            preparedStatement.setString(3, rubrique.getContenu());
            preparedStatement.setString(4, rubrique.getUrl());
            preparedStatement.executeUpdate();
            ResultSet resultat = preparedStatement.getGeneratedKeys();
            String key = "";
            if(resultat.next()){
            	key = resultat.getString(1);
            	System.out.println("le resultat : "+key);
            }
            this.key =  key;
            return true;
        } catch (SQLException e) {
        	System.out.println("catch rubriqueDaoImpl ");
            e.printStackTrace();
            return false;
        }	
	}

	public List<Rubrique> listerRubrique(String sql){
		System.out.println("*****  ");
		System.out.println("  sql  ==  "+sql);
		List<Rubrique> rubriques = new ArrayList<Rubrique>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
		
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);

            while (resultat.next()) {
            	String id_rub = resultat.getString("id_rub");
                String titre = resultat.getString("titre");
                String categorie = resultat.getString("categorie");
                String date = resultat.getString("date_ajout");
                String url = resultat.getString("url_img");
                String contenu = resultat.getString("contenu");
                Rubrique rubrique = new Rubrique();
            	
            	rubrique.setTitre(titre);
            	rubrique.setCategorie(categorie);
            	rubrique.setDate_ajout(date);
            	rubrique.setUrl(url+id_rub);
            	rubrique.setContenu(contenu);
            	rubrique.setId_rub(id_rub);
            	rubriques.add(rubrique);
            }
        } catch (SQLException e) {
        	System.out.println("catch rubrique doa");
            e.printStackTrace();
        }
        return rubriques;
	}
	@Override
	public boolean suppRubrique(String id_rub) {
		boolean valideSupp = false;
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
        try{
        	connexion = daoFactory.getConnection();
        	preparedStatement = connexion.prepareStatement("DELETE FROM rubrique WHERE id_rub = ?;");
        	preparedStatement.setString(1, id_rub);
        	preparedStatement.executeUpdate();
        	valideSupp = true;
		} catch (Exception e) {
			valideSupp = false;
		}
		
		return valideSupp;
	}
}
