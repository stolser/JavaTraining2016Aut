package com.stolser.javatraining.webproject.model.entity;

import com.stolser.javatraining.webproject.model.entity.periodical.CostPlan;
import com.stolser.javatraining.webproject.model.entity.periodical.Periodical;
import com.stolser.javatraining.webproject.model.entity.user.User;

import java.util.Date;

public class Subscription {
    private long id;
    private User user;
    private Periodical periodical;
    private CostPlan costPlan;
    private int packSize;
    private String deliveryAddress;
    private Date creationDate;
    private Date startDate;
    private Date endDate;
    private Status status;

    public enum Status {
        NEW, ACTIVE, SUSPENDED, STOPPED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public CostPlan getCostPlan() {
        return costPlan;
    }

    public void setCostPlan(CostPlan costPlan) {
        this.costPlan = costPlan;
    }

    public int getPackSize() {
        return packSize;
    }

    public void setPackSize(int packSize) {
        this.packSize = packSize;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
