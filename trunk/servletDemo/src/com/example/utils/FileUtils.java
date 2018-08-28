package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
	
	/**
	 * 判断文件及目录是否存在，若不存在则创建文件及目录
	 * @author 老胡
	 * @date 2018年8月28日 上午10:54:41
	 * @param filepath
	 * @return
	 */
	public static File checkExist(String filepath){
		File file=new File(filepath);
		try{
			if (file.exists()) {							//判断文件目录的存在
				if(!file.isDirectory()){					//判断文件的存在性 
					file.createNewFile();					//创建文件
				}
			}else {
				File file2=new File(file.getParent());
				file2.mkdirs();							//创建文件夹
				if(!file.isDirectory()){
					file.createNewFile();//创建文件 
				}
			}
			return file;
		}catch(Exception e){
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 输入流拷贝文件
	 * @author 老胡
	 * @date 2018年8月28日 上午10:53:57
	 * @param src 文件输入流
	 * @param dstPath 文件输出路径
	 * @return = true 拷贝成功 =false 拷贝失败
	 */
	public static boolean copy(InputStream src, final String dstPath) {
		try {
			File dstFile = new File(dstPath);
			byte[] buffer = new byte[64 * 1024];
			int length = 0;

			try (OutputStream os = new FileOutputStream(dstFile)){
				while ((length = src.read(buffer, 0, buffer.length)) != -1) {
					os.write(buffer, 0, length);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
