package com.jt.manage.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jt.common.vo.PicUploadResult;
import com.jt.manage.service.FileService;
@Service
public class FileServiceImpl implements FileService{
	
	@Value("${image.localPath}")//可以动态获取spring容器里的key值
	private String localpath;
	@Value("${image.urlPath}")
	private String urlPath;
	
	/**
	 * 1,判断图片的类型jpg/png/git
	 * 2，判断是否为恶意程序exe.jpg
	 * 3,为了提高检索的效率，将图片分文件存储，
	 * 	3.1UUID:hash随机算法（当前毫秒数+算法+hash）=32位hash值
	 *     aaaaaaaa-bbbbbbbb-cccccccc-dddddddd/1.jpg(分级存储)
	 *     yyyy/MM/dd
	 * 4,如何杜绝文件重名现象
	 * uuid+随机数（3位数）.jpg
	 * 5,实现文件上传    
	 */
	@Override
	public PicUploadResult fileUpload(MultipartFile uploadFile) {
		PicUploadResult picUploadResult = new PicUploadResult();
		//获取文件名及后缀
		String filename = uploadFile.getOriginalFilename();
		//将文件名全部改成小写
		filename = filename.toLowerCase();
		//1,判断图片的类型jpg/png/git
		if(!filename.matches("^.*\\.(jpg|png|gif)$")){
			picUploadResult.setError(1);
			return picUploadResult;
		}
		
		//2，判断是否为恶意程序exe.jpg
		try {
			BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
			int height = bufferedImage.getHeight();
			int width = bufferedImage.getWidth();
			if(height==0||width==0){
				//表示不是图片
				picUploadResult.setError(1);
				return picUploadResult;
			}
			//程序执行到这里，表示是一张图片
			//为图片进行分文件存储yyyy/MM/dd
			String datepath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			//定义文件保存路径
			String dirpath=localpath+datepath;
			//判断文件夹是否存在
			File dirfile = new File(dirpath);
			if(!dirfile.exists()){
				//文件不存在时，应该创建文件夹
				dirfile.mkdirs();
			}
			//4，动态生成文件名称，uuid+随机数（3位数）
			String uuid = UUID.randomUUID().toString().replace("-", "");
			int randomNum=new Random().nextInt(1000);
			String imageFileType=filename.substring(filename.lastIndexOf("."));
			String imageFileName=uuid+randomNum+imageFileType;
			
			//5,实现文件上传 
			String dest = dirpath+"/"+imageFileName;
			uploadFile.transferTo(new File(dest));
			
			//6.封装参数
			picUploadResult.setHeight(height+"");
			picUploadResult.setWidth(width+"");
			
			//7，准备虚拟路径，
			String imageUrlPath = urlPath+datepath+"/"+imageFileName;
			picUploadResult.setUrl(imageUrlPath);//添加虚拟路径
		} catch (IOException e) {
			e.printStackTrace();
			picUploadResult.setError(1);
			return picUploadResult;
		}
		
		return picUploadResult;
	}

}
