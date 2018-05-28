package com.cloud.lc.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.ro.FileRo;
import com.cloud.lc.springboot.util.ServletUtil;

@RestController
@RequestMapping("/file")
public class FileController {
	
	private String PATH = "E:/upload";
	
	@PostMapping
	public FileRo upload( MultipartFile file ) throws IllegalStateException, IOException{
		
		Logger.info("上传文件名："+file.getName());
        Logger.info(file.getOriginalFilename());
        Logger.info(file.getSize()+"");
        
        File localFile = new File(PATH, file.getOriginalFilename());
        file.transferTo(localFile);
        return new FileRo(localFile.getAbsolutePath());
	}
	
	
	@GetMapping("/{name}")
    public void download(@PathVariable String name, HttpServletRequest request, HttpServletResponse response) {
        try {
        	//InputStream inputStream = new FileInputStream( new File( PATH , name + ".png") );
        	//OutputStream outputStream = response.getOutputStream();
            //response.setContentType("application/x-download");
            //response.addHeader("Content-Disposition", "attachment;filename=" + name + ".png");
            //IOUtils.copy(inputStream, outputStream);
            String fileName = name+".png";
            ServletUtil.responseClientDownloadFile(response, PATH+"/"+fileName, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	
}
