package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.Subject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.listener.SubjectExcelListener;
import com.atguigu.eduservice.mapper.SubjectMapper;
import com.atguigu.eduservice.service.SubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

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
}
