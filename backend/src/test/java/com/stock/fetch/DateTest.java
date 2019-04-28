 package com.stock.fetch;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

/**
 * @author Dave.zhao
 * @date 2018/09/04
 */
public class DateTest {

    @Test
    public void date() {
//        LocalDate date = LocalDate.now();
//        LocalDate date = LocalDate.parse("2018/09/04");
        
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        String text = date.format(formatter);
        LocalDate parsedDate = LocalDate.parse(text, formatter);

        
        System.out.println(text);
        System.out.println(date.getYear()+"-"+date.getMonthValue()+"-"+date.getDayOfMonth());
    }
}
