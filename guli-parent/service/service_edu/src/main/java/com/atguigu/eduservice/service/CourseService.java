package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CourseInfo;
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
}
