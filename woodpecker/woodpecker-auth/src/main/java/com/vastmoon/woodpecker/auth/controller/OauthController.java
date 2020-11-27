package com.vastmoon.woodpecker.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

/**
 * <p> ClassName: OauthController
 * <p> Description: 获取信息
 *
 * @author yousuf 2020/11/27
 */
@Controller
public class OauthController {
    @ResponseBody
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }
}
