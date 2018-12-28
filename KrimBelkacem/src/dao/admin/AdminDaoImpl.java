package dao.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Admin;
import dao.DaoFactory;

public class AdminDaoImpl implements AdminDao {
	
	private DaoFactory daoFactory;
	
	public AdminDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
		System.out.println("2");
	}

	@Override
	public List<Admin> adminCon(String pseudo, String mdp) {
		List<Admin> admins = new ArrayList<Admin>();
        String sql = "SELECT * FROM admin WHERE nom =\""+pseudo+"\" AND mdp_admin = \""+mdp+"\" ;";
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;
        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery(sql);
            while (resultat.next()) {
            	int id_admin = resultat.getInt("id_admin");
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                String mdp_admin = resultat.getString("mdp_admin");
                Admin admin = new Admin();
                admin.setId_admin(id_admin);
                admin.setNom(nom);
                admin.setPrenom(prenom);
                admin.setMdp(mdp_admin);
                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("3");
        return admins;
	}

}
