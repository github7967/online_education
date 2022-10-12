package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CoursePublish;
import com.atguigu.eduservice.entity.vo.CourseQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Repository //只是为了让service层的注入显示正常而已
public interface CourseMapper extends BaseMapper<Course> {
    /**
     * 添加课程信息
     *
     * @param course
     */
    int saveCourse(Course course);

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    Course getCourseInfoById(String courseId);

    /**
     * 修改课程信息
     *
     * @param course    课程实体
     * @return  true or false
     */
    int updateCourseInfo(Course course);

    /**
     * 根据课程id查询课程确认信息
     *
     * @param courseId  课程id
     * @return  课程信息实体
     */
    CoursePublish getPulishCourseInfo(String courseId);

    /**
     * 课程最终发布
     *
     * @param course  课程实体
     * @return
     */
    boolean publishCourse(Course course);

    /**
     * 根据课程id删除课程
     *
     * @param courseId  课程id
     * @return
     */
    int removeCourseByCourseId(String courseId);

    /**
     * 分页带条件查询课程   (待完善)
     *
     * @return  课程实体
     */
    List<Course> getCourseList(long limit, long current, CourseQuery courseQuery);
}
