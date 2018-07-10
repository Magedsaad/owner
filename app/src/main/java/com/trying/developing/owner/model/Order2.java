package com.trying.developing.owner.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by developing on 7/10/2018.
 */
@IgnoreExtraProperties
public class Order2  {

    public Double  lat;
    public Double lng;
    public String details;
    public String address;
    public String status;

    public Order2(Double lat, Double lng, String details, String address, String status) {
        this.lat = lat;
        this.lng = lng;
        this.details = details;
        this.address = address;
        this.status = status;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
