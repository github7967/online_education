package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitVodClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author LiSong
 * @since 2022-10-11
 */
@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file  视频文件
     * @return
     */
    @Override
    public String uploadAly(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();   //上传文件的原始名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));  //上传之后显示的名称
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest request =
                    new UploadStreamRequest(ConstantVodUtils.ACCESS_KEY_ID,
                                            ConstantVodUtils.ACCESS_KEY_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据视频id删除阿里云视频
     *
     * @param id    视频id
     * @return
     */
    @Override
    public void removeAlyVideoById(String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(201, "删除视频失败");
        }
    }

    /**
     * 批量删除阿里云视频
     *
     * @param videoList     视频id集合
     * @return
     */
    @Override
    public void removeMoreAlyVideo(List videoList) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //videoIdList值转换成"1,2,3..."格式
            String videoIds = StringUtils.join(videoList.toArray(), ",");
            //向request设置视频id
            request.setVideoIds(videoIds);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(201, "删除视频失败");
        }
    }
}
