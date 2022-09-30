package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author LiSong
 * @since 2022-09-25
 */
@Api(description = "课程管理")
@RestController
@CrossOrigin
@RequestMapping("/eduservice/subject")
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    /**
     * 添加课程分类
     *
     * @param file  Excel文件
     * @return
     */
    @ApiOperation(value = "添加课程分类")
    @RequestMapping(value = "addsubject", method = RequestMethod.POST)
    public Result addSubject(MultipartFile file) {
        //上传过来的Excel文件
        subjectService.saveSubject(file, subjectService);
        return Result.ok();
    }

    /**
     * 课程列表
     *
     * @return  对象实体
     */
    @RequestMapping(value = "getAllSubject", method = RequestMethod.GET)
    //list集合泛型是一级分类
    public Result getAllSubject() {
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
        return Result.ok().data("list", list);
    }
}

