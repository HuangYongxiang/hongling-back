package com.hl.core.lib.util;

import android.view.Window;

import com.hl.core.lib.util.common.ButtonUtils;
import com.hl.core.lib.util.common.CheckDataUtil;
import com.hl.core.lib.util.common.DensityUtil;
import com.hl.core.lib.util.common.LogUtil;
import com.hl.core.lib.util.common.PopupWindowUtil;
import com.hl.core.lib.util.common.SelectorUtil;
import com.hl.core.lib.util.common.SystemUtil;
import com.hl.core.lib.util.common.ToastUtil;
import com.hl.core.lib.util.common.UUIDUtil;
import com.hl.core.lib.util.common.UnCodeUtile;
import com.hl.core.lib.util.common.ValidateUtil;
import com.hl.core.lib.util.common.dialog.DialogUtil;
import com.hl.core.lib.util.common.TimeUtil;
import com.hl.core.lib.util.common.sp.SPUtil;

/**
 * @Describe: 工具管理中心
 * @Package: com.hl.core.lib.util
 * @Author: liyu
 * @Date: 2018/1/2 11:50
 * @Copyright: hl
 */
public class UtilManager {

    //SharePreference
    public static SPUtil SP = new SPUtil();

    //日志打印
    public static LogUtil Log = new LogUtil();

    //Toast提示
    public static ToastUtil Toast = new ToastUtil();

    //像素、密度计算与转换
    public static DensityUtil Density = new DensityUtil();

    //应用、系统相关信息
    public static SystemUtil System = new SystemUtil();

    //时间工具
    public static TimeUtil TimeUtil = new TimeUtil();

    //popupWindow
    public static PopupWindowUtil getPopupWindowUtil(Window window){
        return  PopupWindowUtil.getInitince(window);
    }

    //自定义对话框
    public static DialogUtil DialogUtil = new DialogUtil();

    //防止连续点击
    public static ButtonUtils ButtonUtils = new ButtonUtils();

    //生成 uuid
    public static UUIDUtil UUIDUtil = new UUIDUtil();

    //数据合法性检测   VIN码、身份证、手机号、车牌号等
    public static CheckDataUtil CheckDataUtil = new CheckDataUtil();

    public static UnCodeUtile UnCodeUtile=new UnCodeUtile();

    //选择器、获取主题颜色工具类
    public static SelectorUtil selector = new SelectorUtil();

    //手机号 车牌号校验
    public static ValidateUtil ValidateUtil = new ValidateUtil();
}
