package com.study.springboot.service;

import com.study.springboot.entity.Notice;
import com.study.springboot.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
    private final NoticeRepository noticeRepository;
    @Override
    public Notice saveReply(Notice notice) {
        notice.setSeq((notice.getSeq()-1));;
        return noticeRepository.save(notice);
    }
}
