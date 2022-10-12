package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Chapter;
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
public interface ChapterMapper extends BaseMapper<Chapter> {
    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId  课程id
     * @return  课程大纲实体
     */
    List<Chapter> selectlistChapter(String courseId);

    /**
     * 添加章节
     *
     * @param chapter   章节实体
     * @return
     */
    int addChapter(Chapter chapter);

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
    int updateChapterById(Chapter chapter);

    /**
     * 根据id删除章节
     *
     * @param chapterId     章节id
     * @return
     */
    int deleteChapter(String chapterId);

    /**
     * 根据课程id删除章节
     *
     * @param courseId  课程id
     * @return
     */
    void removeChapterByCourseId(String courseId);
}
