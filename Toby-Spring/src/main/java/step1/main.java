package step1;

import java.sql.Connection;
import java.sql.SQLException;

public class main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		UserDao dao = new UserDao() {
			
			@Override
			public Connection getConnection() throws ClassNotFoundException, SQLException {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		User user = new User();
		user.setId("stux12");
		user.setName("김진환");
		user.setPassword("stux12");
		
		dao.add(user);
		
		System.out.println(user.getId() + " 등록 성공");
		
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		System.out.println(user2.getPassword());
		System.out.println(user2.getId() + " 조회 성공");
		
		
	}
}
