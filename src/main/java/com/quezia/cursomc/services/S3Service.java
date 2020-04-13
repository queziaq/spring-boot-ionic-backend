package com.quezia.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	
	private Logger log= LoggerFactory.getLogger(S3Service.class);
	@Autowired
	private AmazonS3 s3client;
	
	@Value("${s3.bucket}")
	private String bucketName;
	
	public URI uploadFile(MultipartFile multiPartFile) throws IOException {

		String fileName = multiPartFile.getOriginalFilename();
		InputStream is = multiPartFile.getInputStream();
		String contentType = multiPartFile.getContentType();

		return uploadFile(is, fileName, contentType);
					
	}
	
	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			log.info("Iniciando Upload");
			s3client.putObject(bucketName, fileName, is, meta);
			log.info("upload Finalizado");
			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao Converter URL para URI");
		}
	}

}
