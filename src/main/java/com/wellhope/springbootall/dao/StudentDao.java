package com.wellhope.springbootall.dao;

import com.wellhope.springbootall.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author GaoJ
 * @create 2021-02-26 21:03
 */
public interface StudentDao extends JpaRepository<Student,Integer> {

}
