package com.study.springboot

import com.study.springboot.entity.Dept
import com.study.springboot.repository.DeptRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SpringbootApplicationTests {

	@Autowired
	lateinit var deptRepository: DeptRepository


	@Test
	fun contextLoads() {
		println("테스트~")
	}

	fun aaa(ss: String) {
		println("aaa함수를 호출하였습니다.")
		println(ss)
	}

	@Test
	fun test1() {
		println("연습")
		aaa("변수 테스트")
	}

	@Test
	fun test2() {
//		val list: List<Dept?> = deptRepository.findAll() ?: emptyList()
		val list: List<Dept?> = deptRepository.findAll()
		println(list)
	}
}
