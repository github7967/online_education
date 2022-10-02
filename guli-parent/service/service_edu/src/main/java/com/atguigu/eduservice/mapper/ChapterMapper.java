package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Chapter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Repository //只是为了让service层的注入显示正常而已
public interface ChapterMapper extends BaseMapper<Chapter> {

}
