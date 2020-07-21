package kr.jboard2.service.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.jboard2.bean.MemberBean;
import kr.jboard2.config.DBConfig;
import kr.jboard2.config.SQL;
import kr.jboard2.controller.CommonService;

public class LoginService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		if(req.getMethod().equals("POST")) {
			
			// �Ķ���� ����
			String uid = req.getParameter("uid");
			String pass = req.getParameter("pass");
			
			// 1, 2�ܰ�
			Connection conn = DBConfig.getConnection();
			
			// 3�ܰ�
			PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_MEMBER);
			psmt.setString(1, uid);
			psmt.setNString(2, pass);
			
			// 4�ܰ�
			ResultSet rs = psmt.executeQuery();
			
			// 5�ܰ�
			MemberBean mb = null;
			
			if(rs.next()) {
				mb = new MemberBean();
				mb.setUid(rs.getString(1));
				mb.setPass(rs.getString(2));
				mb.setName(rs.getString(3));
				mb.setNick(rs.getString(4));
				mb.setEmail(rs.getString(5));
				mb.setHp(rs.getString(6));
				mb.setGrade(rs.getInt(7));
				mb.setZip(rs.getString(8));
				mb.setAddr1(rs.getString(9));
				mb.setAddr2(rs.getString(10));
				mb.setRegip(rs.getString(11));
				mb.setRdate(rs.getString(12));
			}
			
			// 6�ܰ�
			rs.close();
			psmt.close();
			conn.close();
			
			// ����� ��ü ���� ����
			if(mb != null) {
				// ȸ���� ���� ��� ����� ��ü ���� ���� �� ����Ʈ �̵�
				HttpSession session = req.getSession();
				session.setAttribute("member", mb);
				// �����̷�Ʈ
				return "redirect:/Jboard2/list.do";
			} else {
				// ȸ���� �ƴ� ��� �ٽ� �α��� �̵�
				return "redirect:/Jboard2/user/login.do";
			}
			
		}else {
			// GET ��û�϶� View�� forward�� ���� view���� ����
			return "/user/login.jsp";	
		}

	}

}
