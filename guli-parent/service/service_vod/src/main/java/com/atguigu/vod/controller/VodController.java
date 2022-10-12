package com.atguigu.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.atguigu.commonutils.Result;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.utils.ConstantVodUtils;
import com.atguigu.vod.utils.InitVodClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiSong
 * @since 2022-10-11
 */
@Api(description = "视频管理")
@RestController
@CrossOrigin    //解决跨域问题
@RequestMapping("/eduvod/video")
public class VodController {
    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     *
     * @param file  视频文件
     * @return
     */
    @ApiOperation(value = "上传视频到阿里云")
    @RequestMapping(value = "/uploadAlyiVideo", method = RequestMethod.POST)
    public Result uploadAlyiVideo(MultipartFile file) {
        String videoId = vodService.uploadAly(file);
        return Result.ok().data("videoId", videoId);
    }

    @ApiOperation(value = "删除阿里云视频")
    @RequestMapping(value = "/removeAlyVideo/{id}", method = RequestMethod.DELETE)
    public Result removeAlyVideo(
            @ApiParam(name = "id", value = "视频id", required = true)
            @PathVariable String id) {
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建删除视频request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id
            request.setVideoIds(id);
            //调用初始化对象的方法实现删除
            client.getAcsResponse(request);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(201, "删除视频失败");
        }
    }
}
