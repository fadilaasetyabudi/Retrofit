package com.example.baju.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSepatu {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Sepatu> listDataSepatu;
    @SerializedName("message")
    String message;
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public List<Sepatu> getListDataSepatu() {
        return listDataSepatu;
    }
    public void setListDataSepatu(List<Sepatu> listDataSepatu) {
        this.listDataSepatu = listDataSepatu;
    }
}

