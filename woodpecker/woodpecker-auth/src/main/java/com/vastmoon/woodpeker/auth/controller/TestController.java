package com.vastmoon.woodpeker.auth.controller;

import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> ClassName: TestController
 * <p> Description: // TODO
 *
 * @author yousuf 2020/11/19
 */
@RestController
public class TestController {
    @GetMapping("/oauth/test")
    public String test(HttpRequest request) {
        System.out.println(request);
        return "test";
    }
}
