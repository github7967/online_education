package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.service.VideoService;
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
        videoService.deleteVideoById(videoId);
        return Result.ok();
    }
}

