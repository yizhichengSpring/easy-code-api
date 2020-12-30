package com.yi.easycode.commons.util;

/**
 * @author yizhicheng
 * @ClassName WordUtils
 * @Description 字符串处理，源码来自于 commons-lang
 * @Date 2020/12/20 2:12 下午
 **/
public class WordUtil {
    public static String capitalizeFully(String str, char[] delimiters) {
        int delimLen = delimiters == null ? -1 : delimiters.length;
        if (str != null && str.length() != 0 && delimLen != 0) {
            str = str.toLowerCase();
            return capitalize(str, delimiters);
        } else {
            return str;
        }
    }

    public static String capitalize(String str, char[] delimiters) {
        int delimLen = delimiters == null ? -1 : delimiters.length;
        if (str != null && str.length() != 0 && delimLen != 0) {
            int strLen = str.length();
            StringBuilder builder = new StringBuilder(strLen);
            boolean capitalizeNext = true;
            for(int i = 0; i < strLen; ++i) {
                char ch = str.charAt(i);
                if (isDelimiter(ch, delimiters)) {
                    builder.append(ch);
                    capitalizeNext = true;
                } else if (capitalizeNext) {
                    builder.append(Character.toTitleCase(ch));
                    capitalizeNext = false;
                } else {
                    builder.append(ch);
                }
            }
            return builder.toString();
        } else {
            return str;
        }
    }

    private static boolean isDelimiter(char ch, char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        } else {
            int i = 0;
            for(int isize = delimiters.length; i < isize; ++i) {
                if (ch == delimiters[i]) {
                    return true;
                }
            }
            return false;
        }
    }
}
