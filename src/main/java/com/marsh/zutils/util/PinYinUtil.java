package com.marsh.zutils.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼音工具类
 */
public class PinYinUtil {

    private PinYinUtil() {

    }

    public static String getAlphas(String... chinese) {
        StringBuilder sb = new StringBuilder();

        for (String ch : chinese) {
            ch = ch.substring(0, 1);
            sb.append(getAlpha(ch));
        }
        return sb.toString();
    }


    /**
     * 获取汉语拼音首字母
     */
    public static String getAlpha(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (char c : nameChar) {
            if (c > 128) {
                try {
                    pinyinName.append(PinyinHelper.toHanyuPinyinStringArray(
                            c, defaultFormat)[0].charAt(0));
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pinyinName.append(c);
            }
        }
        return pinyinName.toString();
    }
}
