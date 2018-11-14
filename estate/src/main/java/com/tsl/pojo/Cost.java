package com.tsl.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_cost")
public class Cost {
    @Id
    @Column(name = "cost_id")
    private Integer costId;

    private String cname;

    private Date time;

    private Float money;

    @Column(name = "owner_id")
    private Integer ownerId;

    private String state;

    /**
     * @return cost_id
     */
    public Integer getCostId() {
        return costId;
    }

    /**
     * @param costId
     */
    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    /**
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return money
     */
    public Float getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Float money) {
        this.money = money;
    }

    /**
     * @return owner_id
     */
    public Integer getOwnerId() {
        return ownerId;
    }

    /**
     * @param ownerId
     */
    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
}