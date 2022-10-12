package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.CourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublish;
import com.atguigu.eduservice.mapper.CourseMapper;
import com.atguigu.eduservice.service.ChapterService;
import com.atguigu.eduservice.service.CourseDescriptionService;
import com.atguigu.eduservice.service.CourseService;
import com.atguigu.eduservice.service.VideoService;
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

    @Autowired
    private VideoService videoService;

    @Autowired
    private ChapterService chapterService;

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

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CourseInfo getCourseInfo(String courseId) {
        //1.查询课程表
        Course course = courseMapper.getCourseInfoById(courseId);
        CourseInfo courseInfo = new CourseInfo();
        BeanUtils.copyProperties(course, courseInfo);
        //2.查询描述表
        CourseDescription courseDescription = courseDescriptionService.getCourseDescriptionById(courseId);
        courseInfo.setDescription(courseDescription.getDescription());
        return courseInfo;
    }

    /**
     * 修改课程信息
     *
     * @param courseInfo    课程实体
     * @return  true or false
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateCourseInfo(CourseInfo courseInfo) {
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        course.setBuyCount(0L).setViewCount(0L).setVersion(1L).setStatus("Draft");  //有待修改
        int update = courseMapper.updateCourseInfo(course);
        if (update == 0) {
            throw new GuliException(201, "修改课程信息失败");
        }

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setId(courseInfo.getId());
        courseDescription.setDescription(courseInfo.getDescription());
        courseDescriptionService.updateCourseDescriptionById(courseDescription);
    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param courseId  课程id
     * @return  课程信息实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public CoursePublish pulishCourseInfo(String courseId) {
        return courseMapper.getPulishCourseInfo(courseId);
    }

    /**
     * 课程最终发布
     *
     * @param id  课程id
     * @return
     */
    @Override
    public void publishCourse(String id) {
        Course course = new Course();
        course.setId(id).setStatus("Normal");
        boolean flag = courseMapper.publishCourse(course);
        if (!flag) {
            throw new GuliException(201, "发布失败!");
        }
    }

    /**
     * 根据课程id删除课程
     *
     * @param courseId  课程id
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void removeCourse(String courseId) {
        //1.根据课程id删除小节
        videoService.removeVideoByCourseId(courseId);
        //2.根据课程id删除章节
        chapterService.removeChapterByCourseId(courseId);
        //3.根据课程id删除课程描述
        courseDescriptionService.removeCourseDescriptionByCourseId(courseId);
        //4.根据课程id删除课程
        int result = courseMapper.removeCourseByCourseId(courseId);
        if (result == 0) {
            throw new GuliException(201, "删除课程失败");
        }
    }
}
