package com.study.springboot.repository;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.study.springboot.entity.Person;
import com.study.springboot.entity.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
class PersonRepositoryTest {
    @Autowired
    PersonRepository personRepository;
    @Test
    public void testIns(){
        IntStream.rangeClosed(1,10).forEach(t->{
            Person person = Person.builder()
                    .name("사람"+t)
                    .addr("주소"+t)
                    .build();
            personRepository.save(person);
        });
    }

    @Test
    public void testSel(){
        List<Person> person = personRepository.findAll();
        person.stream().forEach(i->{
            System.out.println(i);
        });
    }

    @Test
    public void testUpd(){
        Person person = personRepository.findById(1L).orElseThrow();
        System.out.println(person);

        person = Person.builder()
                .pid(1L)
                .name("수정테스트")
                .addr("수정테스트")
                .build();
        personRepository.save(person);

        person = personRepository.findById(1L).orElseThrow();
        System.out.println(person);
    }

    @Test
    public void testDel(){
        Person person = personRepository.findById(1L).orElseThrow();
        System.out.println(person);

        personRepository.deleteById(1L);

        person = personRepository.findById(1L).orElseThrow();
        System.out.println(person);
    }

    @Test
    public void testSel2(){
        Person person = personRepository.findPersonByName("사람1");
        System.out.println(person);
    }

    @Test
    public void testSel3(){
        List<Person> person = personRepository.findAllByNameLike("%사%");
        person.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void testSelectAll(){
        String name = "사람1";
        List<Person> person = personRepository.selectAll(name);
        person.stream().forEach(t->{
            System.out.println(t);
        });
    }

    @Test
    public void testInsertPerson(){
        String name = "인서트테스트name";
        String addr = "인서트테스트addr";
        personRepository.insertPerson(name, addr);
        List<Person> person = personRepository.findAll();
        person.stream().forEach(t->{
            System.out.println(t);
        });

    }

    @Test
    public void testInsertPerson2(){
        Person person = Person.builder()
                .name("인서트테스트name2")
                .addr("인서트테스트addr2")
                .build();
        personRepository.insertPerson2(person);

        List<Person> list = personRepository.findAll();
        list.stream().forEach(t->{
            System.out.println(t);
        });

    }
}