package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Chapter;
import com.atguigu.eduservice.entity.Video;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.ChapterMapper;
import com.atguigu.eduservice.service.ChapterService;
import com.atguigu.eduservice.service.VideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    @Autowired
    private ChapterMapper chapterMapper;

    @Autowired
    private VideoService videoService;

    /**
     * 根据课程id查询课程大纲列表
     *
     * @param courseId  课程id
     * @return  课程大纲实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {
        //1.根据课程id查询课程里面所有的章节
        List<Chapter> chapterList = chapterMapper.selectlistChapter(courseId);
        //2.根据课程id查询课程里面所有的小节
        List<Video> videoList = videoService.selectlistVideo(courseId);
        //创建list集合, 用于最终封装
        List<ChapterVo> finalList = new ArrayList<>();
        //3.遍历查询章节list集合进行封装
        for (int i = 0; i < chapterList.size(); i++) {
            Chapter chapter = chapterList.get(i);
            //把Chapter对象里面的值复制到ChapterVo里面
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            //把chapterVo放到最终list集合中
            finalList.add(chapterVo);

            //4.遍历查询小节list集合, 进行封装
            //创建集合, 用于封装章节中的小节
            List<VideoVo> videolist = new ArrayList<>();
            for (int j = 0; j < videoList.size(); j++) {
                Video video = videoList.get(j);
                //判断: 小节里面的chapterID和章节里面的id是否一样
                if (video.getChapterId().equals(chapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(video, videoVo);
                    //放到小节封装的集合中
                    videolist.add(videoVo);
                }
            }
            //把封装之后的小节list集合放到章节对象里面
            chapterVo.setChildren(videolist);
        }

        return finalList;
    }

    /**
     * 添加章节
     *
     * @param chapter   章节实体
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addChapter(Chapter chapter) {
        if (StringUtils.isEmpty(chapter)) {
            throw new GuliException(201, "chapter is null");
        }
        int rows = chapterMapper.addChapter(chapter);
        if (rows == 0) {
            throw new GuliException(201, "添加章节失败");
        }
    }

    /**
     * 根据id查询章节
     *
     * @param chapterId     章节id
     * @return  章节实体
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Chapter getChapterInfo(String chapterId) {
        Chapter chapter = chapterMapper.getChapterInfo(chapterId);
        if (StringUtils.isEmpty(chapter)) {
            return null;
        }
        return chapter;
    }

    /**
     * 根据id修改章节
     *
     * @param chapter   章节实体
     * @return  true of false
     */
    @Override
    public void updateChapterById(Chapter chapter) {
        int row = chapterMapper.updateChapterById(chapter);
        if (row == 0) {
            throw new GuliException(201, "章节修改失败");
        }
    }

    /**
     * 根据id删除章节
     *
     * @param chapterId     章节id
     * @return
     */
    @Override
    public boolean deleteChapter(String chapterId) {
        //根据chapterid章节id查询小节表, 如果有数据, 则不允许进行删除
        int count = videoService.selectVideoCount(chapterId);
        //判断是否有小节
        if (count > 0) {
            throw new GuliException(201, "该章节下有小节, 无法进行删除");
        }else {
            int delete = chapterMapper.deleteChapter(chapterId);
            return delete > 0;
        }
    }

    /**
     * 根据课程id删除章节
     *
     * @param courseId  课程id
     * @return
     */
    @Override
    public void removeChapterByCourseId(String courseId) {
        chapterMapper.removeChapterByCourseId(courseId);
    }
}
