package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.mapper.VideoMapper;
import com.atguigu.eduservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

}
