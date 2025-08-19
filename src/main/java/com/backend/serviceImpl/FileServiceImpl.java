package com.backend.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.backend.service.FileService;

public class FileServiceImpl implements FileService {
	   @Override
	    public String uploadImage(String path, MultipartFile file) throws IOException {
	        // Original file name
	        String name = file.getOriginalFilename();

	        // Random name generation
	        String randomID = UUID.randomUUID().toString();
	        String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));

	        // Full path
	        String filePath = path + File.separator + fileName1;

	        // Create folder if not exists
	        File f = new File(path);
	        if (!f.exists()) {
	            f.mkdirs(); // make directories if needed
	        }

	        // Copy file to target location
	        Files.copy(file.getInputStream(), Paths.get(filePath));

	        return fileName1;
	    }

	    @Override
	    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
	        String fullPath = path + File.separator + fileName;
	        return new FileInputStream(fullPath);
	    }
	}
