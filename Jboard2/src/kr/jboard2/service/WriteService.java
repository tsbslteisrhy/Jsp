package kr.jboard2.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.jboard2.config.DBConfig;
import kr.jboard2.config.SQL;
import kr.jboard2.controller.CommonService;

public class WriteService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("POST")) {
			
			// �Ķ���� ����
			String title   = req.getParameter("title");
			String content = req.getParameter("content");
			String uid     = req.getParameter("uid");
			String regip   = req.getRemoteAddr();
						
			// 1, 2�ܰ�
			Connection conn = DBConfig.getConnection();
			
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(SQL.INSERT_ARTICLE);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, 0);
			psmt.setString(4, uid);
			psmt.setString(5, regip);
			
			// 4�ܰ�
			psmt.executeUpdate();
			
			// 5�ܰ�
			// 6�ܰ�
			psmt.close();
			conn.close();
			
			// �����̷�Ʈ
			return "redirect:/Jboard2/list.do";
			
		}else {
			return "/write.jsp";
		}
			
	}

}