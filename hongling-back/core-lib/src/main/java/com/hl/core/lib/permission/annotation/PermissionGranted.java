package com.hl.core.lib.permission.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Describe:
 * @Package: com.hl.core.lib.permission.annotation
 * @Author: liyu
 * @Date: 2018/1/11/011 15:10
 * @Copyright: hl
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface PermissionGranted {

}
