package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.entity.subject.OneSubject;
import com.atguigu.eduservice.entity.subject.TwoSubject;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.SubjectMapper;
import com.atguigu.eduservice.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-25
 */
@Service
@Slf4j
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectMapper subjectMapper;

    /**
     * 添加课程分类
     *
     * @param file  Excel文件
     * @return
     */
    @Override
    public void saveSubject(MultipartFile file, SubjectService subjectService) {
        try {
            //获取文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (GuliException e) {
            throw new GuliException(1999, "Read error");
        } catch (Exception e) {
            log.error("Read error: {}", e);
            e.printStackTrace();
        }
    }

    /**
     * 课程列表
     *
     * @return  对象实体
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //查询出所有的一级分类
        QueryWrapper<Subject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<Subject> oneSubjectList = baseMapper.selectList(wrapperOne);
        //查询出所有的二级分类
        List<Subject> twoSubjectList = subjectMapper.getgetAllTwoSubject();
        //创建list集合,用于存储最终数据
        List<OneSubject> finalSubjectList = new ArrayList<>();
        //封装一级分类(查询出来所有的一级分类list集合遍历, 得到每个一级分类对象, 获取每个一级分类对象值, 封装到要求的list集合里面: List<OneSubject> finalSubjectList)
        for (int i = 0; i < oneSubjectList.size(); i++) {
            Subject subject = oneSubjectList.get(i);
//            String id = subject.getId();
//            String title = subject.getTitle();
//            OneSubject oneSubject = new OneSubject();
//            oneSubject.setId(id);
//            oneSubject.setTitle(title);
//            finalSubjectList.add(oneSubject);
            //简写方式
            OneSubject oneSubject = new OneSubject();
            BeanUtils.copyProperties(subject, oneSubject);
            finalSubjectList.add(oneSubject);
            //二级分类
            List<TwoSubject> list = new ArrayList<>();
            for (int j = 0; j < twoSubjectList.size(); j++) {
                Subject tsubject = twoSubjectList.get(j);
                if (subject.getId().equals(tsubject.getParentId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(tsubject, twoSubject);
                    list.add(twoSubject);
                }
            }
            oneSubject.setChildren(list);
        }
        return finalSubjectList;
    }
}
