package com.example.freelsapps.Model;

import com.example.freelsapps.ListLowongan;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLowongan {
    @SerializedName("status")
    String status;

    @SerializedName("data_lowongan")
    List<ListLowongan> listDataLowongan;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ListLowongan> getListDataLowongan() {
        return listDataLowongan;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
