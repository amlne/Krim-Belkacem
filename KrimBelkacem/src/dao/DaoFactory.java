package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.admin.AdminDao;
import dao.admin.AdminDaoImpl;
import dao.deliberation.DeliberationDao;
import dao.deliberation.DeliberationDaoImpl;
import dao.doc.DocDao;
import dao.doc.DocDaoImpl;
import dao.eleve.EleveDao;
import dao.eleve.EleveDaoImpl;
import dao.planning.PlanningDao;
import dao.planning.PlanningDaoImpl;
import dao.preinscription.PreinscriptionDao;
import dao.preinscription.PreinscriptionDaoImpl;
import dao.prof.ProfDao;
import dao.prof.ProfDaoImpl;
import dao.rubrique.RubriqueDao;
import dao.rubrique.RubriqueDaoImpl;

public class DaoFactory {
	private String username;
	private String url;
	private String password;
	
	public DaoFactory(String url,String username,String password){
		this.url = url;
		this.username = username;
		this.password = password;
	}
	
	public static DaoFactory getInstance(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
		}
		DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/projetLicence","root","");
		return instance;
	}
	
	public  Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url,username,password);
	}
	public ProfDao getProfDao(){
		return new ProfDaoImpl(this);
	}
	public DocDao getDocDao(){
		return new DocDaoImpl(this);
	}
	public EleveDao getEleveDao(){
		return new EleveDaoImpl(this);
	}
	public AdminDao getAdminDao(){
		return new AdminDaoImpl(this);
	}
	public PlanningDao getPlanningDao(){
		return new PlanningDaoImpl(this);
	}
	public RubriqueDao getRubriqueDao(){
		return new RubriqueDaoImpl(this);
	}
	public DeliberationDao getDeliberationDao(){
		return new DeliberationDaoImpl(this);
	}
	public PreinscriptionDao getPreinscriptionDao(){
		return new PreinscriptionDaoImpl(this);
	}
}
