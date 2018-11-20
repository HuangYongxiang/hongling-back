package com.hl.core.lib.util.common;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class VinUtil {

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

    // //将校验位之外的16位的每一位的加权系数乘以此位数字或字母的对应值，再将各乘积相加，求得的和被11除，除的的余数即为校验位，如果余数是10，校验位应为字母X
//    public final static boolean isLegal(String vin) {
//
//        if (null == vin) {
//            return false;
//        } else if (vin.trim().length() == 17) {
//            vin = vin.trim().toLowerCase();
//            char[] codes = vin.toCharArray();
//
//            int resultInCode = 0;
//            if ("0123456789".contains(vin.subSequence(8, 9))) {
//                resultInCode = Integer
//                        .valueOf(vin.subSequence(8, 9).toString());
//            } else {
//                if ("x".equals(vin.subSequence(8, 9))) {
//                    resultInCode = 10;
//                }else{
//                    return false;
//                }
//            }
//
//            int total = 0;
//            for (int i = 1; i < codes.length + 1; i++) {
//                char code = codes[i - 1];
//
//                if (kv.containsKey(code)) {
//                    if (9 == i) {
//                        continue;
//                    } else {
//                        total += kv.get(code) * wv.get(i);
//                    }
//                } else {
//                    return false;
//                }
//            }
//            return resultInCode == total % 11;
//        } else {
//            return false;
//        }
//    }

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

    public static void main(String[] args) {

//        System.out.println(isLegal("UU6JA69691D713820"));
//        System.out.println(isLegal("LFV3A21K7D4262398"));
//        System.out.println(isLegal("LFV3A23C793062656"));
//        System.out.println(isLegal("LSGDC82C11S10203O"));
//        System.out.println(isLegal("LSGDC82C11S102030"));
//        System.out.println(isLegal("LSGUD84X2BE041557"));
//        System.out.println(isLegal("WDDBF4CB2EJ143048"));
//        System.out.println(isLegal("LFP83ACCXD1D99699"));

    }

}