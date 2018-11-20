package com.hl.core.lib.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hl.core.lib.R;
import com.hl.core.lib.activity.CoreHandler;

/**
 * @Describe: 加载框
 * @Package: com.hl.core.lib.fragment
 * @Author: liyu
 * @Date: 2018/2/23 11:29
 * @Copyright: hl
 */
public class LoadingDialogFragment extends DialogFragment implements CoreHandler.CallBack {

    private CoreHandler coreHandler;
    private FragmentManager fragmentManager;
    private ImageView iv_load_result;// 加载的结果图标显示
    private TextView tv_load;// 加载的文字展示
    private ProgressBar pb_loading;// 加载中的图片
    private String showText = "加载中...";
    private final int SHOW_LOADING_DIALOG = 0x001;//显示加载框
    private final int DISMISS_DIALOG = 0x002;//关闭加载框
    private final int DIALOG_LOAD_SUCCEED = 0x003;//加载成功
    private final int DIALOG_LOAD_FAILED = 0x004;//加载失败
    private int showDialogTimes = 0;//显示加载框次数

    public LoadingDialogFragment bindHandler(CoreHandler coreHandler){
        this.coreHandler = coreHandler;
        if(coreHandler != null)
            coreHandler.addCallBack(this);
        return this;
    }

    public LoadingDialogFragment bindFragmentManager(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
        return this;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.core_loading_dialog_style);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.core_commom_loading_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        iv_load_result = (ImageView) view.findViewById(R.id.core_load_result);
        tv_load = (TextView) view.findViewById(R.id.core_tv_load);
        pb_loading = (ProgressBar) view.findViewById(R.id.core_pb_loading);
        iv_load_result.setVisibility(View.GONE);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Window dialogWindow = getDialog().getWindow();
        if(dialogWindow != null){
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.gravity = Gravity.CENTER;
            dialogWindow.setAttributes(lp);
        }
        getDialog().setCancelable(false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener((dialogInterface, i, keyEvent) -> {
            dismiss();
            return false;
        });
        text(showText);
    }

    public LoadingDialogFragment text(String text){
        if(tv_load != null && !TextUtils.isEmpty(text)){
            tv_load.setText(text);
        }
        showText = text;
        return this;
    }

    //显示对话框
    private void showDialog(FragmentManager manager,String tip){
        if(TextUtils.isEmpty(tip)){
            text(showText);
        }else{
            if(tv_load != null && !TextUtils.isEmpty(tip)){
                tv_load.setText(tip);
            }
        }
        if(null != pb_loading && pb_loading.getVisibility() != View.VISIBLE){
            pb_loading.setVisibility(View.VISIBLE);
        }
        if(null != iv_load_result){
            iv_load_result.setVisibility(View.GONE);
        }
        manager.executePendingTransactions();
        if(!isAdded()&&!isVisible()&&!isRemoving()){
            show(manager,"LoadingDialogFragment_TAG");
        }
    }

    public void dismissDialog(){
        if(getActivity() != null && !getActivity().isFinishing()){
            dismiss();
        }
    }

    // 加载成功
    private void succeed(String text) {
        if(pb_loading != null)
            pb_loading.setVisibility(View.GONE);
        if(iv_load_result != null){
            iv_load_result.setVisibility(View.VISIBLE);
            iv_load_result.setImageResource(R.mipmap.core_load_suc_icon);
        }
        tv_load.setText(TextUtils.isEmpty(text)?"加载成功":text);
    }

    // 加载失败
    private void failed(String text) {
        if(pb_loading != null)
            pb_loading.setVisibility(View.GONE);
        if(iv_load_result != null){
            iv_load_result.setVisibility(View.VISIBLE);
            iv_load_result.setImageResource(R.mipmap.core_load_fail_icon);
        }
        tv_load.setText(TextUtils.isEmpty(text)?"加载失败":text);
    }

    public void succeed(){
        succeed(null);
    }

    public void failed(){
        failed(null);
    }

    //显示加载框
    public void showLoadingDialog(){
        if(coreHandler != null){
            coreHandler.sendEmptyMessage(SHOW_LOADING_DIALOG);
        }
    }

    //显示加载框
    public void showLoadingDialog(String tip){
        if(coreHandler != null){
            Message msg = new Message();
            msg.what = SHOW_LOADING_DIALOG;
            msg.obj = tip;
            coreHandler.sendMessage(msg);
        }
    }

    //关闭加载框
    public void dismissLoadingDialog(){
        //延迟600ms消失，避免提示一闪而过问题
        if(coreHandler != null)
            coreHandler.sendEmptyMessageDelayed(DISMISS_DIALOG,600);
    }

    //显示加载失败
    public void showLoadFailedDialog(){
        if(coreHandler != null)
            coreHandler.sendEmptyMessage(DIALOG_LOAD_FAILED);
    }

    //显示加载成功
    public void showLoadSucceedDialog(){
        coreHandler.sendEmptyMessage(DIALOG_LOAD_SUCCEED);
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_LOADING_DIALOG:
                if(fragmentManager != null){
                    ++showDialogTimes;
                    Dialog dialog = getDialog();
                    String tip = "";
                    if(msg.obj != null){
                        tip = String.valueOf(msg.obj);
                    }
                    if(dialog == null){
                        showDialog(fragmentManager,tip);
                    }
                }
                break;
            case DIALOG_LOAD_SUCCEED:
                succeed();
                break;
            case DIALOG_LOAD_FAILED:
                failed();
                break;
            case DISMISS_DIALOG:
                //当多次打开加载框时，在最后一个关闭命令到达时关闭加载框
                --showDialogTimes;
                if(showDialogTimes <= 0){
                    showDialogTimes = 0;
                    Dialog dialog = getDialog();
                    if(dialog != null && dialog.isShowing()){
                        dismissDialog();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy() {
        if(null != coreHandler)
            coreHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
