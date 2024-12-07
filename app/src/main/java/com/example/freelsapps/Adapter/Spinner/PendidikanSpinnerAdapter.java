package com.example.freelsapps.Adapter.Spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class PendidikanSpinnerAdapter {
    private Context context;
    private Spinner spinner;
    private ArrayList<String> listPendidikan = new ArrayList<>();

    public PendidikanSpinnerAdapter(Context context, Spinner spinner) {
        this.context = context;
        this.spinner = spinner;

        listPendidikan.add("Pendidikan Terakhir");
        listPendidikan.add("Sekolah Dasar");
        listPendidikan.add("Sekolah Menengah Pertama");
        listPendidikan.add("Sekolah Menengah Atas");
        listPendidikan.add("Diploma");
        listPendidikan.add("Sarjana");
        listPendidikan.add("Magister");
        listPendidikan.add("Doktor");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listPendidikan);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
