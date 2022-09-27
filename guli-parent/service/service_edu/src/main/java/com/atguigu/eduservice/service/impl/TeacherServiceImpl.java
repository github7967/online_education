package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.Teacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.mapper.TeacherMapper;
import com.atguigu.eduservice.service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2022-09-18
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    /**
     * 查询讲师表中所有数据(rest风格)
     *
     * @return  对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Teacher> findAllTeacher() {
        return teacherMapper.findAllTeacher();
    }

    /**
     * 根据id逻辑删除讲师
     *
     * @return  true or fales
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeTeacher(String id) {
        return teacherMapper.removeTeacher(id);
    }

    /**
     * 分页查询讲师
     *
     * @param current   当前页
     * @param limit     每页记录数
     * @return          实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> pageListTeacherr(long current, long limit) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current, limit);
        //调用方法实现分页(调用方法的时候, 底层封装, 把分页所有数据封装到pageTeacher对象里面)
        teacherMapper.selectMapsPage(pageTeacher, null);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows", records);
        return map;
    }

    /**
     * 分页带条件查询讲师
     *
     * @param current       当前页
     * @param limit         每页记录数
     * @param teacherQuery  条件对象
     * @return              实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Map<String, Object> pageTeacherCondition(long current, long limit, TeacherQuery teacherQuery) {
        //创建page对象
        Page<Teacher> pageTeacher = new Page<>(current, limit);
        //构建条件
        QueryWrapper<Teacher> wrapper = new QueryWrapper<>();
        //判断条件值是否为空, 如果不为空就拼接条件
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if (!StringUtils.isEmpty(teacherQuery.getName())) {
            wrapper.like("name", teacherQuery.getName());   //like:模糊查询
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())) {
            wrapper.eq("level", teacherQuery.getLevel());   //eq:=
        }
        if (!StringUtils.isEmpty(begin)) {
            wrapper.ge("gmt_create", begin);     //ge:>=  gt:>
        }
        if (!StringUtils.isEmpty(end)) {
            wrapper.le("gmt_modified", end);         //le:<=
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        //调用方法实现分页(调用方法的时候, 底层封装, 把分页所有数据封装到pageTeacher对象里面)
        teacherMapper.selectMapsPage(pageTeacher, wrapper);
        long total = pageTeacher.getTotal();
        List<Teacher> records = pageTeacher.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows", records);
        return map;
    }

    /**
     * 添加讲师
     *
     * @param teacher   讲师对象
     * @return      true or false
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean addTeacher(Teacher teacher) {
        return teacherMapper.addTeacher(teacher);
    }

    /**
     * 根据Id查询讲师
     *
     * @param id    讲师Id值
     * @return      实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Teacher getTeacher(String id) {
        return teacherMapper.getTeacher(id);
    }

    /**
     * 根据Id修改讲师
     *
     * @param teacher   实体对象
     * @return      true or false
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }
}
