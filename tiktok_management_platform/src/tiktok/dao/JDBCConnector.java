package tiktok.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnector {
	// 占쏙옙트占쏙옙호占쏙옙 占쏙옙占쏙옙 : ex) 占쏙옙占싼댐옙 - (占쏙옙占싻곤옙 21, 占쏙옙占쏙옙占쏙옙 20, ...)
	// 占쏙옙표占쏙옙화 占쏙옙占쏙옙占쏙옙 URL, 占쏙옙 占쏙옙화占쏙옙호占쏙옙 占쏙옙트占쏙옙호
	
	/// 占썩본 占쏙옙占쏙옙 - base configuration
	private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver"; // 占쌨모리울옙 占싸듸옙占쏙옙 占쏙옙 占쌍댐옙.
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/tiktok_management?serverTimezone=UTC&useUnicode=yes&characterEncoding=UTF-8";
	private static final String ID = "root";
	private static final String PWD = "root"; //root,1234占쌩울옙占쏙옙 占쏙옙占� 占쏙옙占쏙옙
	private static Connection con;
	
	public static Connection getCon() {
		try {
			Class.forName(DRIVER_PATH);
			System.out.println("�젙�긽�쟻�쑝濡� JDBC Driver媛� Road �릺�뿀�뒿�땲�떎..");
			con = DriverManager.getConnection(URL, ID, PWD); // 占쏙옙占쏙옙 처占쏙옙占쏙옙 占쌔억옙 占쏙옙占쏙옙占� 占쏙옙 占쌍댐옙.
			System.out.println("�뿰寃곗씠 �옒 �릺�뿀�뒿�땲�떎.");
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
			ResultSet rs = stmt.executeQuery(sql);  // sql 占쏙옙占쏙옙占� 占쏙옙占쏙옙占쏙옙占쏙옙 占쌨소드가 占쌕몌옙占쏙옙.
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
