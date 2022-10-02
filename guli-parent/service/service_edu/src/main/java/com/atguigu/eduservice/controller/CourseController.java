package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.vo.CourseInfo;
import com.atguigu.eduservice.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
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
        courseService.saveCourseInfo(courseInfo);
        return Result.ok();
    }
}

