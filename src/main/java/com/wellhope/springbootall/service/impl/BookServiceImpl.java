package com.wellhope.springbootall.service.impl;

import com.wellhope.springbootall.entity.TBook;
import com.wellhope.springbootall.mapper.TBookMapper;
import com.wellhope.springbootall.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GaoJ
 * @create 2021-02-26 13:56
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private TBookMapper bookMapper;

    @Override
    public TBook getById(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }
}
