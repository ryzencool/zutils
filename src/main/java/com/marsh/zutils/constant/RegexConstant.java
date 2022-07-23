package com.marsh.zutils.constant;

/**
 * 正则表达式
 */
public interface RegexConstant {
    /**
     * 短信验证码
     */
    String CHECK_CODE = "\\d{6}";
    /**
     * 超级密码格式校验
     */
    String SUPER_PWD = "\\S{6,20}";
    /**
     * 手机号码格式
     */
    String MOBILE = "^1[34578]\\d{9}$";
    /**
     * 用户联系方式，手机或者固话
     */
    String USER_TEL = "^(\\d{3,4}-\\d{7,8})|(1[34578]\\d{9})$";
    /**
     * 固话
     */
    String PHONE = "(\\d{3,4}-?)?\\d{7,8}";
    /**
     * 开关
     */
    String SWITCH_ALL = "0|1";
    /**
     * QQ号码
     */
    String QQ = "\\d{5,12}";
    /**
     * 正整数
     */
    String POSITIVE_INTEGER = "^[1-9]\\d{0,5}$";
    /**
     * 自然数
     */
    String NATURAL_NUMBER = "^\\d{1,6}$";
    /**
     * 带小数点的数字
     */
    String NUMBER_WITH_POINT = "^\\d{1,6}[\\.]?\\d*$";
    /**
     * 邮箱
     */
    String EMAIL = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    /**
     * 邮箱前缀
     */
    String EMAIL_PRE = "[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*";
    /**
     * 一种密码规则
     */
    String PWD_RULE = "^(?![0-9]+$)(?![a-z]+$)(?![0-9a-z]+$)(?![\\~\\)\\!@#\\$%^&\\*\\(\\)_\\+\\-=\\{\\}\\[\\]|:;<>\\?,\\./]+$)[0-9A-Za-z\\~\\)\\!@#\\$%^&\\*\\(\\)_\\+\\-=\\{\\}\\[\\]|:;<>\\?,\\./]{8,16}$";
    /**
     * 日期查询，年-月，或者年-月-日
     */
    String DATE_QUERY_SHORT = "\\d{4}-\\d{2}-\\d{2}||\\d{4}-\\d{2}";
    /**
     * 日期查询，年-月
     */
    String DATE_QUERY_MONTH = "\\d{4}-\\d{2}";
    /**
     * 日期格式 ：yyyy-MM-dd HH:mm:ss
     */
    String DATE_FORMAT = "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01])\\s(0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})$";
    /**
     * 时间格式校验
     * 1、2010-12-31 10:00:00
     * 2、2010-7-12 9:1:02
     * 3、2010-01-12 09:01:02
     */
    String DATE_QUERY_LONG = "\\d{4}-\\d{1,2}-\\d{1,2} \\d{1,2}:\\d{1,2}:\\d{1,2}";
    /**
     * 时间格式校验
     * 2016-02-25 08:09:45
     */
    String DATE_LONG_REGEX = "\\d{4}-\\d{2}-\\d{2} (0\\d{1}|1\\d{1}|2[0-3]):[0-5]\\d{1}:([0-5]\\d{1})";
    /**
     * 时间格式校验，严格位数
     * 00:00
     */
    String DATE_FORMAT_STRICT = "\\d{2}:\\d{2}";
    /**
     * 银行名称
     */
    String BANK_NAME = "[\\s\\S]{1,20}";
    /**
     * 支付宝帐号
     */
    String ALIPAY_ACCOUNT = "(^1[34578]\\d{9}$)|(^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$)";
    /**
     * 1-20长度的非空字符串
     */
    String COMMON_TWENTY_LENGTH = "\\S{1,20}";
    /**
     * 1-20长度的非空字符串或者空
     */
    String COMMON_TWENTY_LENGTH_OR_EMPTY = "^$|\\S{1,20}";
    /**
     * 1-20长度的字符串(<b color="red">可以含空格</b>)
     */
    String COMMON_TWENTY_LENGTH_WITH_EMPTY = "[\\s\\S]{1,20}";
    /**
     * 1-100长度的字符串(<b color="red">可以含空格</b>)
     */
    String COMMON_HUNDRED_LENGTH_WITH_EMPTY = "[\\s\\S]{1,100}";
    /**
     * 0-20长度的字符串(<b color="red">可以含空格</b>)
     */
    String COMMON_TWENTY_LENGTH_WITH_EMPTY_OR_EMPTY = "[\\s\\S]{0,20}";
    /**
     * 32长度的非空字符串
     */
    String AUTH_CODE_TOKEN = "\\S{32}";
    /**
     * 签名字符串
     */
    String SIGN = "\\S{40}";
    /**
     * URI
     */
    String URI = "(http|https)://([0-9a-z-_]+\\.)+(com|cn|net|org|biz|info|cc|tv|gov|co)(:[0-9]{1,4})?(/|/.*|)";
    /**
     * URI or empty
     */
    String URI_OR_EMPTY = "^$|(http|https)://([0-9a-z-_]+\\.)+(com|cn|net|org|biz|info|cc|tv|gov|co)(:[0-9]{1,4})?(/|/.*|)";
    /**
     * 1-999999
     */
    String NUMBER_GREATER_THAN_ZERO_OR_EMPTY = "^$|^[1-9]\\d{0,5}$";
    /**
     * 身份证号码
     */
    String ID_NO = "^(\\d{15}|\\d{17}[A-Za-z0-9])$";
    /**
     * 固定电话:025-88888888
     */
    String TELL = "^(0[0-9]{2,3}\\-?)?([2-9][0-9]{6,7})+(\\-[0-9]{1,4})?$";
    /**
     * 中文及英文
     */
    String WORD = "^[A-Za-z\\u4e00-\\u9fa5]+$";

