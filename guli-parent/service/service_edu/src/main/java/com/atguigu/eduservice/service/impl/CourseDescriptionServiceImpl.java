package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.CourseDescription;
import com.atguigu.eduservice.mapper.CourseDescriptionMapper;
import com.atguigu.eduservice.service.CourseDescriptionService;
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
}
