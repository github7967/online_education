package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author LiSong
 * @since 2022-09-25
 */
public interface SubjectService extends IService<Subject> {
    /**
     * 添加课程分类
     *
     * @param file  Excel文件
     * @return
     */
    void saveSubject(MultipartFile file, SubjectService subjectService);
}
