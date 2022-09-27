package com.atguigu.oss.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LiSong
 * @since 2022-09-23
 */
@Api(description = "文件上传管理")
@RestController
@CrossOrigin    //解决跨域问题
@RequestMapping("/eduoss/fileoss")
public class OssController {
    @Autowired
    private OssService ossService;

    /**
     * 上传头像
     *
     * @param file  需要上传的文件
     * @return  文件保存的路径
     */
    @ApiOperation(value = "文件上传")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Result uploadOssFile(
            @ApiParam(name = "file", value = "文件", required = true) MultipartFile file) {
        //获取上传文件, 并返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url", url);
    }
}
