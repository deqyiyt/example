package com.example.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import com.example.utils.MutiFileUpload;

/**
 * 
 * @description TODO(用一句话描述该类做什么)
 * @date: 2018年8月21日 下午5:14:01
 * @author: jiuzhou.hu
 */
public class FileUploadServlet extends HttpServlet {

	/**
	 * 
	 * @type long
	 * @date 2018年8月21日 下午4:31:03
	 */
	private static final long serialVersionUID = -8673589894676618163L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MutiFileUpload fileUpload = new MutiFileUpload();
		fileUpload.parse(request);

		String folderPath = new File(getServletContext().getRealPath("/")) + "/upload";
		File filesDir = new File(folderPath);
		if (!filesDir.exists() && !filesDir.isDirectory()) {
			System.out.println("目录或文件不存在! 创建目标存放附件文件夹：" + filesDir.getPath());
			filesDir.mkdir();
		}

		// 这里是打印非上传组件的值，查看是否能够正常接收
		// System.out.println( fileUpload.parameters.get("possess") );

		Iterator<FileItem> iterator = fileUpload.files.values().iterator();
		while (iterator.hasNext()) {
			FileItem item = iterator.next();
			String fileName = fileUpload.getFileName(item);
			if (fileName != null && !fileName.equals("")) {
				File file = new File(folderPath + "/" + fileName);

				try {
					if (!file.isDirectory()) {
						// 上传成功后做数据库操作
						item.write(file);
						System.out.println("上传成功，文件物理路径：" + file.getPath());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
//		response.sendRedirect("download");
	}
}