package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.feignclient.VodClientService;
import com.atguigu.eduservice.mapper.VideoMapper;
import com.atguigu.eduservice.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private VodClientService vodClientService;

    @Override
    public List<Video> selectlistVideo(String courseId) {
        return videoMapper.selectVideoById(courseId);
    }

    /**
     * 根据chapterid章节id查询小节表
     *
     * @param chapterId     章节id
     * @return
     */
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
        //1.根据课程id查询课程所有的视频id
        List<String> videoList = videoMapper.selectVideoByIds(courseId);
        //判断非空
        if (videoList.isEmpty()) {
            throw new GuliException(201, "videoList is null");
        }
        vodClientService.deleteBatch(videoList);
        videoMapper.removeVideoByCourseId(courseId);
    }

    /**
     * 根据小节id获取到视频id
     *
     * @param videoId   小节id
     * @return  video实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String getVideoById(String videoId) {
        Video video = videoMapper.getVideoById(videoId);
        String videoSourceId = video.getVideoSourceId();
        return videoSourceId;
    }
}
