package com.study.springboot.repository;

import com.study.springboot.dto.MyMongoData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MyMongoDataRepository extends MongoRepository<MyMongoData, Long> {
    public List<MyMongoData> findByName(String s);
    public List<MyMongoData> findByNameLike(String s);
}
