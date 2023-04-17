package com.study.springboot.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MyController {
    @GetMapping("/")
    fun root(model: Model) {
        model.addAttribute("name", "테스트 이름입니다.")
    }
}