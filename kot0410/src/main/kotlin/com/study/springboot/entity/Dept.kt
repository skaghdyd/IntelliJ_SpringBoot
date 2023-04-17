package com.study.springboot.entity

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import javax.persistence.Entity
import javax.persistence.Id

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
class Dept {
    @Id
    private val deptno: Long? = null
    private val dname: String? = null
    private val loc: String? = null
}