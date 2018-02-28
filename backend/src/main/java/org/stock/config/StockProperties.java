package org.stock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "stock", ignoreUnknownFields = true)
public class StockProperties {

	private  String uploadTempFolder;

	public String getUploadTempFolder() {
		return uploadTempFolder;
	}

	public void setUploadTempFolder(String uploadTempFolder) {
		this.uploadTempFolder = uploadTempFolder;
	}
	
}
