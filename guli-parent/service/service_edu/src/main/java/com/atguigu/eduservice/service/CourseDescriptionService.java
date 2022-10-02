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
}
