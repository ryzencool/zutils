package com.marsh.zutils.util;



import com.marsh.zutils.constant.RegexConstant;

import java.util.regex.Pattern;

/**
 * @author lucky.zhou
 */
public class ValidUtil {

    private ValidUtil() {

    }

    /**
     * 判断密码是否是字母和数字的组合
     *
     * @param str
     * @return
     */
    public static boolean isPwd(String str) {
        Pattern p1 = Pattern.compile(RegexConstant.NUM);
        Pattern p2 = Pattern.compile(RegexConstant.STRING);
        return (p1.matcher(str).find() && p2.matcher(str).find());
    }

    /**
     * 判断密码是否包含特殊字符
     *
     * @param str
     * @return
     */
    public static boolean isSpecialString(String str) {
        Pattern p = Pattern.compile(RegexConstant.SPECIAL_STRING);
        return p.matcher(str).find();
    }

    /**
     * 验证密码规则
     *
     * @param str
     * @return
     */
    public static boolean matchNewPwdRule(String str) {
        Pattern p = Pattern.compile(RegexConstant.PWD_RULE);
        return p.matcher(str).find();
    }

    /**
     * 只是验证日期格式，不验证其正确性。
     * 基本格式：yyyy-MM-dd HH:mm:ss
     */
    public static boolean isDateSimpleFormat(String dateTime) {
        Pattern pattern = Pattern.compile(RegexConstant.DATE_FORMAT);
        return pattern.matcher(dateTime).find();
    }

    /**
     * 是否是手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        Pattern pattern = Pattern.compile(RegexConstant.MOBILE);
        return pattern.matcher(mobile).find();
    }

    /**
     * 是否是邮箱地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile(RegexConstant.EMAIL);
        return pattern.matcher(email).find();
    }

    /**
     * 是否是正确的IP地址
     *
     * @param ip 地址
     * @return true 是；false 不是
     */
    public static boolean isIP(String ip) {
        Pattern pattern = Pattern.compile(RegexConstant.IP);
        return pattern.matcher(ip).find();
    }

    /**
     * 是否是数字
     *
     * @param text
     * @return true 是；false 不是
     */
    public static boolean isNumeric(String text) {
        Pattern pattern = Pattern.compile(RegexConstant.NUM);
        return pattern.matcher(text).matches();
    }
}
