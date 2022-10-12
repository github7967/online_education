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

    List<Video> selectVideoById(@Param("courseId") String courseId);

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
}
