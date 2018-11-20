package com.hl.core.lib.util.common;

import android.text.method.ReplacementTransformationMethod;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Describe:
 * @Package: com.hl.core.lib.util.common
 * @Author: liyu
 * @Date: 2018/1/22 18:04
 * @Copyright: hl
 */

public class CheckDataUtil {

    public static Map<Character, Integer> kv = new HashMap<Character, Integer>();

    public static Map<Integer, Integer> wv = new HashMap<Integer, Integer>();

    static {

        for (int i = 0; i < 10; i++) {
            kv.put(String.valueOf(i).charAt(0), i);
        }
        //车辆代码中的数字和字母的对应表
        kv.put('a', 1);
        kv.put('b', 2);
        kv.put('c', 3);
        kv.put('d', 4);
        kv.put('e', 5);
        kv.put('f', 6);
        kv.put('g', 7);
        kv.put('h', 8);
        kv.put('j', 1);
        kv.put('k', 2);
        kv.put('l', 3);
        kv.put('m', 4);
        kv.put('n', 5);
        kv.put('p', 7);
        kv.put('q', 8);
        kv.put('r', 9);
        kv.put('s', 2);
        kv.put('t', 3);
        kv.put('u', 4);
        kv.put('v', 5);
        kv.put('w', 6);
        kv.put('x', 7);
        kv.put('y', 8);
        kv.put('z', 9);
        //每一位的加权系数
        wv.put(1, 8);
        wv.put(2, 7);
        wv.put(3, 6);
        wv.put(4, 5);
        wv.put(5, 4);
        wv.put(6, 3);
        wv.put(7, 2);
        wv.put(8, 10);
        wv.put(10, 9);
        wv.put(11, 8);
        wv.put(12, 7);
        wv.put(13, 6);
        wv.put(14, 5);
        wv.put(15, 4);
        wv.put(16, 3);
        wv.put(17, 2);

    }

    public final static String isVin(String vin) {
        String flag = "";
        if (vin.length() != 17) {
            flag = "vin码长度不正确！";
            return flag;
        } else {
            Pattern pattern = Pattern.compile("^.*([0-9a-zA-Z])\\1{5}.*$");
            Matcher matcher = pattern.matcher(vin);
            if (matcher.matches()) {
                flag = "vin码中不能有6位连续字符！";
                return flag;
            }
            if (vin.toUpperCase().contains("I")) {
                flag = "vin码含有非法字符  I(i)";
                return flag;
            }
            if (vin.toUpperCase().contains("O")) {
                flag = "vin码含有非法字符  O(o)";
                return flag;
            }
            if (vin.toUpperCase().contains("Q")) {
                flag = "vin码含有非法字符  Q(q)";
                return flag;
            }

        }
        return flag;
    }

