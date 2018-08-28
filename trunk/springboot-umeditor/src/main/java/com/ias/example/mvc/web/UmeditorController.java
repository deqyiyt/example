package com.ias.example.mvc.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ias.example.mvc.utils.FileUtils;

/**
 * 百度编辑文件上传
 * @date: 2018年8月24日 上午12:24:47
 * @author: 老胡
 */
@RestController
@RequestMapping(value = "umeditor")
public class UmeditorController {
	
	private static final Logger log = LoggerFactory.getLogger(UmeditorController.class);

	
	private String imageFolder = this.getClass().getResource("/").getPath() + "imgs/";
	
	/**
	 * 上传文件
	 * 用于测试上传，返回文件名称
	 * 生产环境一般返回具体的文件服务器URL，例如：七牛云，阿里OSS等
	 * @author 老胡
	 * @date 2018年8月24日 上午12:24:57
	 * @param file
	 * @param fileLength
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("upload")
	public String upload(@RequestParam(value="file", required=false) MultipartFile file
	        , @RequestParam(value="length",required=false,defaultValue="0") int fileLength) throws IllegalStateException, IOException {
		if(file != null && file.getSize() > 0) {
			String fileName = file.getOriginalFilename();
			String filePath = imageFolder + fileName;
			log.debug("保存的文件路径为：{}", imageFolder);
			file.transferTo(FileUtils.checkExist(filePath));
			return fileName;
		} else {
			return "";
		}
	}
	
	/**
	 * 图片下载/预览，生产环境一般使用文件服务器代替
	 * 使用文件服务器就不需要这里来读取文件展示
	 * @author 老胡
	 * @date 2018年8月24日 上午12:25:07
	 * @param fileName
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "download/{fileName}")
	public void download( @PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {
		String filePath = imageFolder + fileName;
		log.debug("图片路径：{}", filePath);
		byte[] data = FileUtils.readToByte(new File(filePath));
		response.setContentType("image/png");
	    try(OutputStream os = response.getOutputStream()) {
		    os.write(data);
		    os.flush();
	    }
	}
}

