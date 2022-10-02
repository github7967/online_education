package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.CourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.mapper.CourseMapper;
import com.atguigu.eduservice.service.CourseDescriptionService;
import com.atguigu.eduservice.service.CourseService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    /**
     * 添加课程基本信息
     *
     * @param courseInfo    信息实体
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveCourseInfo(CourseInfo courseInfo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        course.setBuyCount(0L).setViewCount(0L).setVersion(1L).setStatus("Draft");
        int rows = courseMapper.saveCourse(course);
        if (rows == 0) {
            //添加失败
            throw new GuliException(201, "添加课程信息失败");
        }
        //获取添加之后的课程id
        String cid = course.getId();
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courseInfo.getDescription()).setId(cid);
        courseDescriptionService.saveCourseDescription(courseDescription);
        return cid;
    }
}
