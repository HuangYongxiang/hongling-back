package com.hl.core.lib.util.common;

import java.util.UUID;

/**
 * @Describe:生成UUID
 * @Package: com.hl.eval.bean
 * @Author: liyu
 * @Date: 2018/1/12 16:41
 * @Copyright: hl
 */

public class UUIDUtil {
    public static String getUUID(){
       return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
