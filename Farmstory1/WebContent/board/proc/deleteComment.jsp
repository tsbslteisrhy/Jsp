<%@page import="kr.co.farmstory1.config.SQL"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="kr.co.farmstory1.config.DBConfig"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%

	request.setCharacterEncoding("utf-8");

	String seq    = request.getParameter("seq");
	String group  = request.getParameter("group");
	String cate   = request.getParameter("cate");
	String parent = request.getParameter("parent");
	
	// 1, 2단계
	Connection conn = DBConfig.getConnection();
	
	// 3단계
	PreparedStatement psmtRC = conn.prepareStatement(SQL.UPDATE_RECOUNT_COMMENT);
	psmtRC.setString(1, parent);
		
	PreparedStatement psmt = conn.prepareStatement(SQL.DELETE_COMMENT);
	psmt.setString(1, seq);
		
	// 4단계
	psmtRC.executeUpdate(); // parent -1
	psmt.executeUpdate(); // comment 삭제
	
	// 5단계
	// 6단계
	psmtRC.close();
	psmt.close();
	conn.close();
	
	// 리다이렉트
	response.sendRedirect("/Farmstory1/board/view.jsp?seq="+parent+"&group="+group+"&cate="+cate);
%>