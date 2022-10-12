package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
public interface CourseService extends IService<Course> {
    /**
     * 添加课程基本信息
     *
     * @param courseInfo    信息实体
     * @return
     */
    String saveCourseInfo(CourseInfo courseInfo);

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    CourseInfo getCourseInfo(String courseId);

    /**
     * 修改课程信息
     *
     * @param courseInfo    课程实体
     * @return  true or false
     */
    void updateCourseInfo(CourseInfo courseInfo);

    /**
     * 根据课程id查询课程确认信息
     *
     * @param courseId  课程id
     * @return  课程信息实体
     */
    CoursePublish pulishCourseInfo(String courseId);

    /**
     * 课程最终发布
     *
     * @param id  课程id
     * @return
     */
    void publishCourse(String id);

    /**
     * 根据课程id删除课程
     *
     * @param courseId  课程id
     * @return
     */
    void removeCourse(String courseId);
}
