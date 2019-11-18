package com.itrexgroup.vydrasergei.bookcatalog.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * Parse date from String (db users table, field created_at)
     * @param dateStr - String value of date
     * @return - Date object
     * @throws ParseException - throws Date Exception
     */
    public static Date getDateFromString(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateStr);
    }
}
