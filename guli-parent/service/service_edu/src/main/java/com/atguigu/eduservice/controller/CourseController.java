package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.Course;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.entity.vo.CoursePublish;
import com.atguigu.eduservice.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Api(description = "课程管理")
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin    //解决跨域问题
public class CourseController {
    @Autowired
    private CourseService courseService;

    /**
     * 添加课程基本信息
     *
     * @param courseInfo    信息实体
     * @return
     */
    @ApiOperation(value = "添加课程基本信息")
    @RequestMapping(value = "addCourseInfo", method = RequestMethod.POST)
    public Result addCourseInfo(@RequestBody CourseInfo courseInfo) {
        String id = courseService.saveCourseInfo(courseInfo);
        return Result.ok().data("courseId", id);
    }

    /**
     * 根据课程id查询课程基本信息
     *
     * @param courseId  课程id
     * @return  课程基本信息实体
     */
    @ApiOperation(value = "根据课程id查询课程基本信息")
    @RequestMapping(value = "/getCourseInfo/{courseId}", method = RequestMethod.GET)
    public Result getCourseInfo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        CourseInfo courseInfo = courseService.getCourseInfo(courseId);
        return Result.ok().data("courseInfoVo", courseInfo);
    }

    /**
     * 修改课程信息
     *
     * @param courseInfo    课程实体
     * @return  true or false
     */
    @ApiOperation(value = "修改课程信息")
    @RequestMapping(value = "/updateCourseInfo", method = RequestMethod.POST)
    public Result updateCourseInfo(@RequestBody CourseInfo courseInfo) {
        courseService.updateCourseInfo(courseInfo);
        return Result.ok();
    }

    /**
     * 根据课程id查询课程确认信息
     *
     * @param courseId  课程id
     * @return  课程信息实体
     */
    @ApiOperation(value = "根据课程id查询课程确认信息")
    @RequestMapping(value = "/getPulishCourseInfo/{courseId}", method = RequestMethod.GET)
    public Result getPulishCourseInfo(@ApiParam(name = "courseId", value = "课程id", required = true)
                                      @PathVariable String courseId) {
        CoursePublish coursePublish = courseService.pulishCourseInfo(courseId);
        return Result.ok().data("coursePublish", coursePublish);
    }

    /**
     * 课程最终发布
     *
     * @param id  课程id
     * @return
     */
    @RequestMapping(value = "/publishCourse/{id}", method = RequestMethod.POST)
    public Result publishCourse(
            @ApiParam(name = "id", value = "课程id", required = true)
            @PathVariable String id) {
        courseService.publishCourse(id);
        return Result.ok();
    }

    /**
     * 分页带条件查询课程   (待完善)    SELECT * FROM `course` order by gmt_create desc limit 0, 3
     *
     * @return  课程实体
     */
    @RequestMapping(value = "/getCourseList", method = RequestMethod.GET)
    public Result getCourseList() {
        List<Course> list = courseService.list(null);
        return Result.ok().data("list",list);
    }

    /**
     * 根据课程id删除课程   (前端后期完成)
     *
     * @param courseId  课程id
     * @return
     */
    @ApiOperation(value = "根据课程id删除课程")
    @RequestMapping(value = "/deleteCourse/{courseId}", method = RequestMethod.DELETE)
    public Result deleteCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        courseService.removeCourse(courseId);
        return Result.ok();
    }
}

