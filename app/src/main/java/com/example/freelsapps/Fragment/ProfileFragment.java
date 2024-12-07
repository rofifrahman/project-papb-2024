package com.example.freelsapps.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.freelsapps.Adapter.Spinner.LokasiSpinnerAdapter;
import com.example.freelsapps.Adapter.Spinner.PendidikanSpinnerAdapter;
import com.example.freelsapps.R;

public class ProfileFragment extends Fragment {
    private Spinner spPendidikan;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        spPendidikan = view.findViewById(R.id.spPendidikanProfile);
        new PendidikanSpinnerAdapter(getContext(), spPendidikan);

        return view;
    }
}