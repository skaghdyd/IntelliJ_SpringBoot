package com.study.springboot.repository

import com.study.springboot.entity.Dept
import org.springframework.data.jpa.repository.JpaRepository

interface DeptRepository : JpaRepository<Dept?, Long?>