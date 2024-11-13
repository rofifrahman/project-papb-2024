package com.example.freelsapps.Adapter.Spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class JenisPekerjaanSpinnerAdapter {
    private Context context;
    private Spinner spinner;
    private ArrayList<String> listJenisPekerjaan = new ArrayList<>();

    public JenisPekerjaanSpinnerAdapter(Context context, Spinner spinner) {
        this.context = context;
        this.spinner = spinner;

        listJenisPekerjaan.add("Jenis Pekerjaan");
        listJenisPekerjaan.add("Full Time");
        listJenisPekerjaan.add("Part Time");
        listJenisPekerjaan.add("Internship");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listJenisPekerjaan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
