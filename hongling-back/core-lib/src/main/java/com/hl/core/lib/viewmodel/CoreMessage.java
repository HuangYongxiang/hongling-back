package com.hl.core.lib.viewmodel;

/**
 * @Describe: 回调信息体
 * @Package: com.hl.core.lib.viewmodel
 * @Author: liyu
 * @Date: 2018/3/15 15:25
 * @Copyright: hl
 */
public class CoreMessage {

    public static final int TIP = 65534;//TOAST
    public static final int CLOSE_REFRESH = 65533;//关闭下拉刷新状态
    public static final int LOGIN = 60000;//登录
    public static final int LOGOUT = 60001;//注销
    public static final int SIGN_IN = 60002;//签退、签到
    public static final int SYNC_DICT = 60003;//同步字典
    public static final int CREATE_CONTRACT = 60004;//创建合同

    public int showLoadingDialog = -1;
    public int msgCode = -1;
    public String message = "";

    public CoreMessage(int showLoadingDialog) {
        this.showLoadingDialog = showLoadingDialog;
    }

    public CoreMessage(String message) {
        this.message = message;
    }

    public CoreMessage(int msgCode, String message) {
        this.msgCode = msgCode;
        this.message = message;
    }

    public CoreMessage(int showLoadingDialog, int msgCode, String message) {
        this.showLoadingDialog = showLoadingDialog;
        this.msgCode = msgCode;
        this.message = message;
    }


    @Override
    public String toString() {
        return "CoreMessage{" +
                "showLoadingDialog=" + showLoadingDialog +
                ", msgCode=" + msgCode +
                ", message='" + message + '\'' +
                '}';
    }
}
