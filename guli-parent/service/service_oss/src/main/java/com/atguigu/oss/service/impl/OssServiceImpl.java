package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstandPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author LiSong
 * @since 2022-09-23
 */
@Service
public class OssServiceImpl implements OssService {
    /**
     * 上传头像
     *
     * @param file  需要上传的文件
     * @return  文件保存的路径
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        //工具类获取值
        String endpoint = ConstandPropertiesUtils.END_POINT;
        String accessKeyId = ConstandPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstandPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstandPropertiesUtils.BUCKET_NAME;
        try {
            //创建OSS实例
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
            //获取上传文件流
            InputStream inputStream = file.getInputStream();
            //获取文件名称
            String fileName = file.getOriginalFilename();
            //在文件名称里面添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(17);
            fileName = uuid + fileName;
            //获取当前系统时间
            String time = new DateTime().toString("yyyy/MM/dd");
            fileName = time + "/" + fileName;
            //调用oss方法实现上传
            ossClient.putObject(bucketName, fileName, inputStream);
            //关闭流
            ossClient.shutdown();
            //把上传之后的文件路径进行返回
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
