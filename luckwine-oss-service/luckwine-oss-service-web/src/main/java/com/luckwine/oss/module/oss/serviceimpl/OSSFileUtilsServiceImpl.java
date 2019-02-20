package com.luckwine.oss.module.oss.serviceimpl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CopyObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OSSFileUtilsServiceImpl {

    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${oss.ossPath}")
    private String ossPath;

    @Value("${oss.bucketName}")
    private String bucketName;

    @Value("${oss.ossUrlPath}")
    private String ossUrlPath;

    /**
     * oss tmp 文件夹定时清理，保存数据请调用 OSSFileUtilsServiceImpl().save()
     * @param inputStream
     * @param filename
     * @return
     */
    public String upload(InputStream inputStream, String filename) {
        String imagePath = "tmp/" + ossPath + getFolderDir() + filename;
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        ossClient.putObject(bucketName, imagePath, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();

        return imagePath;
    }

    public List<String> save(List<String> fileList) {
        List<String> stringList = new ArrayList<>();
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CopyObjectRequest copyObjectRequest;
        for (String fileItem : fileList) {
            fileItem = fileItem.replace(ossUrlPath, "");
            copyObjectRequest = new CopyObjectRequest(bucketName, fileItem, bucketName, fileItem.substring(4, fileItem.length()));
            ossClient.copyObject(copyObjectRequest);
            stringList.add(fileItem.substring(4, fileItem.length()));
        }
        return stringList;
    }


    private String getFolderDir() {
        return DateUtils.formatDate(new Date(), "/yyyy/MM/dd/HH/mm/");
    }
}
