package com.lontsi.gestiondestock.services.impl;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.lontsi.gestiondestock.services.FlickrService;

public class FlickrServiceImpl implements FlickrService {

	@Value("${flickr.apikeys}")
	private String apikey;

	@Value("${flickr.apisecret}")
	private String apisercret;

	@Value("${flickr.appkeys}")
	private String appkey;

	@Value("${flickr.appsecret}")
	private String appsecret;

	private Flickr flickr;

	@Autowired
	 public FlickrServiceImpl(Flickr flickr) {
		this.flickr = flickr;
	}

	@Override
	public String savePhoto(InputStream photo, String title) throws FlickrException {

		UploadMetaData uploadMetaData = new UploadMetaData() ;
		uploadMetaData.setTitle(title);

		String photoId = flickr.getUploader()
		.upload(photo, uploadMetaData);
		
		return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
	}

	

}
