package com.atguigu.eduservice.feignclient;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.service.impl.VodClientServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author LiSong
 * @since 2022-10-13
 */
@Component  //交给spring管理
@FeignClient(value = "service-vod", fallback = VodClientServiceImpl.class)  //出错后执行实现类里面的方法
public interface VodClientService {
    /**
     * 删除课程小节
     *
     * @param id   小节id
     * @return
     */
    @ApiOperation(value = "删除阿里云视频")
    @RequestMapping(value = "/eduvod/video/removeAlyVideo/{id}", method = RequestMethod.DELETE)
    public Result removeAlyVideo(
            @ApiParam(name = "id", value = "视频id", required = true)
            @PathVariable("id") String id);

    /**
     * 批量删除阿里云视频
     *
     * @param videoList     视频id集合
     * @return
     */
    @RequestMapping(value = "/eduvod/video/delete-batch", method = RequestMethod.DELETE)
    public Result deleteBatch(@RequestParam("videoList") List<String> videoList);
}
