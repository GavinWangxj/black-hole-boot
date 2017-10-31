package cn.cincout.tech.springdatajpa.application.utils;


import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaoyu on 16-9-14.
 *
 * @author zhaoyu
 * @date 17-4-21
 * @sine 1.8
 */
public abstract class DateTimeUtils {
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss SSS";
    private DateTimeUtils() {}

    public static String longToDateStr(long date, String fmt) {
        String pattern = (fmt == null) ? FORMAT : fmt;
        final SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(new Date(date));
    }

    public static long dateStrToLong(String dataStr) {
        if (StringUtils.isBlank(dataStr)) {
            throw new IllegalArgumentException("data str can not be blank.");
        }

        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dataStr);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }
}
