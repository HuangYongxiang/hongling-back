package com.hl.photo.business.entity;

/**
 * po基类，继承此类防止代码混淆产生的Gson解析异常
 * @Package com.xunlei.video.framework
 * @ClassName: BasePo
 * @author Beethoven
 * @mail zhanghuitao@xunlei.com
 * @date Apr 16, 2014 3:47:29 PM
 */
public class BasePo {
    
    private boolean checked;

    /**
     * 是否选中（多选模式）
     * @Title: isChecked
     * @return boolean
     * @date May 20, 2014 12:23:49 AM
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * 设置是否选中（多选模式）
     * @Title: setChecked
     * @param checked
     * @return void
     * @date May 20, 2014 12:24:11 AM
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    
    /**
     * 错误码，只有接口返回400时有值
     */
    public int errorCode;
    
    /**
     * 错误信息，只有接口返回400时有值
     */
    public String message;
    
}
