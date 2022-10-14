package com.atguigu.vod.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    /**
     * 根据视频id删除阿里云视频
     *
     * @param id    视频id
     * @return
     */
    @ApiOperation(value = "删除阿里云视频")
    @RequestMapping(value = "/removeAlyVideo/{id}", method = RequestMethod.DELETE)
    public Result removeAlyVideo(
            @ApiParam(name = "id", value = "视频id", required = true)
            @PathVariable String id) {
        vodService.removeAlyVideoById(id);
        return Result.ok();
    }

    /**
     * 批量删除阿里云视频
     *
     * @param videoList     视频id集合
     * @return
     */
    @RequestMapping(value = "/delete-batch", method = RequestMethod.DELETE)
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList) {
        vodService.removeMoreAlyVideo(videoList);
        return Result.ok();
    }
}
