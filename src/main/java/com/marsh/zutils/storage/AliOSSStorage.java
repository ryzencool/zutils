package com.marsh.zutils.storage;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.CreateBucketRequest;
import com.marsh.zutils.config.AliOSSConfig;
import com.marsh.zutils.property.AliOSSProperties;
import io.minio.Result;
import io.minio.messages.Item;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.io.InputStream;
import java.util.List;

@ConditionalOnBean(AliOSSProperties.class)
public class AliOSSStorage implements StorageHelper {

	private final AliOSSProperties aliOSSProperties;

	public AliOSSStorage(AliOSSProperties aliOSSProperties) {
		this.aliOSSProperties = aliOSSProperties;
	}

	private OSS init() {
		return new OSSClientBuilder().build(aliOSSProperties.getEndpoint(), aliOSSProperties.getAccessKeyId(), aliOSSProperties.getAccessKeySecret());
	}

	@Override
	public void makeBucket(String bucketName) {
		OSS oss = init();
		CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
		oss.createBucket(createBucketRequest);
		oss.shutdown();
	}

	@Override
	public Object listBuckets() {
		OSS oss = init();
		List<Bucket> buckets = oss.listBuckets();
		oss.shutdown();
		return buckets;
	}

	@Override
	public boolean bucketExists(String bucketName) {
		OSS oss = init();
		boolean b = oss.doesBucketExist(bucketName);
		oss.shutdown();
		return b;
	}

	@Override
	public void removeBucket(String bucketName) {
		OSS oss = init();
		oss.deleteBucket(bucketName);
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
		OSS oss = init();
		oss.putObject(bucketName, objectName, stream);
		oss.shutdown();
	}

	@Override
	public String getPutUrl(String bucketName, String objectName) {
		return String.format(AliOSSConfig.TEMPLATE_URL, bucketName, aliOSSProperties.getRegion(), objectName);
	}

	@Override
	public InputStream downloadObject(String bucketName, String path) {
		return null;
	}
}
