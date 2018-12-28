package dao.planning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Planning;
import dao.DaoFactory;

public class PlanningDaoImpl implements PlanningDao {
	private DaoFactory daoFactory;
	private String erreur;

	public PlanningDaoImpl(DaoFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}

	@Override
	public boolean ajoutPlanning(Planning planning ,int trimestre) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;
             
        List<Planning> plannings = this.listerPlanning("Select * from plannings where id_cls = \""+planning.getClasse().getId_cls()+"\";");
        if(plannings != null){
        	if(plannings.size() == 0){
        		try {
        			connexion = daoFactory.getConnection();
        			if(trimestre == 1){
        	        	String sql1 = "INSERT INTO plannings (id_cls,trimestre1) VALUES (?,?);";
        	        	preparedStatement = connexion.prepareStatement(sql1);
                        preparedStatement.setString(1, planning.getClasse().getId_cls());
                        preparedStatement.setString(2, planning.getTrimestre1());
        	        }
        	        if(trimestre == 2){
        	        	String sql2 = "INSERT INTO plannings (id_cls,trimestre2) VALUES (?,?);";
        	        	preparedStatement = connexion.prepareStatement(sql2);
                        preparedStatement.setString(1, planning.getClasse().getId_cls());
                        preparedStatement.setString(2, planning.getTrimestre2());
        	        }
        	        if(trimestre == 3){
        	        	String sql3 = "INSERT INTO plannings (id_cls,trimestre3) VALUES (?,?);";
        	        	preparedStatement = connexion.prepareStatement(sql3);
                        preparedStatement.setString(1, planning.getClasse().getId_cls());
                        preparedStatement.setString(2, planning.getTrimestre3());
        	        }
        	        if(trimestre == 4){
        	        	String sql4 = "INSERT INTO plannings (id_cls,annuel) VALUES (?,?);";
        	        	preparedStatement = connexion.prepareStatement(sql4);
                        preparedStatement.setString(1, planning.getClasse().getId_cls());
                        preparedStatement.setString(2, planning.getAnnuel());
        	        }
        		    preparedStatement.executeUpdate();
                    return true;
                } catch (SQLException e) {
                	System.out.println("catch ");
                    e.printStackTrace();
                    return false;
                }	
        	}else
        		if(plannings.size() == 1){
        			try {
            			connexion = daoFactory.getConnection();
            			if(trimestre == 1){
            	        	String sql1 = "UPDATE plannings SET trimestre1 = ? WHERE id_plan = ?";
            	        	preparedStatement = connexion.prepareStatement(sql1);
                            preparedStatement.setString(1, planning.getTrimestre1());
                            preparedStatement.setString(2, plannings.get(0).getId_planning());
            	        }
            	        if(trimestre == 2){
            	        	String sql2 = "UPDATE plannings SET trimestre2 = ? WHERE id_plan = ?";
            	        	preparedStatement = connexion.prepareStatement(sql2);
                            preparedStatement.setString(1, planning.getTrimestre2());
                            preparedStatement.setString(2, plannings.get(0).getId_planning());
            	        }
            	        if(trimestre == 3){
            	        	String sql3 = "UPDATE plannings SET trimestre3 = ? WHERE id_plan = ?";
            	        	preparedStatement = connexion.prepareStatement(sql3);
                            preparedStatement.setString(1, planning.getTrimestre3());
                            preparedStatement.setString(2, plannings.get(0).getId_planning());
            	        }
            	        if(trimestre == 4){
            	        	String sql4 = "UPDATE plannings SET annuel = ? WHERE id_plan = ?";
            	        	preparedStatement = connexion.prepareStatement(sql4);
                            preparedStatement.setString(1, planning.getAnnuel());
                            preparedStatement.setString(2, plannings.get(0).getId_planning());
            	        }
            		    preparedStatement.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                    	System.out.println("catch ");
                        e.printStackTrace();
                        return false;
                    }	
        		}else{
        			return false;
        		}
        }
        return false;

	}

	@Override
	public List<Planning> listerPlanning(String sql) {
		List<Planning> plannings = new ArrayList<Planning>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(sql);

			while (resultat.next()) {
				String id_plan = resultat.getString("id_plan");
				String id_cls = resultat.getString("id_cls");
				String trimestre1 = resultat.getString("trimestre1");
				String trimestre2 = resultat.getString("trimestre2");
				String trimestre3 = resultat.getString("trimestre3");
				String annuel = resultat.getString("annuel");
				Planning planning = new Planning();

				planning.setId_planning(id_plan);
				planning.getClasse().setId_cls(id_cls);
				planning.setTrimestre1(trimestre1);
				planning.setTrimestre2(trimestre2);
				planning.setTrimestre3(trimestre3);
				planning.setAnnuel(annuel);

				plannings.add(planning);
			}
		} catch (SQLException e) {
			System.out.println("catch planning doa");
			e.printStackTrace();
		}
		return plannings;
	}

	@Override
	public boolean suppPlanning(String id_plan) {
		boolean valideSupp = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM plannings WHERE id_plan = ?;");
			preparedStatement.setString(1, id_plan);
			preparedStatement.executeUpdate();
			valideSupp = true;
		} catch (Exception e) {
			valideSupp = false;
		}

		return valideSupp;
	}
	public boolean suppPlanning(String id_plan,String trimestre) {
		boolean valideSupp = false;
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		try {
			connexion = daoFactory.getConnection();
			if(trimestre == null){
				return false;
			}else
				if(trimestre.equals("1")){
					preparedStatement = connexion.prepareStatement("UPDATE plannings SET trimestre1 = null WHERE id_plan = ?;");
					preparedStatement.setString(1, id_plan);
				}else
					if(trimestre.equals("2")){
						preparedStatement = connexion.prepareStatement("UPDATE plannings SET trimestre2 = null WHERE id_plan = ?;");
						preparedStatement.setString(1, id_plan);
					}else
						if(trimestre.equals("3")){
							preparedStatement = connexion.prepareStatement("UPDATE plannings SET trimestre3 = null WHERE id_plan = ?;");
							preparedStatement.setString(1, id_plan);
						}else
							if(trimestre.equals("annuel")){
								preparedStatement = connexion.prepareStatement("UPDATE plannings SET annuel = null WHERE id_plan = ?;");
								preparedStatement.setString(1, id_plan);
							}else{
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
