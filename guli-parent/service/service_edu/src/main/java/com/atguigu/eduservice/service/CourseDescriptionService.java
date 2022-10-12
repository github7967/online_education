package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.CourseDescription;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程简介 服务类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
public interface CourseDescriptionService extends IService<CourseDescription> {
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
     * @return  true or false
     */
    void updateCourseDescriptionById(CourseDescription courseDescription);

    /**
     * 根据课程id删除描述
     *
     * @param courseId  课程id
     * @return
     */
    void removeCourseDescriptionByCourseId(String courseId);
}
