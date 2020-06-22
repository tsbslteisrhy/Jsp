package kr.co.farmstory1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	// 배포용
//	private static final String HOST = "jdbc:mysql://yhzin.com:3306/rhj?";
//	private static final String USER = "root";
//	private static final String PASS = "0203hj";
	
	// 개발용
	private static final String HOST = "jdbc:mysql://192.168.44.46:3306/rhj";
	private static final String USER = "rhj";
	private static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2단계
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		return conn;
	}
}
