package ua.com.alexcoffee.model;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

@MappedSuperclass
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;

    @Transient
    private static String codePattern = "ALEXCOFFEE1234567890";

    @Transient
    private static int codeLength = 6;

    @Transient
    private static String timezoneID = "GMT+3";

    @Transient
    private static String datePattern = "EEE, d MMM yyyy, HH:mm:ss";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Model() {

    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Model other = (Model) obj;
        return (this.toEquals().equals(other.toEquals()));
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    public String toEquals() {
        return toString();
    }

    public static String createRandomString() {
        return createRandomString(codePattern, codeLength);
    }

    public static String createRandomString(String pattern, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = new Random().nextInt(pattern.length());
            char ch = pattern.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    public static String dateToStringWithFormat(Date date) {
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        TimeZone timeZone = TimeZone.getTimeZone(timezoneID);
        return dateToStringWithFormat(date, dateFormat, timeZone);
    }

    public static String dateToStringWithFormat(Date date, DateFormat dateFormat, TimeZone timeZone) {
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static String getCodePattern() {
        return codePattern;
    }

    public static void setCodePattern(String codePattern) {
        Model.codePattern = codePattern;
    }

    public static int getCodeLength() {
        return codeLength;
    }

    public static void setCodeLength(int codeLength) {
        Model.codeLength = codeLength;
    }

    public static String getTimezoneID() {
        return timezoneID;
    }

    public static void setTimezoneID(String timezoneID) {
        Model.timezoneID = timezoneID;
    }

    public static String getDatePattern() {
        return datePattern;
    }

    public static void setDatePattern(String datePattern) {
        Model.datePattern = datePattern;
    }
}
