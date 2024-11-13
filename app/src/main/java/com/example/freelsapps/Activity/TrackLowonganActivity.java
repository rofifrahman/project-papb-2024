package com.example.freelsapps.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelsfragment.Adapter.ListLowonganAdapter;
import com.example.freelsfragment.ListLowongan;
import com.example.freelsfragment.Model.GetLowongan;
import com.example.freelsfragment.R;
import com.example.freelsfragment.Rest.ApiClient;
import com.example.freelsfragment.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackLowonganActivity extends AppCompatActivity {

    private CardView cvPelamar;
    private RecyclerView rvListLamaran;
    private ListLowonganAdapter listLamaranAdapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_lowongan);

        this.cvPelamar = findViewById(R.id.cvPelamar);
        this.rvListLamaran = findViewById(R.id.rvListLamaran);

        rvListLamaran.setLayoutManager(new LinearLayoutManager(this));
        listLamaranAdapter = new ListLowonganAdapter(this);
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
                        Toast.makeText(TrackLowonganActivity.this, "Data pelamar kosong" + response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(TrackLowonganActivity.this, "Gagal mengambil data " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLowongan> call, Throwable t) {
                Toast.makeText(TrackLowonganActivity.this, "Gagal memuat data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}