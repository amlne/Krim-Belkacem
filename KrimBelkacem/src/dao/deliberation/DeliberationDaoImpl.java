package dao.deliberation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Deliberation;
import dao.DaoFactory;

public class DeliberationDaoImpl implements DeliberationDao {

	private DaoFactory daoFactory;
	private String erreur;

	public DeliberationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean ajout(Deliberation deliberation, int trimestre) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		List<Deliberation> deliberations = this.listerDeliberation(
				"Select * from deliberation where id_cls = \"" + deliberation.getClasse().getId_cls() + "\";");

		if (deliberations != null) {
			if (deliberations.size() == 0) {
				try {
					connexion = daoFactory.getConnection();
					if (trimestre == 1) {
						String sql1 = "INSERT INTO deliberation (id_cls,trimestre1) VALUES (?,?);";
						preparedStatement = connexion.prepareStatement(sql1);
						preparedStatement.setString(1, deliberation.getClasse().getId_cls());
						preparedStatement.setString(2, deliberation.getTrimestre1());
					}
					if (trimestre == 2) {
						String sql2 = "INSERT INTO deliberation (id_cls,trimestre2) VALUES (?,?);";
						preparedStatement = connexion.prepareStatement(sql2);
						preparedStatement.setString(1, deliberation.getClasse().getId_cls());
						preparedStatement.setString(2, deliberation.getTrimestre2());
					}
					if (trimestre == 3) {
						String sql3 = "INSERT INTO deliberation (id_cls,trimestre3) VALUES (?,?);";
						preparedStatement = connexion.prepareStatement(sql3);
						preparedStatement.setString(1, deliberation.getClasse().getId_cls());
						preparedStatement.setString(2, deliberation.getTrimestre3());
					}
					preparedStatement.executeUpdate();
					return true;
				} catch (SQLException e) {
					System.out.println("catch ");
					e.printStackTrace();
					return false;
				}
			} else if (deliberations.size() == 1) {
				try {
					connexion = daoFactory.getConnection();
					if (trimestre == 1) {
						String sql1 = "UPDATE deliberation SET trimestre1 = ? WHERE id_del = ?;";
						preparedStatement = connexion.prepareStatement(sql1);
						preparedStatement.setString(1, deliberation.getTrimestre1());
						preparedStatement.setString(2, deliberations.get(0).getId_del());
					}
					if (trimestre == 2) {
						String sql2 = "UPDATE deliberation SET trimestre2 = ? WHERE id_del = ?;";
						preparedStatement = connexion.prepareStatement(sql2);
						preparedStatement.setString(1, deliberation.getTrimestre2());
						preparedStatement.setString(2, deliberations.get(0).getId_del());
					}
					if (trimestre == 3) {
						String sql3 = "UPDATE deliberation SET trimestre3 = ? WHERE id_del = ?;";
						preparedStatement = connexion.prepareStatement(sql3);
						preparedStatement.setString(1, deliberation.getTrimestre3());
						preparedStatement.setString(2, deliberations.get(0).getId_del());
					}
					preparedStatement.executeUpdate();
					return true;
				} catch (SQLException e) {
					System.out.println("catch ");
					e.printStackTrace();
					return false;
				}
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public List<Deliberation> listerDeliberation(String sql) {
		List<Deliberation> deliberations = new ArrayList<Deliberation>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(sql);

			while (resultat.next()) {
				String id_del = resultat.getString("id_del");
				String id_cls = resultat.getString("id_cls");
				String trimestre1 = resultat.getString("trimestre1");
				String trimestre2 = resultat.getString("trimestre2");
				String trimestre3 = resultat.getString("trimestre3");
				Deliberation deliberation = new Deliberation();

				deliberation.setId_del(id_del);
				deliberation.getClasse().setId_cls(id_cls);
				deliberation.setTrimestre1(trimestre1);
				deliberation.setTrimestre2(trimestre2);
				deliberation.setTrimestre3(trimestre3);

				deliberations.add(deliberation);
			}
		} catch (SQLException e) {
			System.out.println("catch planning doa");
			e.printStackTrace();
		}
		return deliberations;
	}

	@Override
	public boolean suppDeliberation(String id_del) {
		boolean valideSupp = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM deliberation WHERE id_del = ?;");
			preparedStatement.setString(1, id_del);
			preparedStatement.executeUpdate();
			valideSupp = true;
		} catch (Exception e) {
			valideSupp = false;
		}

		return valideSupp;
	}

	@Override
	public boolean suppDeliberation(String id_del, String trimestre) {
		boolean valideSupp = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			if (trimestre == null) {
				return false;
			} else if (trimestre.equals("1")) {
				preparedStatement = connexion
						.prepareStatement("UPDATE deliberation SET trimestre1 = null WHERE id_del = ?;");
				preparedStatement.setString(1, id_del);
			} else if (trimestre.equals("2")) {
				preparedStatement = connexion
						.prepareStatement("UPDATE deliberation SET trimestre2 = null WHERE id_del = ?;");
				preparedStatement.setString(1, id_del);
			} else if (trimestre.equals("3")) {
				preparedStatement = connexion
						.prepareStatement("UPDATE deliberation SET trimestre3 = null WHERE id_del = ?;");
				preparedStatement.setString(1, id_del);
			} else {
				return false;
			}
			preparedStatement.executeUpdate();
			valideSupp = true;
		} catch (Exception e) {
			valideSupp = false;
		}

		return valideSupp;
	}

}
