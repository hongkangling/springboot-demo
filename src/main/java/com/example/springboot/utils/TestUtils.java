package com.example.springboot.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author linghongkang
 * @description:
 * @create: 2018-12-04 19:27
 **/
public class TestUtils {

    public static void main(String[] args) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse("2018-5-12");
        Date now = new Date();

        System.out.println((now.getTime()-date.getTime())/(24*60*60*1000)/7*3);
    }
}
