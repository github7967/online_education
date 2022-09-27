package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiSong
 * @since 2022-09-21
 */
@Api(description = "登陆管理")
@RestController
@CrossOrigin    //解决跨域问题
@RequestMapping("/eduservice/user")
public class LoginController {
    /**
     * login
     *
     * @return
     */
    @ApiOperation(value = "登陆")
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Result login() {
        return Result.ok().data("token", "admin");
    }

    /**
     * info
     *
     * @return
     */
    @ApiOperation(value = "获取讲师信息")
    @RequestMapping(value = "info", method = RequestMethod.GET)
    public Result info() {
        return Result.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
