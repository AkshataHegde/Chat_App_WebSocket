package com.highpeak.chat.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public final class NullEmptyUtils {

    private NullEmptyUtils() {
    }

    /**
     * Is nullor empty boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmpty(String val) {
        return isNull(val) || val.trim().isEmpty();
    }

    /**
     * Is nullor empty boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmpty(List<?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Is nullor empty boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmpty(Collection<?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Is nullor empty boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmpty(Number val) {
        return isNull(val) || val.doubleValue() == 0.0D || val.intValue() == 0;
    }

    public static boolean isNullorEmptyWithoutCampaign(Number val) {
        return isNull(val) ;
    }

    /**
     * Is nullor empty boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmpty(Map<?, ?> val) {
        return isNull(val) || val.isEmpty();
    }

    /**
     * Is nullor empty string boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmptyString(String val) {
        return isNull(val) || val.isEmpty() || val.trim().equalsIgnoreCase("empty");
    }

    /**
     * Is nullor empty or null string boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNullorEmptyOrNullString(String val) {
        return isNull(val) || val.isEmpty() || val.trim().equalsIgnoreCase("empty")
                || val.trim().equalsIgnoreCase("null");
    }

    /**
     * Is null boolean.
     *
     * @param val the val
     * @return the boolean
     */
    public static boolean isNull(Object val) {
        return val == null;
    }
}

