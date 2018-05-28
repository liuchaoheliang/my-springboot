package com.cloud.lc.springboot.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.cloud.lc.springboot.logger.Logger;
import com.cloud.lc.springboot.ro.FileRo;
import com.cloud.lc.springboot.util.ServletUtil;

@Controller
@RequestMapping("/webChat")
public class WebChatController {
	
	
	@RequestMapping("/index")
	public String chat( ){
		return "webchat";
	}
	
	

	
	
}
