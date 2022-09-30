package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-25
 */
@Repository
public interface SubjectMapper extends BaseMapper<Subject> {
    /**
     * 课程列表
     *
     * @return  对象实体
     */
    List<Subject> getgetAllTwoSubject();
}
