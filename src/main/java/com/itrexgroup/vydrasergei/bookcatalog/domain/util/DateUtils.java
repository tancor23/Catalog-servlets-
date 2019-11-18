package com.itrexgroup.vydrasergei.bookcatalog.domain.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static Date getDateFromString(String dateStr) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateStr);
    }
}
