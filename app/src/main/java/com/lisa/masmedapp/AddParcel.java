package com.lisa.masmedapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AddParcel {

    String receipient;
    String receipientId;
    String trackingNumber;
    String phoneNumber;
    String parcelDate;
    String courier;

    public AddParcel() {

    }

    public AddParcel(String receipientId, String receipient, String trackingNumber, String phoneNumber, String parcelDate, String courier ) {
        this.receipientId = receipientId;
        this.receipient = receipient;
        this.trackingNumber = trackingNumber;
        this.phoneNumber = phoneNumber;
        this.parcelDate = parcelDate;
        this.courier = courier;
    }

    public String getReceipientId() {
        return receipientId;
    }

    public String getReceipient() {
        return receipient;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getParcelDate() {
        return parcelDate;
    }

    public String getCourier() {
        return courier;
    }

    public void setReceipient(String receipient) {
        this.receipient = receipient;
    }

    public void setReceipientId(String receipientId) {
        this.receipientId = receipientId;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setParcelDate(String parcelDate) {
        this.parcelDate = parcelDate;
    }

    public void setCourier(String courier) {
        this.courier = courier;
    }
}
