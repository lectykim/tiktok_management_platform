package tiktok.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	
	// JDBC Connector - base configuration
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver"; // 최신 버전은 cj를 사용함.
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tiktok_management?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	private static final String ID = "root";
	private static final String PWD = "root"; //root,1234중 자신의 환경에 맞는 비밀번호를 활용
	private static Connection con;
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER_PATH);
			
			con = DriverManager.getConnection(URL, ID, PWD); // JDBC와 연결합니다.
			
		} catch (ClassNotFoundException e) { 
			e.printStackTrace();
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		return con;
	}
	
	public static void resultSetTest() {
		try {
			Statement stmt = con.createStatement();
			String sql = "select * from movie";
			ResultSet rs = stmt.executeQuery(sql);  // sql문 활용
			while (rs.next()) {
				System.out.print(rs.getInt("movie_id") + "\t");
				System.out.print(rs.getInt("user") + "\t");
				System.out.println(rs.getString("len")+"\t");
				System.out.println(rs.getInt("song")+"\t");
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		getCon();
		resultSetTest();
	}

}
