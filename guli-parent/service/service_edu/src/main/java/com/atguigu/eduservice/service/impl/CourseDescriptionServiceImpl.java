package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.CourseDescription;
import com.atguigu.eduservice.mapper.CourseDescriptionMapper;
import com.atguigu.eduservice.service.CourseDescriptionService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {
    @Autowired
    private CourseDescriptionMapper courseDescriptionMapper;

    /**
     * 添加课程描述信息
     *
     * @param courseDescription    信息实体
     * @return
     */
    @Override
    public void saveCourseDescription(CourseDescription courseDescription) {
        courseDescriptionMapper.saveCourseDescription(courseDescription);
    }

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    @Override
    public CourseDescription getCourseDescriptionById(String courseId) {
        return courseDescriptionMapper.getCourseDescriptionById(courseId);
    }

    /**
     * 修改课程描述信息
     *
     * @param courseDescription    课程描述实体
     */
    @Override
    public void updateCourseDescriptionById(CourseDescription courseDescription) {
        int updateDescription = courseDescriptionMapper.updateCourseDescriptionById(courseDescription);
        if (updateDescription == 0) {
            throw new GuliException(201, "修改课程描述信息失败");
        }
    }

    /**
     * 根据课程id删除描述
     *
     * @param courseId  课程id
     * @return
     */
    @Override
    public void removeCourseDescriptionByCourseId(String courseId) {
        courseDescriptionMapper.removeCourseDescriptionByCourseId(courseId);
    }
}
