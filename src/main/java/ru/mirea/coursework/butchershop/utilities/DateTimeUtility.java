package ru.mirea.coursework.butchershop.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeUtility {
    static public SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
    }

    public static String getFormattedDate(Date date) {
        SimpleDateFormat formatter = DateTimeUtility.getDateFormat();

        return formatter.format(date);
    }
}
