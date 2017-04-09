package cn.yt.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	
	@RequestMapping("/uploadimg.do")
	@ResponseBody
	public Object uploadimg(MultipartFile picture,HttpSession session) throws Exception{
		System.out.println(picture);
		if(!picture.isEmpty()){
			String path = session.getServletContext().getRealPath("uploadimages/april");
			String filename = picture.getOriginalFilename();
			String newFilename = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
			File file = new File(path,newFilename);
			picture.transferTo(file);
			return "uploadimages/april/"+newFilename;
		}
		return "fail";
	}
	@RequestMapping("/downloadimg.do")
	@ResponseBody
	public Object downloadimg(String urlString,HttpSession session) throws Exception{
		   //构造URL
		   URL url = new URL(urlString);
		   // 打开连接
		   URLConnection con = url.openConnection();
		   //设置请求超时为5s
		   con.setConnectTimeout(5*1000);
		   // 输入流
		   InputStream is = con.getInputStream();

		   // 1K的数据缓冲
		   byte[] bs = new byte[1024];
		   // 读取到的数据长度
		   int len;
		   // 输出的文件流
		   String savePath = session.getServletContext().getRealPath("uploadimages/april");
		   File file = new File(savePath);
		   if(!file.exists()){
			   file.mkdirs();
		   }
		   String postfix = "";
		   int index1 = urlString.indexOf(".jpg");
		   int index2 = urlString.indexOf(".jpeg"); 
		   int index3 = urlString.indexOf(".png");
		   int index4 = urlString.indexOf(".gif");
		   if(index1 > -1){
			   postfix = urlString.substring(index1, index1+4);
		   }else if(index2 > -1){
			   postfix = urlString.substring(index2, index2+5);
		   }else if(index3 > -1){
			   postfix = urlString.substring(index3, index3+4);
		   }else if(index4 > -1){
			   postfix = urlString.substring(index4, index4+4);
		   }
		   String filename = UUID.randomUUID() + postfix;
		   OutputStream os = new FileOutputStream(file.getPath()+"\\"+filename);
		   // 开始读取
		   while ((len = is.read(bs)) != -1) {
		     os.write(bs, 0, len);
		   }
		   // 完毕，关闭所有链接
		   os.close();
		   is.close();
		   return "uploadimages/april/" + filename;
	}
}
