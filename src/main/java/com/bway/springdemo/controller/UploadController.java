package com.bway.springdemo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
	
	@GetMapping("/upload")
	public String showUploadForm(){
		return "UploadForm";
	}
	
	@PostMapping("/upload")
	public String saveImage(@RequestParam("image") MultipartFile img, Model model) {
		String IMG_PATH ="src/main/resources/static/images/" +img.getOriginalFilename();
		if(!img.isEmpty()){
			try {
				Files.copy(img.getInputStream(), Path.of(IMG_PATH), StandardCopyOption.REPLACE_EXISTING);
				model.addAttribute("msg","Upload Successfully");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		return "UploadForm";
	}

}
