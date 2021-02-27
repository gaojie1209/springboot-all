package com.wellhope.springbootall.service;

import com.wellhope.springbootall.entity.TBook;
import org.springframework.stereotype.Service;

/**
 * @author GaoJ
 * @create 2021-02-26 13:55
 */

public interface BookService {

    TBook getById(Integer id);
}
