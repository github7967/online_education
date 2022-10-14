package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.feignclient.VodClientService;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author LiSong
 * @since 2022-10-13
 */
@Component
public class VodClientServiceImpl implements VodClientService {
    /**
     * 删除课程小节   (降级熔断后执行该方法)
     *
     * @param id   小节id
     * @return
     */
    @Override
    public Result removeAlyVideo(String id) {
        return Result.error().message("删除视频出错了");
    }

    /**
     * 批量删除阿里云视频    (降级熔断后执行该方法)
     *
     * @param videoList     视频id集合
     * @return
     */
    @Override
    public Result deleteBatch(List<String> videoList) {
        return Result.error().message("删除多个视频出错了");
    }
}
