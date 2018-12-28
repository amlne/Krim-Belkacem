package dao.admin;

import java.util.List;

import beans.Admin;

public interface AdminDao {
	List<Admin> adminCon(String pseudo , String mdp);
}
