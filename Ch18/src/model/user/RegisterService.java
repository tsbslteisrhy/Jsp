package model.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import model.CommonService;

public class RegisterService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		if(req.getMethod().equals("POST")) {
			
			// �Ķ���� ����
			req.setCharacterEncoding("utf-8");
			String uid  = req.getParameter("uid");
			String name = req.getParameter("name");
			String hp   = req.getParameter("hp");
			String pos  = req.getParameter("pos");
			String dep  = req.getParameter("dep");
			
			// DB����
			String host = "jdbc:mysql://192.168.50.82:3306/rhj";
			String user = "rhj";
			String pass = "1234";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			
			try {
				// 1�ܰ�
				Class.forName("com.mysql.jdbc.Driver");
				
				// 2�ܰ�
				conn = DriverManager.getConnection(host, user, pass);
				
				// 3�ܰ�
				psmt = conn.prepareStatement("INSERT INTO `MEMBER` VALUE (?, ?, ?, ?, ?, NOW())");
				psmt.setString(1, uid);
				psmt.setString(2, name);
				psmt.setString(3, hp);
				psmt.setString(4, pos);
				psmt.setString(5, dep);
				
				// 4�ܰ�
				psmt.executeUpdate();
								
				// 5�ܰ�	
				// 6�ܰ�
				psmt.close();
				conn.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return "redirect:/Ch18/user/list.do";
		}else {
			return "/user/register.jsp";
		}
	}
	

}
