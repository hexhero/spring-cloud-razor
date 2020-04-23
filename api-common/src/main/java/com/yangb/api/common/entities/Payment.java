package com.yangb.api.common.entities;

import javax.persistence.*;

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serial;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }
}