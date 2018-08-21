package com.example.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {


	/**
	 * 
	 * @type long
	 * @date 2018年8月21日 下午4:51:52
	 */
	private static final long serialVersionUID = -5365447661964125625L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String folderPath = new File(getServletContext().getRealPath("/")) + "/upload";
		
		File file = new File(folderPath);
		String[] fileList = file.list();
		if (null == fileList) {
			fileList = new String[]{};
		}
		
		request.getRequestDispatcher("/WEB-INF/pages/download.jsp").forward(request, response);
	}
}