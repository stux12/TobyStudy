package step1;

import java.sql.Connection;
import java.sql.SQLException;

import step1.UserDao.getConnection2;

public class NUserDao extends UserDao implements getConnection2 {
	
	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Connection getConnection2() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
}
