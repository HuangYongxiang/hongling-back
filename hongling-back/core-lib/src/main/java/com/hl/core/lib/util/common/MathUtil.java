package com.hl.core.lib.util.common;

import java.math.BigDecimal;

public class MathUtil {
	/**
	 * 获取value的双精度浮点数，如果失败，返回defaultValue
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Double getDouble(String value, double defaultValue){
		try{
			return Double.valueOf(value);
		}catch(Exception ex){

		}
		return defaultValue ;
	}
	/**
	 * 获取value的整数值，如果失败，返回defaultValue
	 * @param value
	 * @param defaultValue
	 * @return
	 */
	public static Integer getInt(String value ,int defaultValue){
		try{
			return Integer.valueOf(value);
		}catch(Exception ex){

		}
		return defaultValue ;
	}

	public static double setScale(double d){
		java.text.DecimalFormat   df   =new   java.text.DecimalFormat("#.00");
		java.text.DecimalFormat   df2   =new   java.text.DecimalFormat("#.000");//防止fr1的结果是0.005000000000000000000768 等情况
		String r;
		int intPart1 = new Double(d*1000).intValue();
		//防止0.005的问题
		if (intPart1%5 == 0) {
			r = df.format(d + 0.001);
		}else{
			r = df.format(d);
		}
		return Double.parseDouble(r);
	}
	//乘法
	public static double mul(double v1,double v2)
	{
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
}