    /**
     * 中文及英文1到5个字
     */
    String WORD_FIVE = "^[A-Za-z\\u4e00-\\u9fa5]{1,5}$";

    /**
     * 至多五位大写英文
     */
    String EN_CODE_5 = "^[A-Z]{0,5}$";

    /**
     * 字母
     */
    String STRING = "[A-Za-z]";

    /**
     * 数字
     */
    String NUM = "[0-9]*";
    /**
     * 一位英文
     */
    String EN_CODE_1 = "^[A-Za-z]{1}$";

    /**
     * 50位英文
     */
    String EN_CODE_50 = "^[A-Za-z]{0,50}$";

    /**
     * 字母与数字组合10位内
     */
    String WORD_NUM_5 = "^[a-zA-Z0-9]{0,5}$";

    /**
     * 字母与数字组合10位内
     */
    String WORD_NUM_10 = "^[a-zA-Z0-9]{0,10}$";

    /**
     * 字母与数字组合16位内
     */
    String WORD_NUM_16 = "^[a-zA-Z0-9]{2,16}$";

    /**
     * 字母与数字组合32位内
     */
    String WORD_NUM_32 = "^[a-zA-Z0-9]{2,32}$";
    /**
     * 中文
     */
    String WORD_CNN = "^[\\u4e00-\\u9fa5]{0,10}$";
    /**
     * 中文50
     */
    String WORD_CNN_50 = "^[\\u4e00-\\u9fa5]{0,50}$";
    /**
     * 特殊字符
     */
    String SPECIAL_STRING = "[`~!@#$%^&*()\\-_+=\\\\|{}':;'\",\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
    /**
     * 中文、英文和汉字，1到20个字
     */
    String SPEC_NAME = "^[\\s\\-_&#A-Za-z0-9\\u4e00-\\u9fa5]{1,20}$";
    /**
     * 多个中文、英文和汉字，1到20个字，用,分隔
     */
    String SPEC_NAMES = "([A-Za-z0-9\\u4e00-\\u9fa5]{1,20})+(,[A-Za-z0-9\\u4e00-\\u9fa5]{1,20}){0,49}";
    /**
     * 车牌号码
     */
    String CAR_NUMBER = "^[\\u4e00-\\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$";

    /**
     * 中英文数字及常用标点(包括换行空格)
     */
    String NORMAL_TEXT = "^[\\u4e00-\\u9fa50-9A-Za-z，。“”‘’'\"(),\\.!！《》\\W\\r\\n-]{1,200}";
    /**
     * 中英文数字及常用标点(包括换行空格)
     */
    String NORMAL_TEXT_1 = "^[\\u4e00-\\u9fa50-9A-Za-z，。“”‘’'\"(),#\\.!！《》\\W\\r\\n-]{1,500}";
    /**
     * 通用id正则表达式
     */
    String COMMON_ID = "\\w{1,32}";
    /**
     * 通用正则表达式
     */
    String COMMON_100 = "\\w{1,100}";
    /**
     * 通用小数格式，整数部分最多6位，支持3位小数
     */
    String COMMON_NUM_3_DECIMA = "\\d{1,6}(\\.\\d{1,3})?";
    /**
     * Id,个数不能超过50个
     */
    String COMMON_IDS_50 = "\\w{1,32}(,\\w{1,32}){0,49}";
    /**
     * 字符列表，字符以','分割,字符长度不超过32位,个数不超过10个
     */
    String COMMON_IDS_10 = "\\w{1,32}(,\\w{1,32}){0,9}";
    /**
     * 字符列表，字符以','分割,字符长度不超过32位,个数不超过100个
     */
    String COMMON_IDS_100 = "\\w{1,32}(,\\w{1,32}){0,99}";
    /**
     * 字符列表，字符以','分割,字符长度不超过32位,个数不超过200个
     */
    String COMMON_IDS_200 = "\\w{1,32}(,\\w{1,32}){0,199}";
    /**
     * 字符列表，字符以','分割,字符长度不超过32位,个数不超过5个
     */
    String TICKET_ORDER_NUMBERS_5 = "\\w{1,32}(,\\w{1,32}){0,4}";
    /**
     * 字符列表，字符以','分割,字符长度不超过32位,个数不限
     */
    String STR_LIST = "\\w{1,32}(,\\w{1,32}){0,99}";
    /**
     * 排序方式
     */
    String ORDER_WAY = "asc|desc";
    /**
     * key:value
     */
    String KEY_VALUE = "\\w{1,32}:\\w{1,32}";
    /**
     * 非空白字符32位
     */
    String COMMON_STRING = "\\S{0,32}";
    /**
     * status 1,2,3,4,5,6
     */
    String COMMON_SIX_STATUS = "1|2|3|4|5|6";
    /**
     * status -1,0,1,2 四种状态
     */
    String COMMON_FOUR_STATUS = "-1|0|1|2";
    /**
     * status 1,2 两种状态
     */
    String COMMON_TWO_STATUS = "1|2";
    /**
     * status 1,4 两种状态
     */
    String MEMBER_TYPE = "1|4";
    /**
     * 邮编,六位数字
     */
    String ZIP = "\\d{6}";
    /**
     * 20位以内数字
     */
    String NUMBER_20 = "\\d{0,20}";
    /**
     * 10位以内数字
     */
    String NUMBER_10 = "\\d{0,10}";
    /**
     * ip地址
     */
    String IP = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
    /**
     * 流量大小,以G或M结尾的数字
     */
    String FLOW_AMOUNT = "\\d{1,5}G|\\d{1,10}M";
}

