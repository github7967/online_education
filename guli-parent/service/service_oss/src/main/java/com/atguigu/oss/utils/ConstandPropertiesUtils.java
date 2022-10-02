package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author LiSong
 * @since 2022-09-23
 */

//当项目一启动, spring接口, spring加载之后, 执行接口一个方法
@Component
public class ConstandPropertiesUtils implements InitializingBean {
    //地域节点
    @Value("${aliyun.oss.file.endpoint}")
    private String endpoint;

    //keyid
    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    //keysecret
    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    //bucketname
    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    //定义公开静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT = endpoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
