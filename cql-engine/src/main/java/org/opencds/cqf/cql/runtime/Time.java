package org.opencds.cqf.cql.runtime;

import org.joda.time.*;

import org.opencds.cqf.cql.elm.execution.EqualEvaluator;

import java.util.TimeZone;

/**
 * Created by Chris Schuler on 6/16/2016
 */
public class Time extends BaseTemporal {

    public Time(Partial partial) {
        this.timezone = DateTimeZone.forOffsetMillis(TimeZone.getDefault().getRawOffset());
        this.isDateTime = false;
        setPartial(partial);
    }

    public Time(Partial partial, DateTimeZone timezone) {
        this.timezone = timezone;
        this.isDateTime = false;
        setPartial(partial);
    }

    protected static final DateTimeFieldType[] fields = new DateTimeFieldType[] {
            DateTimeFieldType.hourOfDay(),
            DateTimeFieldType.minuteOfHour(),
            DateTimeFieldType.secondOfMinute(),
            DateTimeFieldType.millisOfSecond(),
    };

    public static DateTimeFieldType[] getFields(int numFields) {
        DateTimeFieldType[] ret = new DateTimeFieldType[numFields];
        System.arraycopy(fields, 0, ret, 0, numFields);
        return ret;
    }

    public static DateTimeFieldType getField(int idx) {
        return fields[idx];
    }

    public static int getFieldIndex(String dateTimeElement) {
        dateTimeElement = dateTimeElement.toLowerCase();

        if (dateTimeElement.startsWith("hour")) {
            return 0;
        }
        else if (dateTimeElement.startsWith("minute")) {
            return 1;
        }
        else if (dateTimeElement.startsWith("second")) {
            return 2;
        }
        else if (dateTimeElement.startsWith("millisecond")) {
            return 3;
        }

        return -1;
    }

    public static String getUnit(int idx) {
        switch (idx) {
            case 0: return "hours";
            case 1: return "minutes";
            case 2: return "seconds";
            case 3: return "milliseconds";
        }
        throw new IllegalArgumentException("Invalid index for Time unit request.");
    }

    public static Time getTimeOfDay() {
        org.joda.time.DateTime dt = org.joda.time.DateTime.now();
        int [] values = { dt.hourOfDay().get(), dt.minuteOfHour().get(), dt.secondOfMinute().get(), dt.millisOfSecond().get() };
        return new Time(new Partial(fields, values), dt.getZone());
    }

    public static Time expandPartialMin(Time dt, int size) {
        for (int i = dt.getPartial().size(); i < size; ++i) {
            dt.setPartial(dt.getPartial().with(getField(i), getField(i).getField(null).getMinimumValue()));
        }
        return dt;
    }

    public Boolean equal(Time other) {
        return this.similar(other, EqualEvaluator.SimilarityMode.EQUAL);
    }

    public Boolean similar(Time other, EqualEvaluator.SimilarityMode mode) {
        if (this.getPartial().size() != other.getPartial().size()) { // Uncertainty
            return null;
        }
        if (mode.equals(EqualEvaluator.SimilarityMode.EQUAL)) {
            return other.getJodaDateTime().toInstant().compareTo(this.getJodaDateTime().toInstant()) == 0;
        }
        else {
            // Do not want to call the equals method for DateTime or Time - returns null if missing elements...
            for (int i = 0; i < this.getPartial().size(); ++i) {
                if (this.getPartial().getValue(i) != other.getPartial().getValue(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
