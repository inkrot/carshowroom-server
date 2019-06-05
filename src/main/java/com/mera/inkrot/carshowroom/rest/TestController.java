package com.mera.inkrot.carshowroom.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {
    @GetMapping("/")
    public @ResponseBody String test() {
        return "<font size=\"6\" color=\"blue\" face=\"verdana\">Test the deployment</font>";
    }
}
