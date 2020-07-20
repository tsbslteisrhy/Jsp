package kr.jboard2.service.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.jboard2.config.DBConfig;
import kr.jboard2.config.SQL;
import kr.jboard2.controller.CommonService;

public class CheckUidService implements CommonService {

	@Override
	public String requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String uid =req.getParameter("uid");
		
		// 1, 2�ܰ�
		Connection conn = DBConfig.getConnection();
		
		// 3�ܰ�
		PreparedStatement psmt = conn.prepareStatement(SQL.SELECT_CHECK_UID);
		psmt.setString(1, uid);
		
		// 4�ܰ�
		ResultSet rs = psmt.executeQuery();
		
		// 5�ܰ�
		int result = 0;
		if(rs.next()) {
			result = rs.getInt(1);
		}
		
		// 6�ܰ�
		rs.close();
		psmt.close();
		conn.close();
		
		// gson ���̺귯���� Ȱ���� json���ڿ� ���� ����
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
				
		return "json:"+json.toString();
	}

}
