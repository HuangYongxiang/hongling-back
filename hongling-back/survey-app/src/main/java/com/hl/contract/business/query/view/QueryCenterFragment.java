package com.hl.contract.business.query.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hl.contract.R;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.core.BaseFragment;
import com.hl.contract.core.plugin.SurveyTitleBar;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TimestampTool;
import com.hl.core.lib.util.UtilManager;
import com.jock.pickerview.view.TimePickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @Describe: 综合查询
 * @Author: liyu
 * @Date: 2018/3/30 9:28
 * @Copyright: hl
 */
public class QueryCenterFragment extends BaseFragment<SurveyTitleBar> {

    private TimePickerView timePickerView;
    private String selectedType;
    private final String SELECTED_REPORT_NO = "1";
    private final String SELECTED_PLATE_NO = "2";
    private final String SELECTED_INSURED = "3";

    SimpleDateFormat simpleDateFormat = null;
    TextView reportTimeStart_tv;
    TextView reportTimeEnd_tv;
    EditText queryContent_et;

    @Override
    protected void initTitle(SurveyTitleBar titleBar) {
        super.initTitle(titleBar);
        titleBar.title = "综合查询";
    }

    @Override
    protected Object initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.survey_query_center_fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        queryContent_et = (EditText) view.findViewById(R.id.query_content_et);
        reportTimeStart_tv = (TextView) view.findViewById(R.id.report_time_start_tv);
        reportTimeEnd_tv = (TextView) view.findViewById(R.id.report_time_end_tv);
        initPickerTimeView();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        final int id = v.getId();
        switch (id) {
            case R.id.report_time_start_tv://点击报案开始日期
                initTimerByExitTime(reportTimeStart_tv.getText().toString());
                timePickerView.show();
                timePickerView.setOnTimeSelectListener(onTimeSelectListener, reportTimeStart_tv);
                break;
            case R.id.report_time_end_tv://点击报案结束日期
                initTimerByExitTime(reportTimeEnd_tv.getText().toString());
                timePickerView.show();
                timePickerView.setOnTimeSelectListener(onTimeSelectListener, reportTimeEnd_tv);
                break;
            case R.id.report_no_rb:
                selectedType = SELECTED_REPORT_NO;
                break;
            case R.id.plate_no_rb:
                selectedType = SELECTED_PLATE_NO;
                break;
            case R.id.insured_rb:
                selectedType = SELECTED_INSURED;
                break;
            case R.id.query_tv://点击查询
                query();
                break;
        }
    }

    private void query() {
        hideSoftInput();
        String reportTimeStart = reportTimeStart_tv.getText().toString();
        String reportTimeEnd = reportTimeEnd_tv.getText().toString();
        String currentTime = TimestampTool.getCurrentDate();
        String content = queryContent_et.getText().toString();
        if ((!TextUtils.isEmpty(selectedType) && !TextUtils.isEmpty(content)) || (!TextUtils.isEmpty(reportTimeStart) && !TextUtils.isEmpty(reportTimeEnd))) {

            //检验时间 起始时间不能大于终止时间
            if (!TextUtils.isEmpty(reportTimeStart) && !TextUtils.isEmpty(reportTimeEnd)) {
                if (reportTimeStart.compareTo(reportTimeEnd) > 0) {
                    UtilManager.Toast.show(getContext(), "起始时间不能大于终止时间");
                    return;
                }
            }

            if (!TextUtils.isEmpty(reportTimeStart) && !TextUtils.isEmpty(currentTime)) {
                if (reportTimeStart.compareTo(currentTime) > 0) {
                    UtilManager.Toast.show(getContext(), "起始时间不能大于当前时间");
                    return;
                }
            }

//            if (!TextUtils.isEmpty(reportTimeEnd) && !TextUtils.isEmpty(currentTime)) {
//                if (reportTimeEnd.compareTo(currentTime) > 0) {
//                    UtilManager.Toast.show(getContext(), "结束时间不能大于当前时间");
//                    return;
//                }
//            }
            //校验时间，最长的时间不可以查过32
            try {
                Date dateStart = simpleDateFormat.parse(reportTimeStart);
                Date dateEnd = simpleDateFormat.parse(reportTimeEnd);
                GregorianCalendar calStart = new GregorianCalendar();
                GregorianCalendar calEnd = new GregorianCalendar();
                calStart.setTime(dateStart);
                calEnd.setTime(dateEnd);
                double dayCount = (calEnd.getTimeInMillis() - calStart.getTimeInMillis()) / (1000 * 3600 * 24);//从间隔毫秒变成间隔天数
                if (dayCount > 32) {
                    UtilManager.Toast.show(getContext(), "查询间隔不能超过32天");
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            ContractListDTO dto = new ContractListDTO();
            dto.setName(content);
            dto.setStartDate(reportTimeStart);
            dto.setEndDate(reportTimeEnd);
            dto.setAccount(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_NAME, ""));
//            dto.setStartData(TimestampTool.getPreviousDay(reportTimeStart,1));
//            dto.setEndDate(TimestampTool.getNextDay(reportTimeEnd,1));

//            QueryTaskDTO queryTaskDTO = new QueryTaskDTO();
//            if(SELECTED_REPORT_NO.equals(selectedType)){
//                queryTaskDTO.setReportCode(content);
//            }else if(SELECTED_PLATE_NO.equals(selectedType)){
//                queryTaskDTO.setPlateNum(content);
//            }else if(SELECTED_INSURED.equals(selectedType)){
//                queryTaskDTO.setInsuredPersonName(content);
//            }
//            queryTaskDTO.setReportStartTime(TimestampTool.getPreviousDay(reportTimeStart,1));
//            queryTaskDTO.setReportEndTime(TimestampTool.getNextDay(reportTimeEnd,1));
//            queryTaskDTO.setUserCode(UtilManager.SP.survey().getString(SurveyClaimUtil.LOGIN_NAME,""));

            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.serializeNulls().create();
            String json = gson.toJson(dto);
            Bundle bundle = new Bundle();
            bundle.putString("json", json);
            startActivity(QueryResultActivity.class, bundle);
        } else {
            UtilManager.Toast.show(getContext(), "至少输入一个查询条件");
        }
    }

    //时间选择器回调
    TimePickerView.OnTimeSelectListener onTimeSelectListener = (date, textView) -> {
        if (textView != null) {
            textView.setText(TimestampTool.date2StringYMD(date));
        }
    };

    //初始化 时间选择器
    private void initPickerTimeView() {
        // 时间选择器
        timePickerView = new TimePickerView(getContext(), TimePickerView.Type.YEAR_MONTH_DAY);
        timePickerView.setTime(new Date());
        timePickerView.setCyclic(false);
        timePickerView.setCancelable(true);
        // 初始化查询时间  默认时间7天 ,当前日期往后7天
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        String yesterday_7 = simpleDateFormat.format(calendar.getTime());

        reportTimeStart_tv.setText(yesterday_7);
//        Date date = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(date);
//        cal.add(Calendar.DATE, 1);
        reportTimeEnd_tv.setText(simpleDateFormat.format(new Date()));
    }

    /**
     * 根据当前的时间初始化日期选择器
     */
    private void initTimerByExitTime(String time) {
        if (TextUtils.isEmpty(time)) {
            return;
        }
        try {
            Date date = simpleDateFormat.parse(time);
            timePickerView.setTime(date);
        } catch (Exception e) {
            e.printStackTrace();
            timePickerView.setTime(new Date());
        }

    }

}
