package com.wellhope.springbootall.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 异常处理类封装统一返回结果信息
 * @author GaoJ
 * @create 2021-02-27 11:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultBean<T> {

    private String status;
    private T data;

}
