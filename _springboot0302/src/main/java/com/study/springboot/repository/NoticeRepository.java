package com.study.springboot.repository;

import com.study.springboot.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "insert into notice(seq, title, content) " +
            "values (:#{#notice.seq}, :#{#notice.title}, :#{#notice.content})",
            nativeQuery = true)
    public void insertNotice(@Param("notice") Notice notice);

    @Query(value = "select max(seq) from notice", nativeQuery = true)
    public Long selectMaxSeq();
}
