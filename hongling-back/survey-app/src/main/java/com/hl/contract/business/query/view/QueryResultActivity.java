package com.hl.contract.business.query.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.hl.contract.R;
import com.hl.contract.business.main.service.dto.ContractListDTO;
import com.hl.contract.business.main.view.QueryContractQueryActivity;
import com.hl.contract.business.main.viewmodel.MainPageVM;
import com.hl.contract.business.query.adapter.QueryTaskItemAdapter;
import com.hl.contract.core.BaseActivity;
import com.hl.contract.table.model.Contract;
import com.hl.core.lib.plugin.title.TitleBar;
import com.hl.core.lib.viewmodel.ViewModel;

import java.util.List;
public class QueryResultActivity extends BaseActivity<TitleBar> implements QueryTaskItemAdapter.TaskListener,SwipeRefreshLayout.OnRefreshListener{

    @ViewModel
    private MainPageVM mainPageVM;


    private ContractListDTO queryTaskDTO;
    private int currentPage = 1;
    private int totalPage = 0;
    private SwipeRefreshLayout viewLoadMore;
    private QueryTaskItemAdapter taskAdapter;
    private RecyclerView taskListView;
    private View recycleHeaderView;
    //防止一次滑动过程中，重复加载
    private boolean mBottomRefreshing;
    //是否加载到最后
    private boolean isLastRecord;

    @Override
    protected Object entryInterceptor(Intent intent) {
        if(intent == null || intent.getExtras() == null)
            return "请填写查询信息";
        String requestJson = intent.getExtras().getString("json");
        queryTaskDTO =  new Gson().fromJson(requestJson, ContractListDTO.class);
        return super.entryInterceptor(intent);
    }

    @Override
    protected void initTitle(TitleBar titleBar) {
        titleBar.title = "查询结果";
    }

    @Override
    protected Object initLayout() {
        return R.layout.survey_activity_query_result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskListView = (RecyclerView) findViewById(R.id.task_list_view);
        viewLoadMore = (SwipeRefreshLayout) findViewById(R.id.view_load_more);

        recycleHeaderView = LayoutInflater.from(QueryResultActivity.this).inflate(R.layout.survey_recycle_view_header_layout,null);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        recycleHeaderView.setLayoutParams(layoutParams);
        recycleHeaderView.setVisibility(View.GONE);

        LinearLayoutManager layoutManager = new LinearLayoutManager(QueryResultActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        taskListView.setLayoutManager(layoutManager);



        //以下代码用于下拉下载报案数据
        //设置刷新时动画的颜色，可以设置4个
        viewLoadMore.setProgressBackgroundColorSchemeResource(android.R.color.white);
        viewLoadMore.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        viewLoadMore.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        viewLoadMore.setOnRefreshListener(this);
        getTaskFromNet(false);
    }



    @Override
    public void onRefresh() {
        getTaskFromNet(false);
    }



    //从网络获取任务列表
    public void getTaskFromNet(boolean showLoadingDialog){
        mainPageVM.getTaskFromNetByContent(showLoadingDialog,queryTaskDTO).observeOnce(this, reportInfo -> loadData(reportInfo,false));
    }

    //装载数据并刷新UI
    private void loadData(List<Contract> reportInfo, boolean fromDB){
        closeRefresh();
        //当从数据库查询没有数据时自动请求网络获取
//        if(reportInfo==null){
//            getTaskFromNet(true);
//            return ;
//        }
        taskAdapter = new QueryTaskItemAdapter(QueryResultActivity.this,this);
        taskListView.setAdapter(taskAdapter);
//        taskAdapter.clear();
        taskAdapter.addAll(reportInfo);
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
    //关闭刷新状态
    private void closeRefresh(){
        if(viewLoadMore != null && viewLoadMore.isRefreshing()){
            viewLoadMore.setRefreshing(false);
        }
    }


}
