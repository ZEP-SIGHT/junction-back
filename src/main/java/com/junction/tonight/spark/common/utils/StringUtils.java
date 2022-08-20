package com.junction.tonight.spark.common.utils;

import com.junction.tonight.spark.common.ObjectConverter;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


public class StringUtils extends org.apache.commons.lang3.StringUtils {
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String QUESTIONMARK = "?";
    public static final String EQUAL = "=";
    public static final String AMPERSAND = "&";
    public static final String DOUBLE_QUOTATION = "\"";
    public static final String NEW_LINE = System.getProperty("line.separator");
    public static boolean isNull(final String str) {
        return str == null;
    }
    public static boolean isNotNull(final String str) {
        return !isNull(str);
    }
    public static boolean anyNull(String... str) {
        return Arrays.stream(str).anyMatch(StringUtils::isNull);
    }
    public static boolean anyEmpty(String... str) {
        return Arrays.stream(str).anyMatch(StringUtils::isEmpty);
    }
    /**
     * @remark
     * 문자열을 delimiter 로 연결한다.<br>
     * @param delimiter 구분자
     * @param str 연결할 문자열
     * @return String
     */
    @Deprecated
    public static String concat(String delimiter, String... str) {
        return String.join(delimiter, str);
    }
    /**
     * @fn nullTo
     * @remark
     * - 문자열이 null 이면 '' 를 리턴
     * @param str
     * @return
     */
    /**
     * @remark
     * - 문자열이 null 이면 '' 를 리턴
     * @param str 문자열
     * @return String
     */
    public static String nullTo(String str) {
        return emptyTo(str, EMPTY);
    }
    /**
     * @fn nullTo
     * @remark
     * - 문자열이 null 이면 '' 를 리턴
     * @param str 문자열
     * @return String
     */
    public static String nullTo(String str, String defaultValue) {
        return isNull(str) ? defaultValue : str;
    }
    /**
     * @fn emptyTo
     * @remark
     * - 문자열이 null 이거나 empty 이면 '' 를 리턴
     * @param str 문자열
     * @return String
     */
    public static String emptyTo(String str) {
        return emptyTo(str, EMPTY);
    }
    /**
     * @fn emptyTo
     * @remark
     * - 문자열이 null 이거나 empty 이면 defaultValue 를 리턴
     * @param str 문자열
     * @param defaultValue 기본문자열
     * @return String
     */
    public static String emptyTo(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }
    /**
     * @fn includeValue
     * @remark
     * - 문자열에 주어진 문자열중 한개라도 존재하는지 판별
     * @param str 문자열
     * @param instr 찾을문자열
     * @return String
     */
    public static boolean includeValue(String str, String... instr) {
        return isNotEmpty(str) && Arrays.stream(instr).anyMatch(str::contains);
    }
    public static String prettyJson(String json) throws Exception {
        return ObjectConverter.toPrettyJson(ObjectConverter.toMap(json));
    }
    public static String quotation(String s) {
        return String.join(s, StringUtils.DOUBLE_QUOTATION, StringUtils.DOUBLE_QUOTATION);
    }
    public static String replace(String s, Map<String, String> map) {
        AtomicReference<String> rtn = new AtomicReference<>(s);
        map.forEach((key, value) -> rtn.set(rtn.get().replaceAll(key, value)));
        return rtn.get();
    }
}
