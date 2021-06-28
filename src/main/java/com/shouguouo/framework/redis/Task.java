package com.shouguouo.framework.redis;

import java.io.Serializable;

public class Task implements Serializable {

    private static final long serialVersionUID = 4924922426941406504L;

    private String sourceDb;

    private String targetDb;

    private Integer table;

    public String getSourceDb() {
        return sourceDb;
    }

    public void setSourceDb(String sourceDb) {
        this.sourceDb = sourceDb;
    }

    public String getTargetDb() {
        return targetDb;
    }

    public void setTargetDb(String targetDb) {
        this.targetDb = targetDb;
    }

    public Integer getTable() {
        return table;
    }

    public void setTable(Integer table) {
        this.table = table;
    }

    public Task() {
    }

    public Task(String sourceDb, String targetDb, Integer table) {
        this.sourceDb = sourceDb;
        this.targetDb = targetDb;
        this.table = table;
    }

    @Override
    public String toString() {
        return "Task{" +
            "sourceDb='" + sourceDb + '\'' +
            ", targetDb='" + targetDb + '\'' +
            ", table=" + table +
            '}';
    }
}