package kr.co.jboard1.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	// DB정보 - 배포용
//	public static final String HOST = "jdbc:mysql://yhzin.com:3306/rhj?useUnicode=true&characterEncoding=utf8";
//	public static final String USER = "root";
//	public static final String PASS = "0203hj";
	
	// DB정보 - 개발용
	public static final String HOST = "jdbc:mysql://192.168.44.46:3306/rhj";
	public static final String USER = "rhj";
	public static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		// 1단계
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2단계
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}	
}


