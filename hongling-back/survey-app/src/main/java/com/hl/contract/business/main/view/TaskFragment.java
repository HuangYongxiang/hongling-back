package com.hl.contract.business.main.view;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hl.contract.R;
import com.hl.contract.business.main.adapter.ReportTaskItemAdapter;
import com.hl.contract.business.main.viewmodel.MainPageVM;
import com.hl.contract.core.BaseFragment;
import com.hl.contract.core.plugin.SurveyTitleBar;
import com.hl.contract.table.model.Contract;
import com.hl.contract.util.SurveyClaimUtil;
import com.hl.contract.util.TextUtil;
import com.hl.core.lib.util.UtilManager;
import com.hl.core.lib.viewmodel.CoreMessage;
import com.hl.core.lib.viewmodel.ViewModel;

import java.util.List;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * @Describe: 案件处理页面
 * @Package: com.hl.contract.business.main.view
 * @Author: liyu
 * @Date: 2018/3/28 10:57
 * @Copyright: hl
 */
public class  TaskFragment extends BaseFragment<SurveyTitleBar>
        implements ReportTaskItemAdapter.TaskListener,SwipeRefreshLayout.OnRefreshListener{

    @ViewModel
    private MainPageVM mainPageVM;
    private SwipeRefreshLayout viewLoadMore;
    private ReportTaskItemAdapter taskAdapter;
    View recycleHeaderView;
    RecyclerView taskListView;
    TextView pendingReportNum_tv;
    TextView pendingTaskNum_tv;

    @Override
    protected void initTitle(SurveyTitleBar titleBar) {
        super.initTitle(titleBar);
        titleBar.title = "合同处理";
        titleBar.rightBtnText = "新建";
        titleBar.showRightBtn = true;
    }

    @Override
    protected Object initLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.survey_fragment_task;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        scheduleJob();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskListView = (RecyclerView) view.findViewById(R.id.task_list_view);
        viewLoadMore = (SwipeRefreshLayout) view.findViewById(R.id.view_load_more);
        recycleHeaderView = LayoutInflater.from(getActivity()).inflate(R.layout.survey_recycle_view_header_layout,null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        recycleHeaderView.setLayoutParams(layoutParams);
        pendingReportNum_tv = (TextView) recycleHeaderView.findViewById(R.id.item_pending_report);
        pendingTaskNum_tv = (TextView) recycleHeaderView.findViewById(R.id.item_pending_task);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskListView.setLayoutManager(layoutManager);
        taskAdapter = new ReportTaskItemAdapter(getActivity(),this);
        taskListView.setAdapter(taskAdapter);
        taskAdapter.setHeaderView(recycleHeaderView);
        //设置刷新时动画的颜色，可以设置4个
        viewLoadMore.setProgressBackgroundColorSchemeResource(android.R.color.white);
        viewLoadMore.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        viewLoadMore.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        viewLoadMore.setOnRefreshListener(this);
//        getTaskFromNet(false);
    }

    @Override
    public void onRefresh() {
//        getTaskFromNet(false);
    }

    @Override
    public void onResume(){
        super.onResume();
//        getTaskFromNet(false);
    }

    //从数据库中获取任务列表
    private void getTaskFromDB(){
        mainPageVM.getTaskFromDB().observeOnce(this, reportInfo -> loadData(reportInfo,true));
    }

    //装载数据并刷新UI
    private void loadData(List<Contract> reportInfo, boolean fromDB){
        closeRefresh();
        //当从数据库查询没有数据时自动请求网络获取
//        if(reportInfo==null){
//            getTaskFromNet(true);
//            return ;
//        }
        taskAdapter.clear();
        taskAdapter.addAll(reportInfo);
        getClaimTaskNumForDeal(reportInfo);
    }

    //获取需处理案件+任务数
    private void getClaimTaskNumForDeal(List<Contract> list){
        int contractTotal = 0;
        int notExamineTotal = 0;
        if(list!=null&&list.size()>0){
            contractTotal = list.size();
            for (Contract c : list) {
                if(c.getExemptFlag()!=null&&"1".equals(c.getExemptFlag())){

                }else{
                    notExamineTotal++;
                }

            }
        }
        pendingReportNum_tv.setText(String.valueOf(notExamineTotal));
        pendingTaskNum_tv.setText(String.valueOf(contractTotal));
//        mainPageVM.getClaimTaskNumForDeal().observeOnce(this,strings -> {
//            if(strings != null && strings.size() == 2){
//                pendingReportNum_tv.setText(strings.get(0));
//                pendingTaskNum_tv.setText(strings.get(1));
//            }
//        });
    }

    //跳转报案信息页面
    @Override
    public void gotoReportPage(Bundle bundle) {
        startActivity(QueryContractQueryActivity.class,bundle);
    }

    //跳转任务页面
    @Override
    public void gotoTaskPage(final String taskType, Bundle bundle) {
    }


    @Override
    protected void msgCallBack(CoreMessage msg) {
        super.msgCallBack(msg);
        if(msg.msgCode == CoreMessage.CLOSE_REFRESH){
            closeRefresh();
            if(!TextUtils.isEmpty(msg.message)){
                UtilManager.Toast.show(getActivity(),msg.message);
            }
        }else if(msg.msgCode == 1){
        }
    }


    //关闭刷新状态
    private void closeRefresh(){
        if(viewLoadMore != null && viewLoadMore.isRefreshing()){
            viewLoadMore.setRefreshing(false);
        }
    }


    @Override
    public void onClick(View view) {
        super.onClick(view);
        final int id = view.getId();
        switch (id){
            case R.id.title_right_btn:
                final String txt = TextUtil.safeText((TextView) view);
                //点击刷新按钮
                if("新建".equals(txt)){
                    Bundle bundle = new Bundle();
                    bundle.putString(SurveyClaimUtil.REPORT_NO, "");
                    bundle.putString(SurveyClaimUtil.ITEM_ID, "");
                    Contract contract = new Contract();
                    bundle.putSerializable("Contract", contract);
                    startActivity(CreateContractActivity.class,bundle);
                }
                break;
        }
    }


}
