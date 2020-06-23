package com.swj.mybatis;

import java.math.BigDecimal;

/**
 * @author wink~
 * @date 2020/5/19 - 15:55
 */
public class EtlJobLogsVO {
    private BigDecimal jobId;

    private String jobName;

    private Short jobStatus;

    private String jobMode;

    private Long userOperate;

    private String etlSourceTable;

    private String resultMsg;

    private Long logId;

    private Long quantity;

    private String dicItem;

    private BigDecimal dtimeOperate;

    private String triggerTime;

    private String handleTime;

    private String scriptName;

    private String startDate;

    private String endDate;

    @Override
    public String toString() {
        return "EtlJobLogsVO{" +
            "jobId=" + jobId +
            ", jobName='" + jobName + '\'' +
            ", jobStatus=" + jobStatus +
            ", jobMode='" + jobMode + '\'' +
            ", userOperate=" + userOperate +
            ", etlSourceTable='" + etlSourceTable + '\'' +
            ", resultMsg='" + resultMsg + '\'' +
            ", logId=" + logId +
            ", quantity=" + quantity +
            ", dicItem='" + dicItem + '\'' +
            ", dtimeOperate=" + dtimeOperate +
            ", triggerTime='" + triggerTime + '\'' +
            ", handleTime='" + handleTime + '\'' +
            ", scriptName='" + scriptName + '\'' +
            ", startDate='" + startDate + '\'' +
            ", endDate='" + endDate + '\'' +
            '}';
    }

    public BigDecimal getJobId() {
        return jobId;
    }

    public void setJobId(BigDecimal jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Short getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Short jobStatus) {
        this.jobStatus = jobStatus;
    }

    public String getJobMode() {
        return jobMode;
    }

    public void setJobMode(String jobMode) {
        this.jobMode = jobMode;
    }

    public Long getUserOperate() {
        return userOperate;
    }

    public void setUserOperate(Long userOperate) {
        this.userOperate = userOperate;
    }

    public String getEtlSourceTable() {
        return etlSourceTable;
    }

    public void setEtlSourceTable(String etlSourceTable) {
        this.etlSourceTable = etlSourceTable;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDicItem() {
        return dicItem;
    }

    public void setDicItem(String dicItem) {
        this.dicItem = dicItem;
    }

    public BigDecimal getDtimeOperate() {
        return dtimeOperate;
    }

    public void setDtimeOperate(BigDecimal dtimeOperate) {
        this.dtimeOperate = dtimeOperate;
    }

    public String getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(String triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(String handleTime) {
        this.handleTime = handleTime;
    }

    public String getScriptName() {
        return scriptName;
    }

    public void setScriptName(String scriptName) {
        this.scriptName = scriptName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
