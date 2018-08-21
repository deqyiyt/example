<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>download</title>
	</head>
	
	<body>
		<%
		String folderPath = new File(getServletContext().getRealPath("/")) + "/upload";
		File file = new File(folderPath);
		String[] fileList = file.list();
		if (null != fileList) {
			out.println("下载文件:<br/>");
			for(int i=0;i<fileList.length;i++) {
				out.println("<a href='upload/"+fileList[i]+"' target='_blank'>"+fileList[i]+"</a><br/>");
			}
		}
		%>
	</body>
</html>