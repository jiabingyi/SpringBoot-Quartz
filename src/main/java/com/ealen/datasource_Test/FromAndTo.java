package com.ealen.datasource_Test;

import com.ealen.datasource_Test.druid_from.From;
import com.ealen.datasource_Test.druid_to.To;
import com.ealen.entity.Table1;

import java.util.List;

public class FromAndTo {

    public static void main(String[] args) {
        fromAndTo(2);

    }

    public static void fromAndTo(int rows){
        int first=0;
        while (true){
            List<Table1> list= From.queryByRowNum(first,rows);
            first=first+rows;

            To.insertRow(list);
            if(list.size()<rows) break;
        }
    }








}
