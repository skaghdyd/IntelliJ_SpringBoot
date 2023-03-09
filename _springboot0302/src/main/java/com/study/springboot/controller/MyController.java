package com.study.springboot.controller;

import com.study.springboot.entity.Notice;
import com.study.springboot.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MyController {

    private final NoticeRepository noticeRepository;

    @PostConstruct
    public void init(){
        Notice notice = Notice.builder()
                .seq(noticeRepository.selectMaxSeq()+1L)
                .title("제목"+(noticeRepository.selectMaxSeq()+1L))
                .content("내용"+(noticeRepository.selectMaxSeq()+1L))
                .build();
        noticeRepository.insertNotice(notice);

        List<Notice> list = noticeRepository.findAll();
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @GetMapping("/")
    public String root(){
        return "view/index";
    }

    @GetMapping("/page2")
    public String listAction(Model model, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<Notice> list = noticeRepository.findAll();
        final int start = (int) pageable.getOffset();
        final int end = Math.min((start + pageable.getPageSize()), list.size());
        final Page<Notice> page = new PageImpl<>(list.subList(start, end), pageable, list.size());

        model.addAttribute("list", page);
        return "view/list";
    }
}
