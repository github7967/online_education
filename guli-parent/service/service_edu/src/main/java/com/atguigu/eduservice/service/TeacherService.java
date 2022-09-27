package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2022-09-18
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 查询讲师表中所有数据(rest风格)
     *
     * @return  对象集合
     */
    List<Teacher> findAllTeacher();

    /**
     * 根据id逻辑删除讲师
     *
     * @return  true or fales
     */
    boolean removeTeacher(String id);

    /**
     * 分页查询讲师
     *
     * @param current   当前页
     * @param limit     每页记录数
     * @return          实体对象
     */
    Map<String, Object> pageListTeacherr(long current, long limit);

    /**
     * 分页带条件查询讲师
     *
     * @param current       当前页
     * @param limit         每页记录数
     * @param teacherQuery  条件对象
     * @return              实体对象
     */
    Map<String, Object> pageTeacherCondition(long current, long limit, TeacherQuery teacherQuery);

    /**
     * 添加讲师
     *
     * @param teacher   讲师对象
     * @return      true or false
     */
    boolean addTeacher(Teacher teacher);

    /**
     * 根据Id查询讲师
     *
     * @param id    讲师Id值
     * @return      实体对象
     */
    Teacher getTeacher(String id);

    /**
     * 根据Id修改讲师
     *
     * @param teacher   实体对象
     * @return      true or false
     */
    boolean updateTeacher(Teacher teacher);
}
