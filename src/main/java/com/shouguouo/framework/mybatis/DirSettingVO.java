package com.shouguouo.framework.mybatis;

/**
 * @author shouguouo~
 * @date 2020/5/14 - 10:04
 */
public class DirSettingVO {

    private Integer interfaceId;

    private String interfaceName;

    //与interfaceName拼接用户前台展示说明
    private String additionalInstruction;

    private String interfaceType;

    private String fileDir;

    private String fileName;

    private Short impType;

    private Integer jb;

    @Override
    public String toString() {
        return "DirSettingListParam{" +
            "interfaceId=" + interfaceId +
            ", interfaceName='" + interfaceName + '\'' +
            ", additionalInstruction='" + additionalInstruction + '\'' +
            ", interfaceType='" + interfaceType + '\'' +
            ", fileDir='" + fileDir + '\'' +
            ", fileName='" + fileName + '\'' +
            ", impType=" + impType +
            ", jb=" + jb +
            '}';
    }

    public Integer getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(Integer interfaceId) {
        this.interfaceId = interfaceId;
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

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Short getImpType() {
        return impType;
    }

    public void setImpType(Short impType) {
        this.impType = impType;
    }

    public Integer getJb() {
        return jb;
    }

    public void setJb(Integer jb) {
        this.jb = jb;
    }
}
