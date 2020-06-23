package com.swj.mybatis;

/**
 * @author wink~
 * @date 2020/5/14 - 10:04
 */
public class DataImportVO {
    private Integer interfaceId;

    private Integer jb;

    private String interfaceName;

    //与interfaceName拼接用户前台展示说明
    private String additionalInstruction;

    private Integer state;

    private String errlog;

    private String operator;

    private String endDate;

    //标识是否可以具有导入权限，0：不可以 1:可以
    private Integer canImport;

    private String interfaceType;

    private Integer isAutomatic;

    private String fileName;

    private String wjlj;

    private String enName;

    private Short lSjlydm;

    private String importPath;

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
    }

    public Integer getJb() {
        return jb;
    }

    public void setJb(Integer jb) {
        this.jb = jb;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getAdditionalInstruction() {
        return additionalInstruction;
    }

    public void setAdditionalInstruction(String additionalInstruction) {
        this.additionalInstruction = additionalInstruction;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getErrlog() {
        return errlog;
    }

    public void setErrlog(String errlog) {
        this.errlog = errlog;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getCanImport() {
        return canImport;
    }

    public void setCanImport(Integer canImport) {
        this.canImport = canImport;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public Integer getIsAutomatic() {
        return isAutomatic;
    }

    public void setIsAutomatic(Integer isAutomatic) {
        this.isAutomatic = isAutomatic;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getWjlj() {
        return wjlj;
    }

    public void setWjlj(String wjlj) {
        this.wjlj = wjlj;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public Short getlSjlydm() {
        return lSjlydm;
    }

    public void setlSjlydm(Short lSjlydm) {
        this.lSjlydm = lSjlydm;
    }

    public String getImportPath() {
        return importPath;
    }

    public void setImportPath(String importPath) {
        this.importPath = importPath;
    }

    @Override
    public String toString() {
        return "DataImportListParam{" +
            "interfaceId=" + interfaceId +
            ", jb=" + jb +
            ", interfaceName='" + interfaceName + '\'' +
            ", additionalInstruction='" + additionalInstruction + '\'' +
            ", state=" + state +
            ", errlog='" + errlog + '\'' +
            ", operator='" + operator + '\'' +
            ", endDate='" + endDate + '\'' +
            ", canImport=" + canImport +
            ", interfaceType='" + interfaceType + '\'' +
            ", isAutomatic=" + isAutomatic +
            ", fileName='" + fileName + '\'' +
            ", wjlj='" + wjlj + '\'' +
            ", enName='" + enName + '\'' +
            ", lSjlydm=" + lSjlydm +
            ", importPath='" + importPath + '\'' +
            '}';
    }
}
