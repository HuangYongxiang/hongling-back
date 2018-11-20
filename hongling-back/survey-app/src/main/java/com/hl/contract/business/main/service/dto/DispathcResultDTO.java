package com.hl.contract.business.main.service.dto;

import java.util.List;

/**
 * Created by hl on 2017/9/19.
 */

public class DispathcResultDTO {
    private LpReportDTO lpReport;
    private LpInsuranceCarDTO lpInsuranceCar;
    private List<LpFlowDTO> taskList;
    private List<LpPolicyDTO> policyList;
    private List<LpReportCarDTO> lpReportCarList;
    private List<LpReportDriverDTO> lpReportDriverList;
    private List<LpReportLossesDTO> lpReportLossesList;
    private List<LpReportHumanTrackingDTO> lpReportHumanTrackingList;
    private List<LpHistoricalReportInfoDTO> historicalreportInfoDTOList;
    private List<LpHistoricalpaymentInfoDTO> historicalpaymentInfoDTOList;

    public List<LpHistoricalpaymentInfoDTO> getHistoricalpaymentInfoDTOList() {
        return historicalpaymentInfoDTOList;
    }

    public void setHistoricalpaymentInfoDTOList(List<LpHistoricalpaymentInfoDTO> historicalpaymentInfoDTOList) {
        this.historicalpaymentInfoDTOList = historicalpaymentInfoDTOList;
    }

    public LpReportDTO getLpReport() {
        return lpReport;
    }

    public void setLpReport(LpReportDTO lpReport) {
        this.lpReport = lpReport;
    }

    public LpInsuranceCarDTO getLpInsuranceCar() {
        return lpInsuranceCar;
    }

    public void setLpInsuranceCar(LpInsuranceCarDTO lpInsuranceCar) {
        this.lpInsuranceCar = lpInsuranceCar;
    }

    public List<LpFlowDTO> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<LpFlowDTO> taskList) {
        this.taskList = taskList;
    }

    public List<LpPolicyDTO> getPolicyList() {
        return policyList;
    }

    public void setPolicyList(List<LpPolicyDTO> policyList) {
        this.policyList = policyList;
    }

    public List<LpReportCarDTO> getLpReportCarList() {
        return lpReportCarList;
    }

    public void setLpReportCarList(List<LpReportCarDTO> lpReportCarList) {
        this.lpReportCarList = lpReportCarList;
    }

    public List<LpReportDriverDTO> getLpReportDriverList() {
        return lpReportDriverList;
    }

    public void setLpReportDriverList(List<LpReportDriverDTO> lpReportDriverList) {
        this.lpReportDriverList = lpReportDriverList;
    }

    public List<LpReportLossesDTO> getLpReportLossesList() {
        return lpReportLossesList;
    }

    public void setLpReportLossesList(List<LpReportLossesDTO> lpReportLossesList) {
        this.lpReportLossesList = lpReportLossesList;
    }

    public List<LpReportHumanTrackingDTO> getLpReportHumanTrackingList() {
        return lpReportHumanTrackingList;
    }

    public void setLpReportHumanTrackingList(List<LpReportHumanTrackingDTO> lpReportHumanTrackingList) {
        this.lpReportHumanTrackingList = lpReportHumanTrackingList;
    }

    public List<LpHistoricalReportInfoDTO> getHistoricalreportInfoDTOList() {
        return historicalreportInfoDTOList;
    }

    public void setHistoricalreportInfoDTOList(List<LpHistoricalReportInfoDTO> historicalreportInfoDTOList) {
        this.historicalreportInfoDTOList = historicalreportInfoDTOList;
    }
}
