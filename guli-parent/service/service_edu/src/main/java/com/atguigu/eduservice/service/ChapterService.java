package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Chapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
public interface ChapterService extends IService<Chapter> {
    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId  课程id
     * @return  课程大纲实体
     */
    List<ChapterVo> getChapterVideoByCourseId(String courseId);

    /**
     * 添加章节
     *
     * @param chapter   章节实体
     * @return
     */
    void addChapter(Chapter chapter);

    /**
     * 根据id查询章节
     *
     * @param chapterId     章节id
     * @return  章节实体
     */
    Chapter getChapterInfo(String chapterId);

    /**
     * 根据id修改章节
     *
     * @param chapter   章节实体
     * @return  true of false
     */
    void updateChapterById(Chapter chapter);

    /**
     * 根据id删除章节
     *
     * @param chapterId     章节id
     * @return
     */
    boolean deleteChapter(String chapterId);

    /**
     * 根据课程id删除章节
     *
     * @param courseId  课程id
     * @return
     */
    void removeChapterByCourseId(String courseId);
}
