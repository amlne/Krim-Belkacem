package dao.prof;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import beans.Prof;
import dao.DaoFactory;


public class ProfDaoImpl implements ProfDao {
    private DaoFactory daoFactory;
    public ProfDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    @Override
    public List<Prof> profCon(String pseudo, String mdp) {
        List<Prof> profs = new ArrayList<Prof>();
        String sql = "SELECT * FROM prof WHERE nom =\""+pseudo+"\" AND mdp_prf = \""+mdp+"\" ;";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	String id_prof = resultat.getString("id_prf");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String mdp_prf = resultat.getString("mdp_prf");
                Prof prof = new Prof();
                prof.setId_prf(id_prof);
                prof.setNom(nom);
                prof.setPrenom(prenom);
                prof.setMdp(mdp_prf);
                profs.add(prof);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profs;
    }
	@Override
	public List<Prof> listerProf(String sql) {
		List<Prof> profs = new ArrayList<Prof>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	String id_prof = resultat.getString("id_prf");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String email = resultat.getString("email");
                Prof prof = new Prof();
                prof.setId_prf(id_prof);
                prof.setNom(nom);
                prof.setPrenom(prenom);
                prof.setEmail(email);
                profs.add(prof);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profs;
	}
}