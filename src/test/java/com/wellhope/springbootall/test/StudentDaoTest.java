package com.wellhope.springbootall.test;

import com.wellhope.springbootall.dao.StudentDao;
import com.wellhope.springbootall.entity.Student;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @author GaoJ
 * @create 2021-02-26 21:05
 */
public class StudentDaoTest {

    @Autowired
    private StudentDao studentDao;

    @Test
    public void save(){
        Student student =new Student(1L,"gj");

        studentDao.save(student);

    }

}