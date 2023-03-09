package com.study.springboot.repository;

import com.study.springboot.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void testIns(){
        Notice notice = Notice.builder()
                .seq(noticeRepository.selectMaxSeq()+1L)
                .title("제목3")
                .content("내용3")
                .build();
        noticeRepository.insertNotice(notice);

        List<Notice> list = noticeRepository.findAll();
        list.stream().forEach(t->{
            System.out.println(t);
        });
    }
}