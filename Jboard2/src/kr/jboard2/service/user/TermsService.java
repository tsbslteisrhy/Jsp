package kr.jboard2.service.user;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.jboard2.bean.TermsBean;
import kr.jboard2.config.DBConfig;
import kr.jboard2.config.SQL;
import kr.jboard2.controller.CommonService;

public class TermsService implements CommonService{

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		// 1, 2�ܰ�
		Connection conn = DBConfig.getConnection();
		
		// 3�ܰ�
		Statement stmt = conn.createStatement();
		
		// 4�ܰ�
		ResultSet rs = stmt.executeQuery(SQL.SELECT_TERMS);
		
		// 5�ܰ�
		TermsBean tb = new TermsBean();
		
		if(rs.next()) {
			tb.setTerms(rs.getString(1));
			tb.setPrivacy(rs.getString(2));
		}
		
		// 6�ܰ�
		rs.close();
		stmt.close();
		conn.close();
		
		// View���� �����ϱ� ���� bean��ü request������ ����
		req.setAttribute("tb", tb);
		
		return "/user/terms.jsp";
	}

}