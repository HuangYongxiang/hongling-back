package com.hl.contract.business.main.service.dto;

/**  轮询任务时，显示更新的案件数量和任务数量
 *
 * Created by wxl on 2017/8/30.
 */
public class AcceptTaskResult {

    private int reportNum;  //案件数量

    private int taskNum ; //任务数量

    public int getReportNum() {
        return reportNum;
    }

    public void setReportNum(int reportNum) {
        this.reportNum = reportNum;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }
}
