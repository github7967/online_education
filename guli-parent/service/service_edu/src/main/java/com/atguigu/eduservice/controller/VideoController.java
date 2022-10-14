package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.feignclient.VodClientService;
import com.atguigu.eduservice.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Api(description = "课程小节管理")
@CrossOrigin    //解决跨域问题
@RestController
@RequestMapping("/eduservice/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @Autowired
    private VodClientService vodClientService;

    /**
     * 添加课程小节
     *
     * @param video     小节实体
     * @return
     */
    @RequestMapping(value = "/addVideo", method = RequestMethod.POST)
    public Result addVideo(@RequestBody Video video) {
        videoService.addVideo(video);
        return Result.ok();
    }

    /**
     * 删除课程小节
     *
     * @param videoId   小节id
     * @return
     */
    @RequestMapping(value = "/deleteVideo/{videoId}", method = RequestMethod.DELETE)
    public Result deleteVideo(
            @ApiParam(name = "videoId", value = "小节id", required = true)
            @PathVariable String videoId) {
        //根据小节id获取到视频id, 调用方法实现视频删除
        String videoSourceId = videoService.getVideoById(videoId);
        //非空判断
        if (videoSourceId !="" && videoSourceId != null) {
            //根据视频id,远程调用实现视频删除
            Result result = vodClientService.removeAlyVideo(videoSourceId);
            if (result.getCode() == 201) {
                throw new GuliException(201, "删除视频失败, 熔断器...");
            }
        }
        videoService.deleteVideoById(videoId);
        return Result.ok();
    }
}

