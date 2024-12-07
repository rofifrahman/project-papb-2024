package com.example.freelsapps.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.freelsapps.Adapter.ListPelamarAdapter;
import com.example.freelsapps.Adapter.Spinner.PendidikanSpinnerAdapter;
import com.example.freelsapps.Pelamar;
import com.example.freelsapps.R;

import java.util.ArrayList;
import java.util.List;

public class TrackPelamarFragment extends Fragment {
    private CardView cvLamaran;
    private RecyclerView rvListPelamar;
    private ListPelamarAdapter listPelamarAdapter;
    int [] profilePicture = {R.drawable.profile_picture_1, R.drawable.profile_picture_2, R.drawable.profile_picture_3, R.drawable.profile_picture_4, R.drawable.profile_picture_5, R.drawable.profile_picture_6, R.drawable.profile_picture_8};
    private Spinner spFilterPendidikan;

    public TrackPelamarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_track_pelamar, container, false);

        this.cvLamaran = view.findViewById(R.id.cvLamaran);
        this.rvListPelamar = view.findViewById(R.id.rvListPelamar);
        this.spFilterPendidikan = view.findViewById(R.id.spFilterPendidikan);

        List<Pelamar> dataListPelamar = new ArrayList<>();
        dataListPelamar.add(new Pelamar("John Doe", profilePicture[0], "Sekolah Dasar"));
        dataListPelamar.add(new Pelamar("Jane Smith", profilePicture[1], "Sekolah Menengah Pertama"));
        dataListPelamar.add(new Pelamar("Bob Johnson", profilePicture[2], "Sekolah Menengah Atas"));
        dataListPelamar.add(new Pelamar("Alice Brown", profilePicture[3], "Diploma"));
        dataListPelamar.add(new Pelamar("Tom Wilson", profilePicture[4], "Sarjana"));
        dataListPelamar.add(new Pelamar("Emily Davis", profilePicture[5], "Magister"));
        dataListPelamar.add(new Pelamar("David Lee", profilePicture[6], "Doktor"));
        dataListPelamar.add(new Pelamar("Sarah Miller", profilePicture[0], "Sekolah Dasar"));
        dataListPelamar.add(new Pelamar("Mike Clark", profilePicture[1], "Sekolah Menengah Pertama"));
        dataListPelamar.add(new Pelamar("Jessica Taylor", profilePicture[2], "Sekolah Menengah Atas"));

        new PendidikanSpinnerAdapter(getContext(), spFilterPendidikan);

        listPelamarAdapter = new ListPelamarAdapter(getContext());
        rvListPelamar.setAdapter(listPelamarAdapter);
        rvListPelamar.setLayoutManager(new LinearLayoutManager(getContext()));

        spFilterPendidikan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = adapterView.getItemAtPosition(i).toString();
                listPelamarAdapter.getFilter().filter(text);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        listPelamarAdapter.setPelamar(dataListPelamar);
        listPelamarAdapter.notifyDataSetChanged();

        cvLamaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TrackLowonganFragment trackLowongan = new TrackLowonganFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.frameLayout, trackLowongan);
                transaction.commit();
            }
        });

        return view;
    }
}