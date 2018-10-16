package com.jt.manage.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;

@Controller
public class FileController {
	
	@Autowired
	private FileService fileService;
	
	//如果参数需要赋值，需要通过springmvc所提供的解析器才可以
	@RequestMapping("/file")
	/**
	 * 注意事项：
	 * 参数接收，必须和页面提交的name属性相同，否则参数不能提交，程序会报404错误
	 * @param photo
	 * @return
	 */
	public String imageFile(MultipartFile photo) throws IllegalStateException, IOException{
		//1,定义文件上传的目录
		File file = new File("D:/jt-upload");
		if(!file.exists()){
			file.mkdirs();
		}
		String filename = photo.getOriginalFilename();
		File dest = new File("D:/jt-upload/"+filename);
		photo.transferTo(dest);
		System.out.println("文件上传成功！！！！");
		return "index";
	}
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public PicUploadResult fileUpload(MultipartFile uploadFile){
		return fileService.fileUpload(uploadFile);
	}

}
