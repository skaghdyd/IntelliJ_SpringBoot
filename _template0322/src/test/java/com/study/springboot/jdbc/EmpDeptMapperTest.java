package com.study.springboot.jdbc;

import com.study.springboot.dto.Dept;
import com.study.springboot.dto.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmpDeptMapperTest {
    @Autowired
    EmpDeptMapper empDeptMapper;

    @Test
    public void test1(){
        String search = "";
        String type = "S";
        List<Emp> empList = empDeptMapper.findAllEmp(search, type);
        empList.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void test2(){
        List<Dept> deptList = empDeptMapper.findAllDept();
        deptList.stream().forEach(t->{
            System.out.println(t);
        });
    }

}