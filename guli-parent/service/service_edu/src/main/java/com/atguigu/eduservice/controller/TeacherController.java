package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-09-18
 */
@Api(description = "讲师管理")
@RestController
@CrossOrigin    //解决跨域问题
@RequestMapping("/eduservice/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 查询讲师表中所有数据(rest风格)
     *
     * @return  对象集合
     */
    @ApiOperation(value = "查询所有讲师")
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAllTeacher() {
        //调用service的方法实现查询所有的操作
        List<Teacher> list = teacherService.findAllTeacher();
        return Result.ok().data("items", list);
    }

    /**
     * 根据id逻辑删除讲师
     *
     * @return  true or false
     */
    @ApiOperation(value = "根据Id逻辑删除讲师")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result removeTeacher(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable("id") String id) {
        boolean flag = teacherService.removeTeacher(id);
        if (flag) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 分页查询讲师
     *
     * @param current   当前页
     * @param limit     每页记录数
     * @return          实体对象
     */
    @ApiOperation(value = "分页查询讲师")
    @RequestMapping(value = "/pageTeacher/{current}/{limit}", method = RequestMethod.GET)
    public Result pageListTeacher(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit) {
        /*try {
            int a = 1/0;
        } catch (Exception e) {
            throw new GuliException(400,"执行了自定义异常处理");
        }*/
        Map<String, Object> teacher = teacherService.pageListTeacherr(current, limit);
        return Result.ok().data(teacher);
    }

    /**
     * 分页带条件查询讲师
     *
     * @param current       当前页
     * @param limit         每页记录数
     * @param teacherQuery  条件对象
     * @return              实体对象
     */
    @ApiOperation(value = "分页带条件查询讲师")
    @RequestMapping(value = "/pageTeacherCondition/{current}/{limit}", method = RequestMethod.POST)
    public Result pageTeacherCondition(
            @ApiParam(name = "current", value = "当前页", required = true)
            @PathVariable long current,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable long limit,
            @ApiParam(name = "teacherQuery", value = "条件对象")
            @RequestBody(required = false) TeacherQuery teacherQuery) {
        Map<String, Object> queryTeacher = teacherService.pageTeacherCondition(current, limit, teacherQuery);
        return Result.ok().data("total", queryTeacher.get("total")).data("rows", queryTeacher.get("rows"));
    }

    /**
     * 添加讲师
     *
     * @param teacher   讲师对象
     * @return      true or false
     */
    @ApiOperation(value = "添加讲师")
    @RequestMapping(value = "/addTeacher", method = RequestMethod.POST)
    public Result addTeacher(
            @ApiParam(name = "teacher", value = "实体对象")
            @RequestBody Teacher teacher) {
        teacher.setIsDeleted(false);
        boolean save = teacherService.addTeacher(teacher);
        if (save) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    /**
     * 根据Id查询讲师
     *
     * @param id    讲师Id值
     * @return      实体对象
     */
    @ApiOperation(value = "根据Id查询讲师")
    @RequestMapping(value = "/getTeacher/{id}", method = RequestMethod.GET)
    public Result getTeacher(
            @ApiParam(name = "id", value = "讲师Id", required = true)
            @PathVariable("id") String id) {
        Teacher teacher = teacherService.getTeacher(id);
        return Result.ok().data("teacher", teacher);
    }

    /**
     * 修改讲师
     *
     * @param teacher   实体对象
     * @return      true or false
     */
    @ApiOperation(value = "修改讲师")
    @RequestMapping(value = "/updateTeacher", method = RequestMethod.POST)
    public Result updateTeacher(
            @ApiParam(name = "teacher", value = "实体对象")
            @RequestBody Teacher teacher) {
        boolean flag = teacherService.updateTeacher(teacher);
        if (flag) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

