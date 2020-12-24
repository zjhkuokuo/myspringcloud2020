package com.atguigu.springcloud.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName cloud2020
 * @author ZENG JIAN HUI
 * @createTime 2020/12/9 16:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;
    private String message;
    private T result;   //根据传入的对象动态的返回

    public CommonResult(Integer code,String message){
        this(code,message,null);
    }

}
