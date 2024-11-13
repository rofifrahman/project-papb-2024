package com.example.freelsapps.Adapter.Spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class LokasiSpinnerAdapter {
    private Context context;
    private Spinner spinner;
    private ArrayList<String> listLokasi = new ArrayList<>();

    public LokasiSpinnerAdapter(Context context, Spinner spinner) {
        this.context = context;
        this.spinner = spinner;

        listLokasi.add("Lokasi");
        listLokasi.add("Aceh");
        listLokasi.add("Sumatera Utara");
        listLokasi.add("Sumatera Barat");
        listLokasi.add("Riau");
        listLokasi.add("Jambi");
        listLokasi.add("Sumatera Selatan");
        listLokasi.add("Bengkulu");
        listLokasi.add("Lampung");
        listLokasi.add("Kepulauan Bangka Belitung");
        listLokasi.add("Kepulauan Riau");
        listLokasi.add("DKI Jakarta");
        listLokasi.add("Jawa Barat");
        listLokasi.add("Jawa Tengah");
        listLokasi.add("DI Yogyakarta");
        listLokasi.add("Jawa Timur");
        listLokasi.add("Banten");
        listLokasi.add("Bali");
        listLokasi.add("Nusa Tenggara Barat");
        listLokasi.add("Nusa Tenggara Timur");
        listLokasi.add("Kalimantan Barat");
        listLokasi.add("Kalimantan Tengah");
        listLokasi.add("Kalimantan Selatan");
        listLokasi.add("Kalimantan Timur");
        listLokasi.add("Kalimantan Utara");
        listLokasi.add("Sulawesi Utara");
        listLokasi.add("Sulawesi Tengah");
        listLokasi.add("Sulawesi Selatan");
        listLokasi.add("Sulawesi Tenggara");
        listLokasi.add("Gorontalo");
        listLokasi.add("Sulawesi Barat");
        listLokasi.add("Maluku");
        listLokasi.add("Maluku Utara");
        listLokasi.add("Papua Barat");
        listLokasi.add("Papua");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listLokasi);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
