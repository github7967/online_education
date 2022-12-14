package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Repository //只是为了让service层的注入显示正常而已
public interface VideoMapper extends BaseMapper<Video> {
    /**
     * 根据课程id查询课程所有的小节
     *
     * @return  小节实体集合
     */
    List<Video> selectVideoById(@Param("courseId") String courseId);

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
    Video getVideoById(String videoId);

    /**
     * 根据课程id查询课程所有的视频id
     *
     * @param courseId  课程id
     * @return  视频id集合
     */
    List<String> selectVideoByIds(String courseId);
}
