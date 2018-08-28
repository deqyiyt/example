package com.example.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.utils.FileUtils;

public class BodyUploadServlet extends HttpServlet {

	/**
	 * 
	 * @type long
	 * @date 2018年8月28日 上午10:46:57
	 */
	private static final long serialVersionUID = 8883342489558430411L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取当前时间戳
		long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();

		// 存储文件路径
		String filePath = new File(getServletContext().getRealPath("/")) + File.separator + "upload" + File.separator + milliSecond;
		System.out.println(filePath);
		// 创建文件
		FileUtils.checkExist(filePath);
		FileUtils.copy(request.getInputStream(), filePath);

		download(request, response, filePath);
	}

	// 下载文件
	private void download(HttpServletRequest request, HttpServletResponse response, String filePath) throws IOException {
		// 获取当前时间戳
//		long milliSecond = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
//		response.setHeader("content-disposition", "attachment;filename=" + milliSecond);
		try (FileInputStream in = new FileInputStream(filePath);OutputStream out = response.getOutputStream()){
			// 建立快取區
			byte buffer[] = new byte[1024];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				// 輸出緩衝區的內容到瀏覽器，實現文件下載
				out.write(buffer, 0, len);
			}
		}
	}
}