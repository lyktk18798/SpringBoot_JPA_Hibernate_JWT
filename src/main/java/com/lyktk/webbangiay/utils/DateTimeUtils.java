package com.lyktk.webbangiay.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtils {
    public static Date convertStingToDate(String date){
        try{
            return new SimpleDateFormat("dd-MM-yyyy").parse(date);
        }catch(Exception e){
            System.out.print(e);
        }

        return null;
    }
}