    /**
     * 身份证验证的工具（支持5位或18位省份证）
     * 身份证号码结构：
     * 17位数字和1位校验码：6位地址码数字，8位生日数字，3位出生时间顺序号，1位校验码。
     * 地址码（前6位）：表示对象常住户口所在县（市、镇、区）的行政区划代码，按GB/T2260的规定执行。
     * 出生日期码，（第七位 至十四位）：表示编码对象出生年、月、日，按GB按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
     * 顺序码（第十五位至十七位）：表示在同一地址码所标示的区域范围内，对同年、同月、同日出生的人编订的顺序号，
     * 顺序码的奇数分配给男性，偶数分配给女性。
     * 校验码（第十八位数）：
     * 十七位数字本体码加权求和公式 s = sum(Ai*Wi), i = 0,,16，先对前17位数字的权求和；
     *  Ai:表示第i位置上的身份证号码数字值.Wi:表示第i位置上的加权因.Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2；
     * 计算模 Y = mod(S, 11)
     * 通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     */
    final static Map<Integer, String> zoneNum = new HashMap<Integer, String>();
    static {
        zoneNum.put(11, "北京");
        zoneNum.put(12, "天津");
        zoneNum.put(13, "河北");
        zoneNum.put(14, "山西");
        zoneNum.put(15, "内蒙古");
        zoneNum.put(21, "辽宁");
        zoneNum.put(22, "吉林");
        zoneNum.put(23, "黑龙江");
        zoneNum.put(31, "上海");
        zoneNum.put(32, "江苏");
        zoneNum.put(33, "浙江");
        zoneNum.put(34, "安徽");
        zoneNum.put(35, "福建");
        zoneNum.put(36, "江西");
        zoneNum.put(37, "山东");
        zoneNum.put(41, "河南");
        zoneNum.put(42, "湖北");
        zoneNum.put(43, "湖南");
        zoneNum.put(44, "广东");
        zoneNum.put(45, "广西");
        zoneNum.put(46, "海南");
        zoneNum.put(50, "重庆");
        zoneNum.put(51, "四川");
        zoneNum.put(52, "贵州");
        zoneNum.put(53, "云南");
        zoneNum.put(54, "西藏");
        zoneNum.put(61, "陕西");
        zoneNum.put(62, "甘肃");
        zoneNum.put(63, "青海");
        zoneNum.put(64, "新疆");
        zoneNum.put(71, "台湾");
        zoneNum.put(81, "香港");
        zoneNum.put(82, "澳门");
        zoneNum.put(91, "外国");
    }

    final static int[] PARITYBIT = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    final static int[] POWER_LIST = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
            5, 8, 4, 2};

    /**
     * 身份证验证
     *@param  certNo 号码内容
     *@return 是否有效 null和"" 都是false
     */
    public static boolean isIDCard(String certNo){
//    	if(certNo!=null&&!"".equals(certNo)&&certNo.length()<15){
//    		return true;
//    	}else{
        if(certNo == null || (certNo.length() != 15 && certNo.length() != 18))
            return false;
        if(certNo.length() == 15 && isNumeric(certNo)){ //15 位身份证号直接通过
            return true;
        }
        final char[] cs = certNo.toUpperCase().toCharArray();
        //校验位数
        int power = 0;
        for(int i=0; i<cs.length; i++){
            if(i==cs.length-1 && cs[i] == 'X')
                break;//最后一位可以 是X或x
            if(cs[i]<'0' || cs[i]>'9')
                return false;
            if(i < cs.length -1){
                power += (cs[i] - '0') * POWER_LIST[i];
            }
        }

        //校验区位码
        if(!zoneNum.containsKey(Integer.valueOf(certNo.substring(0,2)))){
            return false;
        }

        //校验年份
        String year = certNo.length() == 15 ? getIdcardCalendar() + certNo.substring(6,8) :certNo.substring(6, 10);

        final int iyear = Integer.parseInt(year);
        if(iyear < 1900 || iyear > Calendar.getInstance().get(Calendar.YEAR))
            return false;//1900年的PASS，超过今年的PASS

        //校验月份
        String month = certNo.length() == 15 ? certNo.substring(8, 10) : certNo.substring(10,12);
        final int imonth = Integer.parseInt(month);
        if(imonth <1 || imonth >12){
            return false;
        }

        //校验天数
        String day = certNo.length() ==15 ? certNo.substring(10, 12) : certNo.substring(12, 14);
        final int iday = Integer.parseInt(day);
        if(iday < 1 || iday > 31)
            return false;

        //校验"校验码"
        if(certNo.length() == 15)
            return true;
        return cs[cs.length -1 ] == PARITYBIT[power % 11];
    }


