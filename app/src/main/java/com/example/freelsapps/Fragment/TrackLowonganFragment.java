package com.example.freelsapps.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelsapps.Adapter.ListLowonganAdapter;
import com.example.freelsapps.ListLowongan;
import com.example.freelsapps.Model.GetLowongan;
import com.example.freelsapps.R;
import com.example.freelsapps.Rest.ApiClient;
import com.example.freelsapps.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackLowonganFragment extends Fragment {

    private CardView cvPelamar;
    private RecyclerView rvListLamaran;
    private ListLowonganAdapter listLamaranAdapter;
    private ApiInterface apiInterface;

    public TrackLowonganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_track_lowongan, container, false);

        this.cvPelamar = view.findViewById(R.id.cvPelamar);
        this.rvListLamaran = view.findViewById(R.id.rvListLamaran);

        rvListLamaran.setLayoutManager(new LinearLayoutManager(getContext()));
        listLamaranAdapter = new ListLowonganAdapter(getContext());
        rvListLamaran.setAdapter(listLamaranAdapter);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.getLowongan().enqueue(new Callback<GetLowongan>(){
            public void onResponse(Call<GetLowongan> call, Response<GetLowongan> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ListLowongan> lowonganData = response.body().getListDataLowongan();
                    if (lowonganData != null && !lowonganData.isEmpty()) {
                        listLamaranAdapter.setLowongans(lowonganData);
                    } else {
                        Log.d("API Response", "Data pelamar kosong: " + response.body().toString());
                        Toast.makeText(getContext(), "Data pelamar kosong" + response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Gagal mengambil data " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLowongan> call, Throwable t) {
                Toast.makeText(getContext(), "Gagal memuat data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}