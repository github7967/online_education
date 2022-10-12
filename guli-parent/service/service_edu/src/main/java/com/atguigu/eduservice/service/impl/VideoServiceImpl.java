package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.mapper.VideoMapper;
import com.atguigu.eduservice.service.VideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    @Autowired
    private VideoMapper videoMapper;

    @Override
    public List<Video> selectlistVideo(String courseId) {
        return videoMapper.selectVideoById(courseId);
    }

    @Override
    public int selectVideoCount(String chapterId) {
        return videoMapper.selectVideoCount(chapterId);
    }

    /**
     * 添加课程小节
     *
     * @param video     小节实体
     * @return
     */
    @Override
    public void addVideo(Video video) {
        video.setPlayCount(0L).setIsFree(true).setDuration(0f).setStatus("Draft").setVersion(1L).setSize(0L);
        videoMapper.addVideo(video);
    }

    /**
     * 删除课程小节
     *
     * @param videoId   小节id
     * @return
     */
    @Override
    public void deleteVideoById(String videoId) {
        videoMapper.deleteVideoById(videoId);
    }

    /**
     * 根据课程id删除小节
     *
     * @param courseId  课程id
     * @return
     */
    @Override
    public void removeVideoByCourseId(String courseId) {
        videoMapper.removeVideoByCourseId(courseId);
    }
}
