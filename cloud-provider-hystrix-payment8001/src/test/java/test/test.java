package test;

import java.time.ZonedDateTime;

/**
 * @author ZENG JIAN HUI
 * @projectName cloud2020
 * @createTime 2021/1/16 20:41
 */
public class test {

    public static void main(String[] args) {
        //获取当前时间
        ZonedDateTime zdt = ZonedDateTime.now();
        System.out.println(zdt);    //2021-01-16T20:42:47.794+08:00[Asia/Shanghai]
    }
}
