package com.tsl.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_message")
public class Message {
    @Id
    @Column(name = "message_id")
    private Integer messageId;

    private String mname;

    private Date time;

    private String state;

    private String remarks;

    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * @return message_id
     */
    public Integer getMessageId() {
        return messageId;
    }

    /**
     * @param messageId
     */
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    /**
     * @return mname
     */
    public String getMname() {
        return mname;
    }

    /**
     * @param mname
     */
    public void setMname(String mname) {
        this.mname = mname;
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

    /**
     * @return remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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
}