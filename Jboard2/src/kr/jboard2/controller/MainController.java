package kr.jboard2.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.javafx.image.impl.General;



public class MainController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private Map<String, Object> instances = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// ������Ƽ ���� ��� ���ϱ�
		ServletContext sc = config.getServletContext();
		String path = sc.getRealPath("/WEB-INF")+"/urlMapping.properties";
		
		Properties prop = new Properties();
		
		try {
			// ������Ƽ ���ϰ� �Է� ��Ʈ�� ����
			FileInputStream fis = new FileInputStream(path);
			// �Է� ��Ʈ������ ������Ƽ ���� �о prop��ü�� Key-Map ������ ����
			prop.load(fis);
			// �Է� ��Ʈ�� ����
			fis.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// prop ��ü�� ������ ���� Model ��ü�� Map�� ����
		Iterator iter = prop.keySet().iterator();
		
		while(iter.hasNext()) {
			
			String k = iter.next().toString();
			String v = prop.getProperty(k);
			
			try {
				Class obj = Class.forName(v);
				Object instance = obj.newInstance();
				
				instances.put(k, instance);
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			requestProc(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			requestProc(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void requestProc(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		
		String path = req.getContextPath();
		String uri  = req.getRequestURI();
		
		String action = uri.substring(path.length());
		
		CommonService instance = (CommonService)instances.get(action);
		String result = instance.requestProc(req, resp);

		if(result.startsWith("redirect:")) {
			// �����̷�Ʈ
			String redirectURL = result.substring(9);
			resp.sendRedirect(redirectURL);
			
		}else if(result.startsWith("json:")){
			
			PrintWriter out = resp.getWriter();
			out.print(result.substring(5));
			
		}else {
			// View ������
			RequestDispatcher dispatcher = req.getRequestDispatcher(result);
			dispatcher.forward(req, resp);
		}
	}
	
}
