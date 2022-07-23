package com.marsh.zutils.storage;

import io.minio.Result;
import io.minio.messages.Item;

import java.io.InputStream;

public interface StorageHelper {

    /**
     * 创建桶
     */
    void makeBucket(String bucketName);

    /**
     * 列出所有的桶
     */
    Object listBuckets();

    /**
     * 判断桶是否存在
     */
    boolean bucketExists(String bucketName);

    /**
     * 删除桶
     */
    void removeBucket(String bucketName);

    Iterable<Result<Item>> listObjects(String bucketName);

    Iterable<Result<Item>> listObjects(String bucketName, String prefix);

    InputStream getObject(String bucketName, String objectName);

    void putObject(String bucketName, String objectName, InputStream stream, String contentType);

    String getPutUrl(String bucketName, String objectName);

     InputStream downloadObject(String bucketName, String path);

}
