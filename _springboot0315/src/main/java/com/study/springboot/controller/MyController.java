package com.study.springboot.controller;

import com.study.springboot.dto.MyMongoData;
import com.study.springboot.repository.MyMongoDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {
    private final MyMongoDataRepository myMongoDataRepository;

    @GetMapping("/")
    public ModelAndView index(ModelAndView mav){
        mav.setViewName("index");
        mav.addObject("title", "Find Page");
        mav.addObject("msg", "MyMongoData의 예제입니다.");

        List<MyMongoData> list = myMongoDataRepository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @PostMapping("/")
    public ModelAndView form(
            @RequestParam("name") String name,
            @RequestParam("memo") String memo) {
        MyMongoData myMongoData = new MyMongoData(name, memo);
        myMongoDataRepository.save(myMongoData);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/find")
    public ModelAndView find(ModelAndView mav) {
        mav.setViewName("find");
        mav.addObject("title", "Find Page");
        mav.addObject("msg", "MyMongoData의 예제입니다.");
        mav.addObject("value", "");
        List<MyMongoData> list = myMongoDataRepository.findAll();
        mav.addObject("datalist", list);
        return mav;
    }

    @PostMapping("/find")
    public ModelAndView search(
            @RequestParam("find") String param,
            ModelAndView mav){
        mav.setViewName("find");
        if (param == "") {
            mav = new ModelAndView("redirect:/find");
        } else {
            mav.addObject("title", "Find result");
            mav.addObject("msg", param + "의 검색 결과");
            mav.addObject("value", param);
            List<MyMongoData> list = myMongoDataRepository.findByNameLike(param);
            mav.addObject("datalist", list);
        }
        return mav;
    }

    @GetMapping("/view")
    @ResponseBody
    public List<MyMongoData> view(){
        return myMongoDataRepository.findAll();
    }
}
