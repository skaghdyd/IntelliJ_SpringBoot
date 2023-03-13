package com.study.springboot.controller;

import com.study.springboot.dto.GradeDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @GetMapping("/hello")
    public void hello(Model model, String name){
        model.addAttribute("name", name);
    }

//    @PostMapping("/review")
//    @ResponseBody
//    public GradeDto review(@RequestBody GradeDto gradeDto){
//        System.out.println("===========>"+gradeDto);
//        return gradeDto;
//    }
    @PostMapping("/review")
    public ResponseEntity<Long> review(@RequestBody GradeDto gradeDto){
        System.out.println("===========>"+gradeDto);
        return new ResponseEntity<>(gradeDto.getId(), HttpStatus.OK);
    }
}
