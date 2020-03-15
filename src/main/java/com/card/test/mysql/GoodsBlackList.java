package com.card.test.mysql;

import java.util.Date;

/**
 * Created by cuihp on 2020/2/9.
 */
public class GoodsBlackList {
    private long id;
    private int mhotelId;
    private int shotelId;
    private int sroomId;
    private int ratePlanId;
    private int supplierId;
    private int groupId;
    private int distType;
    private int status;
    private String creater;
    private Date createTime;
    private String createTimeString;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getMhotelId() {
        return mhotelId;
    }

    public void setMhotelId(int mhotelId) {
        this.mhotelId = mhotelId;
    }

    public int getShotelId() {
        return shotelId;
    }

    public void setShotelId(int shotelId) {
        this.shotelId = shotelId;
    }

    public int getSroomId() {
        return sroomId;
    }

    public void setSroomId(int sroomId) {
        this.sroomId = sroomId;
    }

    public int getRatePlanId() {
        return ratePlanId;
    }

    public void setRatePlanId(int ratePlanId) {
        this.ratePlanId = ratePlanId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDistType() {
        return distType;
    }

    public void setDistType(int distType) {
        this.distType = distType;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateTimeString() {
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }
}
