package com.vastmoon.woodpecker.auth.controller;

import com.vastmoon.sparrow.client.dto.Rest;
import com.vastmoon.sparrow.client.dto.RestBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p> ClassName: SecurityController
 * <p> Description: 测试页面
 *
 * @author yousuf 2020/12/1
 */
@Controller
@RequestMapping("/sso")
public class SecurityController {
    @GetMapping("/login")
    public String login() {
        return "ftl/login";
    }

}
