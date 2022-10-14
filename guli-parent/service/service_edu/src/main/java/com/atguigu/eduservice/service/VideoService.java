package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Video;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
public interface VideoService extends IService<Video> {

    List<Video> selectlistVideo(String courseId);

    /**
     * 根据chapterid章节id查询小节表
     *
     * @param chapterId     章节id
     * @return
     */
    int selectVideoCount(String chapterId);

    /**
     * 添加课程小节
     *
     * @param video     小节实体
     * @return
     */
    void addVideo(Video video);

    /**
     * 删除课程小节
     *
     * @param videoId   小节id
     * @return
     */
    void deleteVideoById(String videoId);

    /**
     * 根据课程id删除小节
     *
     * @param courseId  课程id
     * @return
     */
    void removeVideoByCourseId(String courseId);

    /**
     * 根据小节id获取到视频id
     *
     * @param videoId   小节id
     * @return  video实体
     */
    String getVideoById(String videoId);
}
