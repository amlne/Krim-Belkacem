package dao.eleve;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Eleve;
import dao.DaoFactory;

public class EleveDaoImpl implements EleveDao {
	
	private DaoFactory daoFactory;
	
	public EleveDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Eleve> eleveCon(String pseudo, String mdp) {
		// TODO Auto-generated method stub
		List<Eleve> eleves = new ArrayList<Eleve>();
        String sql = "SELECT * FROM eleve WHERE nom =\""+pseudo+"\" AND mdp_elv = \""+mdp+"\" ;";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	int id_elv = resultat.getInt("id_elv");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String mdp_elv = resultat.getString("mdp_elv");
                String classe = resultat.getString("classe");
                String filiere = resultat.getString("filiere");
                Eleve eleve = new Eleve();
                eleve.setId_elv(id_elv);
                eleve.setNom(nom);
                eleve.setPrenom(prenom);
                eleve.setMdp(mdp_elv);
                eleve.setClasse(classe);
                eleve.setFiliere(filiere);
                eleves.add(eleve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eleves;
	}

}
