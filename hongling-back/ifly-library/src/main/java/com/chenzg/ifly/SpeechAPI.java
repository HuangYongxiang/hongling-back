package com.chenzg.ifly;

import android.content.Context;

import com.iflytek.cloud.SpeechUtility;

/**
 * @Describe: 科大讯飞语音API
 * @Package: com.liyu.ifly
 * @Author: liyu
 * @Date: 2018/3/26 16:11
 * @Copyright: hl
 */
public class SpeechAPI {


    public static void init(Context context,String appId){
        SpeechUtility.createUtility(context, appId);
    }


}
