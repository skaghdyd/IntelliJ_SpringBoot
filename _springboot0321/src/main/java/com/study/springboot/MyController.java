package com.study.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {
    @GetMapping("/vue")
    public String vue() {
        return "vue/index"; //호용님 저희 천천히... 즐겁게해요>< 아직 아무것도 안했답니다><
    }
}
