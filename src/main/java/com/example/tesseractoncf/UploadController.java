package com.example.tesseractoncf;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@Controller
public class UploadController {
	 private final Ocr ocr;

	 private BufferedInputStream bis;
	 
	 
	 
	 public UploadController(Ocr ocr) {
	      this.ocr = ocr;
	 }

    
  
    @GetMapping("/")
    public String index() {
        return "index";
    }

    
    
    @PostMapping("/")
    public String sendImg(@RequestParam MultipartFile file , Model model){
    	File yoonFile = this.ocr.yoon(file);
    	String result = "";
    	try {
			Socket socket = new Socket("133.186.135.65",5000);
			OutputStream outputStream = socket.getOutputStream();
			
			InputStream inputStream = socket.getInputStream();
			BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			
			BufferedImage image = ImageIO.read(yoonFile);
			
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    	
			ImageIO.write(image, "jpg", byteArrayOutputStream);
			
			
			//byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
			System.out.println("byteArrayOutputStream : "+byteArrayOutputStream.size());
	        //사진의 크기를 전송
			int filesize = (int )yoonFile.length()*100;
			FileInputStream fis  = new FileInputStream(yoonFile);
			//System.out.println("size : "+size.length);
			outputStream.write(Integer.toString(filesize).getBytes());
			outputStream.flush();
			
			//사진을 전송 
			byte[] data = new byte[(int) (filesize)];
	        //outputStream.write(byteArrayOutputStream.toByteArray());
			outputStream.write(data , 0, fis.read(data));
	        System.out.println("byteArrayOutputStream : "+byteArrayOutputStream.toByteArray());
	        outputStream.flush();
	        System.out.println("Flushed: " + System.currentTimeMillis());
	        fis.close();
	        
	        byte[] tmp = new byte[100];
	    	int zz = bufferedInputStream.read(tmp);
	    	result = new String(tmp,0,zz);
	    	System.err.println(result);
	    	model.addAttribute("result",result);
	    	return "index";
	    	
			
    	} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	model.addAttribute("result","fail");
    	return "index";
    	
    }


}
