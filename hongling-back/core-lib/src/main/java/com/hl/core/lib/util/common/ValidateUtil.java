package com.hl.core.lib.util.common;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正在表达式验证工具类（验证身份证、车牌号等）
 * 
 * @author iStar
 * 
 */
public class ValidateUtil {
	
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
		if (TextUtils.isEmpty(no)){
			return false;
		}
		try {
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



	public static boolean isEmail(String email) {
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return Pattern.matches(regex, email);
	}

}
