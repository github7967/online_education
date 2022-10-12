package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.util.StringUtils;

/**
 * @author LiSong
 * @since 2022-09-25
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //由于"SubjectExcelListener"对象不能交给spring进行管理, 需要自己new, 不能注入其他对象,不能实现数据库操作,解决办法如下:
    public SubjectService subjectService;
    public SubjectExcelListener() { }
    public SubjectExcelListener(SubjectService subjectService) { this.subjectService = subjectService; }

    //读取Excel内容, 一行一行进行读取
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(201, "File data is empty");
        }
        //一行一行读取, 每次读取都有两个值, 第一个值一级分类, 第二个值二级分类
        //判断一级分类是否重复
        Subject existOneSubject = this.exisOneSubject(subjectService, subjectData.getOneSubjectName());
        if (StringUtils.isEmpty(existOneSubject)) {
            existOneSubject = new Subject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());   //一级分类名称
            //进行添加  (后期修改)
            subjectService.save(existOneSubject);
            subjectService.saveOneSubject(existOneSubject);
        }
        //添加二级分类, 判断一级分类是否重复
        String pid = existOneSubject.getId();   //获取一级分类id值
        Subject exisTwoSubject = this.exisTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (StringUtils.isEmpty(exisTwoSubject)) {
            exisTwoSubject = new Subject();
            exisTwoSubject.setParentId(pid);
            exisTwoSubject.setTitle(subjectData.getTwoSubjectName());   //二级分类名称
            //进行添加  (后期修改)
            subjectService.save(exisTwoSubject);
        }
    }

    //判断一级分类不能重复添加
    private Subject exisOneSubject(SubjectService subjectService, String name) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        Subject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private Subject exisTwoSubject(SubjectService subjectService, String name, String pid) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        Subject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