//    }

    private static int getIdcardCalendar() {
        GregorianCalendar curDay = new GregorianCalendar();
        int curYear = curDay.get(Calendar.YEAR);
        int year2bit = Integer.parseInt(String.valueOf(curYear).substring(2));
        return  year2bit;
    }



    public static void main(String[] args) {
        boolean mark = isIDCard("22018119860414121x");
        System.out.println(mark);
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("\\d+\\.?\\d+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    public static ReplacementTransformationMethod inputLowerToUpper=new ReplacementTransformationMethod(){

        @Override
        protected char[] getOriginal() {
            char[] lower = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z' };
            return lower;
        }

        @Override
        protected char[] getReplacement() {
            char[] upper = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
            return upper;
        }

    };

    /**
     * 验证手机号码
     * 中国移动号段：134、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188；
     * 中国联通号段：130、131、132、145、155、156、171、175、176、185、186；
     * 中国电信号段：133、149、153、173、177、180、181、189
     * @param mobiles
     * @return [0-9]{5,9}
     */
    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern
                    .compile("^((17[135678])|(13[0-9])|(14[579])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    // 正则验证 车牌
    /**
     * 1)	常规车牌号：仅允许以汉字开头，后面可录入六个字符，由大写英文字母和阿拉伯数字组成。如：粤B12345；
     2)	 武警车牌：允许前两位为大写英文字母，后面可录入5～7个字符，由大写英文字母和阿拉伯数字组成，其中第一位或者第三位可录汉字也可录大写英文字母及阿拉伯数字，如：WJ京12345
     3)	 最后一个为汉字的车牌：允许以汉字开头，后面可录入六个字符，前五位字符，由大写英文字母和阿拉伯数字组成，而最后一个字符为汉字，汉字包括“挂”、“学”、“警”、“军”、“港”、“澳”。如：粤Z1234港。
     4)	新军车牌：以两位为大写英文字母开头，后面以5位阿拉伯数字组成。如：BA12345。
     5)	 黑龙江车牌存在08或38开头的情况
     6)	农机车牌：汉字开头，中间两位为数字，再加5位车牌字符
     *  第二：新能源车
     * 组成：省份简称（1位汉字）+发牌机关代号（1位字母）+序号（6位），总计8个字符，序号不能出现字母I和字母O
     * 通用规则：不区分大小写，第一位：省份简称（1位汉字），第二位：发牌机关代号（1位字母）
     * 序号位：
     * 小型车，第一位：只能用字母D或字母F，第二位：字母或者数字，后四位：必须使用数字
     * ---([DF][A-HJ-NP-Z0-9][0-9]{4})
     * 大型车，前五位：必须使用数字，第六位：只能用字母D或字母F。
     * ----([0-9]{5}[DF])
     * 新能源车规则："[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF][A-HJ-NP-Z0-9][0-9]{4}))"
     * @param no
     * @return
     */
    public static boolean isPlateNo(String no) {
        boolean flag = false;
        try {
//			Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[0-9]{2}[A-Z0-9]{5}$");
            Pattern p = Pattern.compile("^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[A-Z0-9]{6}$|^[A-Z]{2}[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}[A-Z0-9]{5}$|^[A-Z]{2}[0-9]{2}[A-Z0-9]{5}$|^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{1}[A-Z0-9]{5}[挂学警军港澳]{1}$|^[A-Z]{2}[0-9]{5}$|(^(08|38){1}[A-Z0-9]{4}[A-Z0-9挂学警军港澳]{1}$)|^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领]{1}[0-9]{2}[A-Z0-9]{5}$|^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(([0-9]{5}[DF])|([DF]([A-HJ-NP-Z0-9])[0-9]{4}))");
            Matcher m = p.matcher(no.toUpperCase());
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }

        if (no.equals("临时车牌")) {
            return true;
        }

        if (no.equals("暂未上牌")) {
            return true;
        }

        return flag;
    }

    //固定电话校验
    public static boolean isLandlinePhone(String mobiles) {
        boolean flag = false;
        try {
            Pattern p = Pattern.compile("^0\\d{2,3}-\\d{7,8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    //是否包含中文
    public static boolean isContainChinese(String str) {

        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
