package kr.farmstory2.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {

	// DB���� - ������
//	public static final String HOST = "jdbc:mysql://yhzin.com:3306/rhj?useUnicode=true&characterEncoding=utf8";
//	public static final String USER = "root";
//	public static final String PASS = "0203hj";
	
	// DB���� - ���߿�
	public static final String HOST = "jdbc:mysql://192.168.50.82:3306/rhj";
	public static final String USER = "rhj";
	public static final String PASS = "1234";
	
	public static Connection getConnection() throws Exception {
		// 1�ܰ�
		Class.forName("com.mysql.jdbc.Driver");
		
		// 2�ܰ�
		Connection conn = DriverManager.getConnection(HOST, USER, PASS);
		
		return conn;
	}	
}

