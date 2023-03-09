package com.study.springboot.controller;

import com.study.springboot.entity.MyEntityManager;
import com.study.springboot.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final MyEntityManager em;

    @GetMapping("/create")
    @ResponseBody
    public List<Person> create(){
        return em.create();
    }

    @GetMapping("/update")
    @ResponseBody
    public Person update(){
        return em.update();
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Person> list(){
        return em.selectAll();
    }

    @GetMapping("/delete")
    @ResponseBody
    public List<Person> delete(){
        return em.delete();
    }
}
