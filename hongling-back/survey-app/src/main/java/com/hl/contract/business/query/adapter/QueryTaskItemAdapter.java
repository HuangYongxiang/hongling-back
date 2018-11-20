package com.hl.contract.business.query.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hl.core.lib.adapter.BaseRecyclerViewAdapter;
import com.hl.contract.R;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;

/**
 * Author : liyu
 * Description :
 * Date : 2017/7/13.
 */
public class QueryTaskItemAdapter extends BaseRecyclerViewAdapter<Contract, RecyclerView.ViewHolder> {

    private Context context;
    public QueryTaskItemAdapter(Context context, TaskListener taskListener) {
        this.context = context;
        this.taskListener = taskListener;
    }

    private TaskListener taskListener;
    public interface TaskListener{

        /**
         * 跳转报案信息页面
         * @param bundle 参数
         */
        void gotoReportPage(Bundle bundle);

        /**
         * 跳转任务页面
         * @param taskType 任务类型
         * @param bundle 参数
         */
        void gotoTaskPage(String taskType, Bundle bundle);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(context == null){
            context = parent.getContext();
        }
        return new MyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.survey_task_item_top_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((MyHolder) viewHolder).bind(getItem(position));
        super.onBindViewHolder(viewHolder, position);
    }

    class MyHolder extends RecyclerView.ViewHolder{

        LinearLayout linear_layout;
        LinearLayout report_layout;
        TextView plateNo_tv;
        TextView reportName_tv;
        TextView reportDate_tv;
        TextView reportNo_tv;
        TextView reportStatue_tv;

        private MyHolder(View itemView) {
            super(itemView);
            linear_layout = (LinearLayout) itemView.findViewById(R.id.linear_layout);
            report_layout = (LinearLayout) itemView.findViewById(R.id.report_layout);
            plateNo_tv = (TextView) itemView.findViewById(R.id.case_plate_no_tv);
            reportName_tv = (TextView) itemView.findViewById(R.id.case_report_name_tv);
            reportDate_tv = (TextView) itemView.findViewById(R.id.case_report_date_tv);
            reportNo_tv = (TextView) itemView.findViewById(R.id.case_report_no_tv);
            reportStatue_tv = (TextView) itemView.findViewById(R.id.case_report_statue_tv);
        }

        void bind(Contract item){
            if (item != null) {
                //报案
                plateNo_tv.setText(item.getPlateNo());
                reportName_tv.setText(item.getOwnerName());
//                //把时间改为格式:2006-07-05显示
//                Timestamp timestamp = null;
//                try {
//                    timestamp = TimestampTool.datetime(item.getReportTime());
//                } catch (NumberFormatException e) {
//
//                }
                reportDate_tv.setText(item.getOwnerTelePhone());
                final String reportCode = item.getReportCode();
                reportNo_tv.setText(reportCode);

                report_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString(SurveyClaimUtil.REPORT_NO,reportCode);
                        bundle.putSerializable("Contract",item);
                        if(taskListener != null){
                            taskListener.gotoReportPage(bundle);
                        }
                    }
                });


                if(item.getExemptFlag()!=null){
                    if("0".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("未支付");
                    }else if("1".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("已支付");
                    }else if("2".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("生效");
                    }else if("4".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("未通过");
                    }else if("5".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("已通过");
                    }else if("6".equals(item.getExemptFlag())){
                        reportStatue_tv.setText("已到期");
                    }else {
                        reportStatue_tv.setText("失效");
                    }
                }


            }
        }
    }
}
