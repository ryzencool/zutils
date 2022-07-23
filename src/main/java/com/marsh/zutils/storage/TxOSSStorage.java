package com.marsh.zutils.storage;


import com.marsh.zutils.config.TxOSSConfig;
import com.marsh.zutils.property.TxOSSProperties;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.region.Region;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.io.InputStream;

@ConditionalOnBean({TxOSSProperties.class})
public class TxOSSStorage implements StorageHelper{


	private final TxOSSProperties txOSSProperties;

	public TxOSSStorage(TxOSSProperties txOSSProperties) {
		this.txOSSProperties = txOSSProperties;
	}

	public COSClient init() {
		COSCredentials cred = new BasicCOSCredentials(txOSSProperties.getSecretId(), txOSSProperties.getSecretKey());
		Region region = new Region(txOSSProperties.getRegion());
		ClientConfig clientConfig = new ClientConfig(region);
		return new COSClient(cred, clientConfig);
	}

	@Override
	public void makeBucket(String bucketName) {
		COSClient oss = init();
		oss.createBucket(bucketName);
	}

	@Override
	public Object listBuckets() {

		COSClient oss = init();
		return oss.listBuckets();
	}

	@Override
	public boolean bucketExists(String bucketName) {
		return false;
	}

	@Override
	public void removeBucket(String bucketName) {

	}

	@Override
	public Iterable<Result<Item>> listObjects(String bucketName) {
		return null;
	}

	@Override
	public Iterable<Result<Item>> listObjects(String bucketName, String prefix) {
		return null;
	}

	@Override
	public InputStream getObject(String bucketName, String objectName) {
		return null;
	}

	@Override
	public void putObject(String bucketName, String objectName, InputStream stream, String contentType) {
		COSClient oss = init();
		ObjectMetadata om = new ObjectMetadata();
		oss.putObject(bucketName, objectName, stream, om);
	}

	@Override
	public String getPutUrl(String bucketName, String objectName) {
		return String.format(TxOSSConfig.TEMPLATE_URL, bucketName, txOSSProperties.getRegion(), objectName);
	}

	@Override
	public InputStream downloadObject(String bucketName, String path) {
		var cosClient = init();
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, path);
		COSObject cosObject = cosClient.getObject(getObjectRequest);
		return cosObject.getObjectContent();

	}


}
