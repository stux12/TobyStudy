package step1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//JDBC를 이용한 등록과 조회 기능이 있는 UserDao 클래스
// 2차 수정 전
//public class UserDao {

// 2차 수정 후 
public abstract class UserDao {

	public void add(User user) throws ClassNotFoundException, SQLException{
		// 1차 수정 전
//		Class.forName("com.mysql.jdbc.Driver"); //Mysql JDBC 드라이버 로드
//		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "root"); // localhost/toby프로젝트에 root root로 연결
		
		// 1차 수정 후
		Connection c = getConnection();
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?, ?, ?)"); // SQL 구문 실행 역할
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate(); // SELECT를 제외한 나머지 UPDATE, DELETE, INSERT 실행
		
		// 열린것들 꼭 닫아줘야 함
		ps.close();
		c.close();
	}
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		// 1차 수정 전
//		Class.forName("com.mysql.jdbc.Driver"); //Mysql JDBC 드라이버 로드
//		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "root"); // localhost/toby프로젝트에 root root로 연결
		
		// 1차 수정 후
		Connection c = getConnection();
		
		PreparedStatement ps = c.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery(); // SELECT 실행
		rs.next(); //조회된 정보에서 커서를 이동시켜 한 줄을 읽어 옴
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	
	/*
	 위의 문제점 1차
	 1. DB 연결 Connection의 중복
	 2. 쿼리문 변경시 일일이 쿼리문을 다 변경해주어야 한다.
	 3. 새로운 칼럼 추가시 일일이 다 추가해주어야 한다.
	 4. 예외상황에 대한 처리가 없다. 
	 */
	
	
	// 중복 코드의 메소드 추출 -> 리팩토링 -> 메소드 추출 기법
	// 리팩토링 : 외부의 동작방식에는 변화 없이 내부 구조를 변경해서 재구성
	
	// 2차 수정 전
//	private Connection getConnection() throws ClassNotFoundException, SQLException{
//		Class.forName("com.mysql.jdbc.Driver");
//		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "root");
//		return c;
//	}
	// 2차 수정 후
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
	interface getConnection2{
		public abstract Connection getConnection2() throws ClassNotFoundException, SQLException;
		public static Connection getConnection3() throws ClassNotFoundException, SQLException {
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/toby", "root", "root");
			return c;
		}
	}
	
	/*
	 위의 문제 점 2차
	 1. 만약 두개 이상의 DB를 사용 할 시 각각의 connection이 필요함
	 */
	
	// 상속을 통한 확장
	/*
	 상속에는 abstract, interface 두개가 존재
	 abstract : extends로 상속 받으며, 조금 더 class의 성격을 지니고 있어 선언만 가능
	 interface : implements로 상속 받으며, 다중 상속 가능, 선언 및 구현 가능
	 */
	
	
}
