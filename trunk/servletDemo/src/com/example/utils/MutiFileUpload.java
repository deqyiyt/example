package com.example.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 当读取上传表单的各部分时会用到该encoding，如果没有指定 encoding *
 * 则使用系统缺省的encoding。建议在这里设置成utf-8，并把jsp 的charset也设置成utf-8，否则可能会出现乱码。
 */
public class MutiFileUpload {

	public Map<String,String> parameters;	// 保存form表单域中非上传控件的值
	public Map<String,FileItem> files;		// 保存上传的文件

	// 将文件保存在内存还是磁盘临时文件夹的默认临界值，值为10240，即10kb
	private int sizeThreshold = DiskFileItemFactory.DEFAULT_SIZE_THRESHOLD;

	private long sizeMax = 1024 * 1024 * 20;		// 上传文件的大小限制;
	private String encoding = "UTF-8";			// 字符编码，当读取上传表单的各部分时会用到该encoding

	// 以上属性的 get/set 方法
	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public long getSizeMax() {
		return sizeMax;
	}

	public void setSizeMax(long sizeMax) {
		this.sizeMax = sizeMax;
	}

	public int getSizeThreshold() {
		return sizeThreshold;
	}

	public void setSizeThreshold(int sizeThreshold) {
		this.sizeThreshold = sizeThreshold;
	}// 解析请求，这个方法很重要

	public void parse(HttpServletRequest request){  
  
		//保存数据的2个Map初始化
        parameters = new HashMap<String,String>();
        files = new HashMap<String,FileItem>();
  
        DiskFileItemFactory factory = new DiskFileItemFactory();  
  
        //设置临界值约束
        factory.setSizeThreshold(sizeThreshold);  
  
        ServletFileUpload upload = new ServletFileUpload(factory);  
  
        upload.setSizeMax(sizeMax);  
        upload.setHeaderEncoding(encoding);  
  
        try {  
  
            List<FileItem> items = upload.parseRequest(request);  
            Iterator<FileItem> iterator = items.iterator();  
            while(iterator.hasNext()){  
  
                FileItem item = (FileItem)iterator.next();  
                if(item.isFormField()){                   
                    String fieldName = item.getFieldName();  
                    String value = new String(item.getString(encoding));  
                    parameters.put(fieldName, value);  
  
                }else{  
                    String fieldName = item.getFieldName();  
                    files.put(fieldName, item);  
                }  
            }  
  
        } catch (FileUploadException e) {  
            e.printStackTrace();  
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }  
    }

	/**
	 * 得到上传文件的文件名
	 * 
	 * @param item
	 * @return
	 */
	public String getFileName(FileItem item) {

		String fileName = item.getName();
		fileName = replace(fileName, "\\", "/");
		fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
		return fileName;

	}

	/**
	 * 字符串替换
	 * 
	 * @param source
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static String replace(String source, String oldString, String newString) {

		StringBuffer output = new StringBuffer();
		int lengthOfSource = source.length();
		int lengthOfOld = oldString.length();
		int posStart = 0;
		int pos;

		while ((pos = source.indexOf(oldString, posStart)) >= 0) {

			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengthOfOld;
		}

		if (posStart < lengthOfSource) {
			output.append(source.substring(posStart));
		}

		return output.toString();
	}
}