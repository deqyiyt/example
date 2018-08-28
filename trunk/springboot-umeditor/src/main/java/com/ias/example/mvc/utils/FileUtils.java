package com.ias.example.mvc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

	/**
	 * 判断文件及目录是否存在，若不存在则创建文件及目录
	 * @author 老胡
	 * @date 2018年8月24日 上午12:27:03
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
	 * 将文件转换为byte[]
	 * @author 老胡
	 * @param filePath	本地资源地址
	 * @return
	 */
	public static byte[] readToByte(File file) {
		Long filelength = file.length();
		byte[] filecontent = new byte[filelength.intValue()];
		try {
			FileInputStream in = new FileInputStream(file);
			in.read(filecontent);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filecontent;
	}
}
