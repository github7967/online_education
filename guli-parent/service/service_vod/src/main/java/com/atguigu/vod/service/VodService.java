package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiSong
 * @since 2022-10-11
 */
public interface VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file  视频文件
     * @return
     */
    String uploadAly(MultipartFile file);
}
