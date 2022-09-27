package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiSong
 * @since 2022-09-23
 */
public interface OssService {
    /**
     * 上传头像
     *
     * @param file  需要上传的文件
     * @return  文件保存的路径
     */
    String uploadFileAvatar(MultipartFile file);
}
