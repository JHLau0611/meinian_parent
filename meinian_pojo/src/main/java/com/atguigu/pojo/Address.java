package com.atguigu.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author JHLau
 * @create 2021-09-01 19:58
 */
public class Address implements Serializable {
    private Integer id;
    private String addressName;
    private BigDecimal lng;
    private BigDecimal lat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public BigDecimal getLng() {
        return lng;
    }

    public void setLng(BigDecimal lng) {
        this.lng = lng;
    }

    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }
}
