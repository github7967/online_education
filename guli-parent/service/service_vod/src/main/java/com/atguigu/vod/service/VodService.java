package com.atguigu.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 根据视频id删除阿里云视频
     *
     * @param id    视频id
     * @return
     */
    void removeAlyVideoById(String id);

    /**
     * 批量删除阿里云视频
     *
     * @param videoList     视频id集合
     * @return
     */
    void removeMoreAlyVideo(List videoList);
}
