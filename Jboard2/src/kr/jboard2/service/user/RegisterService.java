package kr.jboard2.service.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.jboard2.config.DBConfig;
import kr.jboard2.config.SQL;
import kr.jboard2.controller.CommonService;

public class RegisterService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("POST")) {
			
			// �Ķ���� ����
			String uid   = req.getParameter("uid");
			String pass1 = req.getParameter("pass1");
			String pass2 = req.getParameter("pass2");
			String name  = req.getParameter("name");
			String nick  = req.getParameter("nick");
			String email = req.getParameter("email");
			String hp    = req.getParameter("hp");
			String zip   = req.getParameter("zip");
			String addr1 = req.getParameter("addr1");
			String addr2 = req.getParameter("addr2");
			String regip = req.getRemoteAddr();
						
			// 1, 2�ܰ�
			Connection conn = DBConfig.getConnection();
			
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_MEMBER);
			psmt.setString(1, uid);
			psmt.setString(2, pass1);
			psmt.setString(3, name);
			psmt.setString(4, nick);
			psmt.setString(5, email);
			psmt.setString(6, hp);
			psmt.setString(7, zip);
			psmt.setString(8, addr1);
			psmt.setString(9, addr2);
			psmt.setString(10, regip);
			
			// 4�ܰ�
			psmt.executeUpdate();
			
			// 5�ܰ�
			// 6�ܰ�
			psmt.close();
			conn.close();
			
			// �����̷�Ʈ
			return "redirect:/Jboard2/user/login.do";
			
		}else {
			
			return "/user/register.jsp";
		}
		
		
	}

}