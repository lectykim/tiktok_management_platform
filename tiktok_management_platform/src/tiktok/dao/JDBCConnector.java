package tiktok.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	// ��Ʈ��ȣ�� ���� : ex) ���Ѵ� - (���а� 21, ������ 20, ...)
	// ��ǥ��ȭ ������ URL, �� ��ȭ��ȣ�� ��Ʈ��ȣ
	
	/// �⺻ ���� - base configuration
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver"; // �޸𸮿� �ε��� �� �ִ�.
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tiktok_management?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	private static final String ID = "root";
	private static final String PWD = "1234"; //root,1234�߿��� ��� ����
	private static Connection con;
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER_PATH);
			System.out.println("정상적으로 JDBC Driver가 Road 되었습니다..");
			con = DriverManager.getConnection(URL, ID, PWD); // ���� ó���� �ؾ� ����� �� �ִ�.
			System.out.println("연결이 잘 되었습니다.");
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
			ResultSet rs = stmt.executeQuery(sql);  // sql ��ɾ��� �������� �޼ҵ尡 �ٸ���.
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
