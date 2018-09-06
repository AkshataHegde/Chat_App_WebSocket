package com.highpeak.chat.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class DateUtil {

    /**
     * The constant DB_DATE_FORMAT.
     */
    public static final String DB_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * The constant SIMPLE_DATE_FORMAT.
     */
    public static final String SIMPLE_DATE_FORMAT = "yyyy-MM-dd";
    /**
     * The constant MM_DD_YYYY.
     */
    public static final String MM_DD_YYYY = "MM-dd-yyyy";
    /**
     * The constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";
    /**
     * The constant DATE_FORMAT_RECURRENCE_PATTERN.
     */
    public static final String DATE_FORMAT_RECURRENCE_PATTERN = "yyyyMMdd'T'HHmmss'Z'";
    /**
     * The constant MLLISECONDS_IN_DAY.
     */
    public static final Long MLLISECONDS_IN_DAY = 86400000l;

    static SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");

    /**
     * Private constructor
     */
    private DateUtil()
    {
        super();
    }

    /**
     * Get {@link DateTime} object for given calendar
     *
     * @param calendar the calendar
     * @return date time
     */
    public static DateTime getDateTime( final Calendar calendar )
    {
        return new DateTime(calendar);
    }

    /**
     * Compare Calendar with current date irrespective of time
     *
     * @param dateToCompare the date to compare
     * @return returns true if the current date is after passed date
     */
    public static Boolean currentDateComparator( final Long dateToCompare )
    {
        final DateTime currentDate = DateTime.now().withTimeAtStartOfDay().withZone(DateTimeZone.UTC);
        final DateTime dateTime = new DateTime(dateToCompare).withTimeAtStartOfDay().withZone(DateTimeZone.UTC);
        return dateTime.isAfter(currentDate);
    }

    /**
     * Return current UTC time in milliseconds
     *
     * @return long long
     */
    public static Long currentTimeMillis()
    {
        return new DateTime().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Return current UTC time in milliseconds
     *
     * @return long long
     */
    public static Long currentTimeMillisWithTimeAtStartOfDay()
    {
        return new DateTime().withTimeAtStartOfDay().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * compare passed in dates
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return returns true if startdate is after enddate
     */
    public static Boolean dateComparator( final Calendar startDate, final Calendar endDate )
    {
        return startDate.after(endDate);
    }

    /**
     * Return future UTC time in milliseconds for given number of days
     *
     * @param numberOfDays the number of days
     * @return long long
     */
    public static Long futureTimeMillisWithTimeAtStartOfDay( final Integer numberOfDays )
    {
        return new DateTime().withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Return past UTC time in milliseconds for given number of days
     *
     * @param numberOfDays the number of days
     * @return the long
     */
    public static Long pastTimeMillisWithTimeAtStartOfDay(final Integer numberOfDays)
    {
        return new DateTime().withTimeAtStartOfDay().minusDays(numberOfDays).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar with time at end of day
     */
    public static Calendar getCalendarWithTimeAtEndOfDay( final Long millisecondsTime, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTime(23, 59, 59, 999).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Get calendar with time at start of day
     *
     * @param calendar
     *            the calendar
     * @return calendar with time at start of day
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDay( final Calendar calendar )
    {
        return new DateTime(calendar).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    public static synchronized Calendar getCalendarWithTime( final Calendar calendar )
    {
        return new DateTime(calendar).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar with time at start of day
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDay( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Gives the Calendar object at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param numberOfDays
     *            the number of days
     * @return calendar with time at start of day based on number of days
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays( final Integer offsetHour,
            final Integer offsetMinute, final Integer numberOfDays )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(currentTimeMillis(), zone).withTimeAtStartOfDay().plusDays(numberOfDays)
                .withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Gives the Calendar object at start-time of the day according to offset and adds/subtracts the
     * number of days passed
     *
     * @param calendar
     *            the calendar
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param numberOfDays
     *            the number of days
     * @return calendar with time at start of day based on number of days
     */
    public static synchronized Calendar getCalendarWithTimeAtStartOfDayBasedOnNumberOfDays( final Calendar calendar,
            final Integer offsetHour, final Integer offsetMinute, final Integer numberOfDays )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(calendar, zone).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toGregorianCalendar();
    }

    /**
     * Return sql timestamp for sent date if not return timestamp for current day
     *
     * @param calendar
     *            the calendar
     * @return current sql time stamp
     */
    public static Timestamp getCurrentSQLTimeStamp( final Calendar calendar )
    {
        if( !NullEmptyUtils.isNull(calendar) )
        {
            return new Timestamp(calendar.getTimeInMillis());
        }
        return new Timestamp(DateUtil.getUTCCalenderInstance(null).getTimeInMillis());
    }

    /**
     * For given date in milliseconds, return string date in specified string format
     *
     * @param dateMilliseconds
     *            the date milliseconds
     * @param format
     *            the format
     * @return date string
     */
    public static synchronized String getDateString( final Long dateMilliseconds, final String format )
    {
        return new DateTime(dateMilliseconds).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Get calendar for today with DB_DAT_FORMAT
     *
     * @return the date string for today
     */
    public static synchronized String getDateStringForToday()
    {
        return DateTime.now().toString(DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT));
    }

    /**
     * For given date in milliseconds, return string date in UTC DB_DATE_FORMAT string format
     *
     * @param dateMilliseconds
     *            the date milliseconds
     * @return date string in utc
     */
    public static synchronized String getDateStringInUTC( final Long dateMilliseconds )
    {
        return new DateTime(dateMilliseconds).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gives the date string at start-time of the day according to offset and adds/subtracts the number
     * of days passed
     *
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param numberOfDays
     *            the number of days
     * @return date string with time at start of day based on number of days
     */
    public static synchronized String getDateStringWithTimeAtStartOfDayBasedOnNumberOfDays( final Integer offsetHour,
            final Integer offsetMinute, final Integer numberOfDays )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(currentTimeMillis(), zone).withTimeAtStartOfDay().plusDays(numberOfDays)
                .withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gives the date string at start-time of the day according to offset and adds/subtracts the number
     * of days passed
     *
     * @param date
     *            the date
     * @param numberOfDays
     *            the number of days
     * @return date string with time at start of day based on number of days
     */
    public static synchronized String getDateStringWithTimeAtStartOfDayBasedOnNumberOfDays( final Long date,
            final Integer numberOfDays )
    {
        return new DateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format, add specified number of days and return in UTC
     * with time at start of the day in DB_DATE_FORMAT string format
     *
     * @param date
     *            the date
     * @param numberOfDays
     *            the number of days
     * @return future date string with time at start of day
     */
    public static synchronized Calendar getFutureDateStringWithTimeAtStartOfDay( final Long date,
            final Integer numberOfDays )
    {
        return new DateTime(date).plusDays(numberOfDays).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    public static synchronized Calendar getFutureDateStringWithTimeAtStartOfYear( final Long date,
                                                                                 final Integer numberOfYears )
    {
        return new DateTime(date).plusYears(numberOfYears).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    public static synchronized Calendar getFutureDateByMinutesStringWithTimeAtStartOfDay( final Long date,
                                                                                 final Integer minutes )
    {
        return new DateTime(date).plusMinutes(minutes).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Parse given string date with specified format, add specified number of days and return in UTC
     * with time at start of the day in DB_DATE_FORMAT string format
     *
     * @param date
     *            the date
     * @param numberOfDays
     *            the number of days
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return future date string with time at start of day
     */
    public static synchronized String getFutureDateStringWithTimeAtStartOfDay( final Long date, final int numberOfDays,
            final int offsetHour, final int offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).withTimeAtStartOfDay().plusDays(numberOfDays).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * get local calendar instance using offset values
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar local calendar instance using offset
     */
    public static synchronized Calendar getLocalCalendarInstanceUsingOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).toGregorianCalendar();
    }

    /**
     * get local calendar instance using offset values
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param numberOfDays
     *            the number of days
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar future local calendar instance using offset
     */
    public static synchronized Calendar getFutureLocalCalendarInstanceUsingOffset( final Long millisecondsTime,
            final Integer numberOfDays, final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).plusDays(numberOfDays).toGregorianCalendar();
    }

    /**
     * Gets utc date string.
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param format
     *            the format
     * @return utc date string
     */
    public static synchronized String getUTCDateString( final Long date, final Integer offsetHour,
            final Integer offsetMinute, final String format )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Gets utc date long.
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return utc date long
     */
    public static synchronized Long getUTCDateLong( final Long date, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Gets local date string.
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param format
     *            the format
     * @return local date string
     */
    public static synchronized String getLocalDateString( final Long date, final Integer offsetHour,
            final Integer offsetMinute, final String format )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Gets millis with time at start of day.
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return the millis with time at start of day
     */
    public static synchronized long getMillisWithTimeAtStartOfDay( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).getMillis();
    }

    /**
     * Parse given string date with specified format and return in UTC with time at start of the day in
     * DB_DATE_FORMAT string format
     *
     * @param date
     *            the date
     * @return start date string with time at start of day
     */
    public static synchronized String getStartDateStringWithTimeAtStartOfDay( final Long date )
    {
        return new DateTime(date).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format and return in UTC with time at start of the day in
     * DB_DATE_FORMAT string format
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return start date string with time at start of day
     */
    public static synchronized String getStartDateStringWithTimeAtStartOfDay( final Long date, final int offsetHour,
            final int offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Parse given string date with specified format and return in UTC with time at start of the day in
     * DB_DATE_FORMAT string format
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param format
     *            the format
     * @return start date string with time at start of day
     */
    public static synchronized String getStartDateStringWithTimeAtStartOfDay( final Long date, final int offsetHour,
            final int offsetMinute, final String format )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Gets string at start time of day according to offset.
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return the string at start time of day according to offset
     */
    public static synchronized String getStringAtStartTimeOfDayAccordingToOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(zone)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string at start time of day according to offset.
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @param dateFormat
     *            the date format
     * @return the string at start time of day according to offset
     */
    public static synchronized String getStringAtStartTimeOfDayAccordingToOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute, final String dateFormat )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(zone)
                .toString(DateTimeFormat.forPattern(dateFormat));
    }

    /**
     * Get UTC calendar for given milliseconds date with offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return string calendar with time at start of day
     */
    public static synchronized String getStringCalendarWithTimeAtStartOfDay( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at end of day.
     *
     * @return the string date at end of day
     */
    public static synchronized String getStringDateAtEndOfDay()
    {
        return new DateTime(currentTimeMillis()).withTimeAtStartOfDay().plusDays(1).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at end of month.
     *
     * @return the string date at end of month
     */
    public static synchronized String getStringDateAtEndOfMonth()
    {
        return new DateTime(currentTimeMillis()).withDayOfMonth(1).plusMonths(1).withTimeAtStartOfDay()
                .withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at endof the week.
     *
     * @return the string date at endof the week
     */
    public static synchronized String getStringDateAtEndofTheWeek()
    {
        return new DateTime(currentTimeMillis()).weekOfWeekyear().roundCeilingCopy().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at start of day.
     *
     * @return the string date at start of day
     */
    public static synchronized String getStringDateAtStartOfDay()
    {
        return new DateTime(currentTimeMillis()).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at start of month.
     *
     * @return the string date at start of month
     */
    public static synchronized String getStringDateAtStartOfMonth()
    {
        return new DateTime(currentTimeMillis()).withDayOfMonth(1).withTimeAtStartOfDay().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gets string date at startof the week.
     *
     * @return the string date at startof the week
     */
    public static synchronized String getStringDateAtStartofTheWeek()
    {
        return new DateTime(currentTimeMillis()).weekOfWeekyear().roundFloorCopy().withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @return string utc calender instance
     */
    public static synchronized String getStringUTCCalenderInstance( final Long millisecondsTime )
    {
        return new DateTime(millisecondsTime).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar string utc calender instance using offset
     */
    public static synchronized String getStringUTCCalenderInstanceUsingOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC)
                .toString(DateTimeFormat.forPattern(DB_DATE_FORMAT));
    }

    /**
     * Gives the hour and minute time as a float for example if time is 02:45 then it is represented as
     * 2.45
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @return time in float
     */
    public static synchronized float getTimeInFloat( final Long millisecondsTime )
    {
        final Calendar cal = getUTCCalenderInstance(millisecondsTime);
        final float timeSlot = cal.get(Calendar.HOUR_OF_DAY);
        return timeSlot + cal.get(Calendar.MINUTE) / 100f;
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @return utc calender instance
     */
    public static synchronized Calendar getUTCCalenderInstance( final Long millisecondsTime )
    {
        return new DateTime(millisecondsTime).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    public static synchronized Calendar getCalenderInstance( final Long millisecondsTime )
    {
        return new DateTime(millisecondsTime).toGregorianCalendar();
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return calendar utc calender instance using offset
     */
    public static synchronized Calendar getUTCCalenderInstanceUsingOffset( final Long millisecondsTime,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Get current time utc calendar instance if external vaue is not passed using provided offset
     *
     * @param millisecondsTime
     *            the milliseconds time
     * @param timeZone
     *            the time zone
     * @return calendar utc calender instance using offset
     */
    public static synchronized Calendar getUTCCalenderInstanceUsingOffset( final Long millisecondsTime,
            final String timeZone )
    {
        final DateTimeZone zone = DateTimeZone.forID(timeZone);
        return new DateTime(millisecondsTime, zone).withZone(DateTimeZone.UTC).toGregorianCalendar();
    }

    /**
     * Gets utc date string with offset.
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return utc date string with offset
     */
    public static synchronized String getUTCDateStringWithOffset( final String date, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        final DateTime dt = formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay();
        return dt.withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DateUtil.DB_DATE_FORMAT));
    }

    /**
     * Gets future utc date string with offset.
     *
     * @param date
     *            the date
     * @param numberOfDays
     *            the number of days
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return future utc date string with offset
     */
    public static synchronized String getFutureUTCDateStringWithOffset( final String date, final Integer numberOfDays,
            final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        final DateTime dt = formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay().plusDays(numberOfDays);
        return dt.withZone(DateTimeZone.UTC).toString(DateTimeFormat.forPattern(DateUtil.DB_DATE_FORMAT));
    }

    /**
     * Gets zone date with offset.
     *
     * @param date
     *            the date
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return zone date with offset
     */
    public static long getZoneDateWithOffset( final String date, final Integer offsetHour, final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        final DateTimeFormatter formatter = DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT);
        return formatter.withZone(zone).parseDateTime(date).withTimeAtStartOfDay().getMillis();
    }

    /**
     * increment by specified number of days
     *
     * @param date
     *            the date
     * @param numberOfDays
     *            the number of days
     * @return incremented calendar instance
     */
    public static Calendar incrementDate( final Long date, final int numberOfDays )
    {
        return new DateTime(date).withTimeAtStartOfDay().withZone(DateTimeZone.UTC).plusDays(numberOfDays)
                .toGregorianCalendar();
    }

    /**
     * Check if the passed date is after current date
     *
     * @param startDateTime
     *            the start date time
     * @return boolean boolean
     */
    public static boolean isDateAfterCurrentDate( final Calendar startDateTime )
    {
        final DateTime startDate = new DateTime(startDateTime.getTimeInMillis());
        return startDate.toLocalDate().isAfter(DateTime.now().toLocalDate());
    }

    /**
     * Check if the passed date is before current date
     *
     * @param startDateTime
     *            the start date time
     * @return boolean boolean
     */
    public static boolean isDateBeforeCurrentDate( final Calendar startDateTime )
    {
        final DateTime startDate = new DateTime(startDateTime.getTimeInMillis());
        return startDate.toLocalDate().isBefore(DateTime.now().toLocalDate());
    }

    /**
     * check if both are on same day
     *
     * @param startDate
     *            the start date
     * @param endDate
     *            the end date
     * @return returns true if both passed in dates are on same day
     */
    public static Boolean isSameDay( final Calendar startDate, final Calendar endDate )
    {
        return startDate.get(Calendar.YEAR) == endDate.get(Calendar.YEAR)
                && startDate.get(Calendar.MONTH) == endDate.get(Calendar.MONTH)
                && startDate.get(Calendar.DAY_OF_MONTH) == endDate.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * Date format for all day task string.
     *
     * @param date
     *            the date
     * @param format
     *            the format
     * @param offsetHour
     *            the offset hour
     * @param offsetMinute
     *            the offset minute
     * @return string string
     */
    public static String dateFormatForAllDayTask( final Long date, final String format, final Integer offsetHour,
            final Integer offsetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offsetHour, offsetMinute);
        return new DateTime(date).withZone(zone).toString(DateTimeFormat.forPattern(format));
    }

    /**
     * Gets time zone.
     *
     * @param offSetHour
     *            the off set hour
     * @param offSetMinute
     *            the off set minute
     * @return time zone
     */
    public static String getTimeZone( final Integer offSetHour, final Integer offSetMinute )
    {
        final DateTimeZone zone = DateTimeZone.forOffsetHoursMinutes(offSetHour, offSetMinute);
        return zone.getID();
    }

    /**
     * Gets date object.
     *
     * @param date
     *            the date
     * @param format
     *            the format
     * @return date object
     * @throws ParseException
     *             the parse exception
     */
    public static Date getDateObject( final Calendar date, final String format ) throws ParseException
    {
        final SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(new DateTime(date).toString(DateTimeFormat.forPattern(SIMPLE_DATE_FORMAT)));
    }

    /**
     * Gets start and end date.
     *
     * @param taskTimeStamp
     *            the task time stamp
     * @param scheduleTimeStamp
     *            the schedule time stamp
     * @param timeZone
     *            the time zone
     * @return start and end date
     */
    public static Calendar getStartAndEndDate( final Long taskTimeStamp, final Long scheduleTimeStamp,
            final String timeZone )
    {
        final Calendar taskTime = Calendar.getInstance();
        final Calendar scheduleTime = Calendar.getInstance();
        final TimeZone tz = TimeZone.getTimeZone(timeZone);
        taskTime.setTimeInMillis(taskTimeStamp);
        scheduleTime.setTimeInMillis(scheduleTimeStamp);
        scheduleTime.setTimeZone(tz);
        final Calendar actualScheduleTime = Calendar.getInstance();
        actualScheduleTime.setTimeZone(tz);
        actualScheduleTime.set(taskTime.get(Calendar.YEAR), taskTime.get(Calendar.MONTH),
                taskTime.get(Calendar.DAY_OF_MONTH), scheduleTime.get(Calendar.HOUR_OF_DAY),
                scheduleTime.get(Calendar.MINUTE), scheduleTime.get(Calendar.SECOND));
        return actualScheduleTime;
    }

    /**
     * Date format for dob java . sql . date.
     *
     * @param dob
     *            the dob
     * @return java . sql . date
     * @throws ParseException
     *             the parse exception
     */
    public static java.sql.Date dateFormatForDOB( String dob ) throws ParseException
    {
        final DateFormat formatter = new SimpleDateFormat(SIMPLE_DATE_FORMAT);
        return new java.sql.Date(formatter.parse(dob).getTime());
    }

    /**
     * Compare dates boolean.
     *
     * @param startDate
     *            the start date
     * @param endDate
     *            the end date
     * @param timeZone
     *            the time zone
     * @return boolean boolean
     */
    public static Boolean compareDates( final Long startDate, final Long endDate, String timeZone )
    {
        final TimeZone tz = TimeZone.getTimeZone(timeZone);
        final DateTime start = new DateTime(startDate).withZone(DateTimeZone.forTimeZone(tz)).plusDays(7);
        final DateTime end = new DateTime(endDate).withZone(DateTimeZone.forTimeZone(tz));
        return (start.getMillis() == end.getMillis());
    }

    public static Date getLocalTimeFromUTC( Calendar date )
    {
        DateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        localFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        return new Date(localFormat.format(date));
    }

    public static Calendar dateString2Calendar( String s ) throws ParseException
    {

        Calendar cal = Calendar.getInstance();
        Date d1 = df.parse(s);
        cal.setTime(d1);
        return cal;
    }

    /**
     * Return date in specified format.
     * @param milliSeconds Date in milliseconds
     * @param dateFormat Date format
     * @return String representing date in specified format
     */
    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}