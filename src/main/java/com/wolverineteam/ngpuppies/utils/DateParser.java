package com.wolverineteam.ngpuppies.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateParser {

    public Date getDateFromString(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate = null;
        try {
            parsedDate = new Date(format.parse(date).getTime());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        return parsedDate;
    }
}
