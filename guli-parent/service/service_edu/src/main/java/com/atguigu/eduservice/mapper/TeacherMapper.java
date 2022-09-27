package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2022-09-18
 */
@Repository //只是为了让service层的注入显示正常而已
public interface TeacherMapper extends BaseMapper<Teacher> {
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
    boolean removeTeacher(@Param("id") String id);

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
