<%@page import="java.net.URLEncoder"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String value="자바";
	String encodedValue = URLEncoder.encode(value, "utf-8");
	response.sendRedirect("./index.jsp?name=" + encodedValue);
%>