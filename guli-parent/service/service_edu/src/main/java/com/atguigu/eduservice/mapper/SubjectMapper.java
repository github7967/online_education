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
@Repository //只是为了让service层的注入显示正常而已
public interface SubjectMapper extends BaseMapper<Subject> {
    /**
     * 查询课程分类列表
     *
     * @return  对象实体
     */
    List<Subject> getgetAllTwoSubject();

    /**
     *  添加一级分类
     *
     * @param existOneSubject   一级分类实体
     */
    void saveOneSubject(Subject existOneSubject);
}
