package com.tsl.pojo;

import javax.persistence.*;

@Table(name = "t_parking")
public class Parking {
    @Id
    @Column(name = "parking_id")
    private Integer parkingId;

    private String number;

    private String state;

    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * @return parking_id
     */
    public Integer getParkingId() {
        return parkingId;
    }

    /**
     * @param parkingId
     */
    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    /**
     * @return number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(String number) {
        this.number = number;
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