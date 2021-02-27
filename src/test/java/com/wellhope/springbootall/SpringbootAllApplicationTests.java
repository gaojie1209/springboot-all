package com.wellhope.springbootall;

import com.wellhope.springbootall.dao.StudentDao;
import com.wellhope.springbootall.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootAllApplicationTests {
    @Autowired
    private StudentDao studentDao;
    @Test
    void contextLoads() {
        Student student =new Student(1L,"gj");

        studentDao.save(student);
    }

}
