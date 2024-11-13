package com.example.freelsapps.Model;

import com.google.gson.annotations.SerializedName;

public class CUDlowongan {
    @SerializedName("status")
    String status;

    @SerializedName("id")
    int id;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
