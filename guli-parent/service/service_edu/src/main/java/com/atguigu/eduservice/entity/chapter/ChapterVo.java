package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiSong
 * @since 2022-10-06
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    //表示小节
    private List<VideoVo> children = new ArrayList<>();
}
