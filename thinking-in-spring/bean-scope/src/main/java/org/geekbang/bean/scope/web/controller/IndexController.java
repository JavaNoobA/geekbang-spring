package org.geekbang.bean.scope.web.controller;

import org.geekbang.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页 Spring Web Controller
 * Created by eru on 2020/7/25.
 */
@Controller
public class IndexController {

    @Autowired
    private User user;

    @GetMapping("index.html")
    public String index(Model model){
        model.addAttribute("user", user);
        return "index";
    }
}
