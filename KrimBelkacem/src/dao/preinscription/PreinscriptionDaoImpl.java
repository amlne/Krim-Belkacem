package dao.preinscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.FormPreinscription;
import beans.Preinscription;
import dao.DaoFactory;

public class PreinscriptionDaoImpl implements PreinscriptionDao {

	private DaoFactory daoFactory;
	private String erreur;

	public PreinscriptionDaoImpl(DaoFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public List<Preinscription> listerPreinscription(String sql) {

		List<Preinscription> preinscriptions = new ArrayList<Preinscription>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(sql);

			while (resultat.next()) {
				int id = resultat.getInt("id");
				int ouvert = resultat.getInt("ouvert");

				// reste prof
				Preinscription preinscription = new Preinscription();

				preinscription.setId(id);
				preinscription.setOuvert(ouvert);
				preinscriptions.add(preinscription);

			}
		} catch (SQLException e) {
			System.out.println("catch xxx doc doa");
			e.printStackTrace();
		}
		return preinscriptions;
	}

	public boolean ajoutFormPreinscription(FormPreinscription formPreinscription) {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO preinscription (nom,prenom,date_nais,lieu_nais,adresse) VALUES(?,?,?,?,?);");
			preparedStatement.setString(1, formPreinscription.getNom());
			preparedStatement.setString(2, formPreinscription.getPrenom());
			preparedStatement.setString(3, formPreinscription.getDate_nais());
			preparedStatement.setString(4, formPreinscription.getLieu_nais());
			preparedStatement.setString(5, formPreinscription.getAdresse());
			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("catch ");
			e.printStackTrace();
			return false;
		}
	}

	public boolean ouvrirPreinscription() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("update etat_preinscription set ouvert = 1 where id = 1;");
			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("catch ");
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean fermerPreinscription() {

		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("update etat_preinscription set ouvert = 0 where id = 1;");
			preparedStatement.executeUpdate();

			return true;

		} catch (SQLException e) {
			System.out.println("catch ");
			e.printStackTrace();
			return false;
		}

	}


}
