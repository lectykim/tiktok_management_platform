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
	private static final String PWD = "root"; //root,1234�߿��� ��� ����
	private static Connection con;
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER_PATH);
			System.out.println("���������� JDBC Driver�� Road �Ǿ����ϴ�.");
			con = DriverManager.getConnection(URL, ID, PWD); // ���� ó���� �ؾ� ����� �� �ִ�.
			System.out.println("������ �� �Ǿ����ϴ�.");
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
			String sql = "select * from user";
			ResultSet rs = stmt.executeQuery(sql);  // sql ��ɾ��� �������� �޼ҵ尡 �ٸ���.
			while (rs.next()) {
				System.out.print(rs.getString("nickname") + "\t");
				System.out.print(rs.getString("password") + "\t");
				System.out.println(rs.getInt("age")+"\t");
				System.out.println(rs.getInt("star")+"\t");
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
