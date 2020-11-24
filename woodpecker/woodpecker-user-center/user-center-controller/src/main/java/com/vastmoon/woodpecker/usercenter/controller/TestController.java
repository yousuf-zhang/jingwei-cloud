package com.vastmoon.woodpecker.usercenter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> ClassName: TestController
 * <p> Description: 测试
 *
 * @author yousuf 2020/11/19
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public String test(String code) {
        System.out.println(code);
        return code;
    }
}
