package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.Video;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 课程视频 Mapper 接口
 * </p>
 *
 * @author LiSong
 * @since 2022-09-30
 */
@Repository //只是为了让service层的注入显示正常而已
public interface VideoMapper extends BaseMapper<Video> {

}
