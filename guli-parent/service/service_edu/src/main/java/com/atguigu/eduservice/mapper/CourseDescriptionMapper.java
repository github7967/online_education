package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.CourseDescription;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程简介 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Repository //只是为了让service层的注入显示正常而已
public interface CourseDescriptionMapper extends BaseMapper<CourseDescription> {
    /**
     * 添加课程描述信息
     *
     * @param courseDescription    信息实体
     * @return
     */
    void saveCourseDescription(CourseDescription courseDescription);

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    CourseDescription getCourseDescriptionById(String courseId);

    /**
     * 修改课程描述信息
     *
     * @param courseDescription    课程描述实体
     */
    int updateCourseDescriptionById(CourseDescription courseDescription);

    /**
     * 根据课程id删除描述
     *
     * @param courseId  课程id
     * @return
     */
    void removeCourseDescriptionByCourseId(String courseId);
}
