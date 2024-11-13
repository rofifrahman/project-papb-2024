package com.example.freelsapps.Adapter.Spinner;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class GajiSpinnerAdapter {
    private Context context;
    private Spinner spinner;
    private ArrayList<String> listGaji = new ArrayList<>();

    public GajiSpinnerAdapter(Context context, Spinner spinner) {
        this.context = context;
        this.spinner = spinner;

        listGaji.add("Gaji");
        listGaji.add(">Rp. 1.000.000");
        listGaji.add(">Rp. 2.000.000");
        listGaji.add(">Rp. 3.000.000");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listGaji);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
