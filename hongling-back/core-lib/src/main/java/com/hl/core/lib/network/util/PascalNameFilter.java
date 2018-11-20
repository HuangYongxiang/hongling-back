package com.hl.core.lib.network.util;

import com.alibaba.fastjson.serializer.NameFilter;

/**
 * @Describe:对json转换实体类时转换首字母大写工具类
 * @Package: com.hl.core.lib.network.util
 * @Author: liyu
 * @Date: 2018/1/17 0017 上午 10:46
 * @Copyright: hl
 */
public class PascalNameFilter implements NameFilter {
    public String process(Object source, String name, Object value) {
        if (name == null || name.length() == 0) {
            return name;
        }

        char[] chars = name.toCharArray();
        chars[0]= Character.toUpperCase(chars[0]);

        String pascalName = new String(chars);
        return pascalName;
    }
}
