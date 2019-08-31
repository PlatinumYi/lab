package com.xuenan.lab.tool;

import java.util.Date;

public class BeijingTime {


    public static Date getBeijingTime( Date date ){


        long BeijingMills = date.getTime() + 1000*8*3600 ;
        return new Date(BeijingMills);

    }
}
