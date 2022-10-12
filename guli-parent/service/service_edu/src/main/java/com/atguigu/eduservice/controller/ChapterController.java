package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.Chapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.service.ChapterService;
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
@Api(description = "课程章节管理")
@RestController
@CrossOrigin    //解决跨域问题
@RequestMapping("/eduservice/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId  课程id
     * @return  课程大纲实体
     */
    @ApiOperation(value = "根据课程id查询章节小节")
    @RequestMapping(value = "/getChapterVideo/{courseId}", method = RequestMethod.GET)
    public Result getChapterVideo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVideoByCourseId(courseId);
        return Result.ok().data("allChapterVideo", list);
    }

    /**
     * 添加章节
     *
     * @param chapter   章节实体
     * @return
     */
    @ApiOperation(value = "添加章节")
    @RequestMapping(value = "/addChapter", method = RequestMethod.POST)
    public Result addChapter(@RequestBody Chapter chapter) {
        chapterService.addChapter(chapter);
        return Result.ok();
    }

    /**
     * 根据id查询章节
     *
     * @param chapterId     章节id
     * @return  章节实体
     */
    @ApiOperation(value = "根据id查询章节")
    @RequestMapping(value = "/getChapterInfo/{chapterId}", method = RequestMethod.GET)
    public Result getChapterInfo(
            @ApiParam(name = "chapterId", value = "章节id", required = true)
            @PathVariable String chapterId) {
        Chapter chapter = chapterService.getChapterInfo(chapterId);
        return Result.ok().data("chapter", chapter);
    }

    /**
     * 根据id修改章节
     *
     * @param chapter   章节实体
     * @return  true of false
     */
    @ApiOperation(value = "根据id修改章节")
    @RequestMapping(value = "/updateChapter", method = RequestMethod.POST)
    public Result updateChapter(@RequestBody Chapter chapter) {
        chapterService.updateChapterById(chapter);
        return Result.ok();
    }

    /**
     * 根据id删除章节
     *
     * @param chapterId     章节id
     * @return
     */
    @ApiOperation(value = "根据id删除章节")
    @RequestMapping(value = "/{chapterId}", method = RequestMethod.DELETE)
    public Result deleteChapter(
            @ApiParam(name = "chapterId", value = "章节id", required = true)
            @PathVariable String chapterId) {
        boolean flag = chapterService.deleteChapter(chapterId);
        if (flag) {
            return Result.ok();
        }else {
            return Result.error();
        }
    }
}

