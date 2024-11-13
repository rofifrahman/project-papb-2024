package com.example.freelsapps.Activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelsfragment.Adapter.ListLowonganAdapter;
import com.example.freelsfragment.Adapter.Spinner.GajiSpinnerAdapter;
import com.example.freelsfragment.Adapter.Spinner.JenisPekerjaanSpinnerAdapter;
import com.example.freelsfragment.ListLowongan;
import com.example.freelsfragment.Model.GetLowongan;
import com.example.freelsfragment.R;
import com.example.freelsfragment.Rest.ApiClient;
import com.example.freelsfragment.Rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {

    private Spinner spFilterLowonganJenisPekerjaan;
    private Spinner spFilterLowonganGaji;
    private SearchView svSearchLowongan;
    private RecyclerView rvListLowongan;
    private ListLowonganAdapter listLowonganAdapter;
    private ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        this.spFilterLowonganJenisPekerjaan = findViewById(R.id.spFilterLowonganJenisPekerjaan);
        this.spFilterLowonganGaji = findViewById(R.id.spFilterLowonganGaji);
        this.svSearchLowongan = findViewById(R.id.svSearchLowongan);
        this.rvListLowongan = findViewById(R.id.rvListLowongan);

        svSearchLowongan.setIconified(false);

        new JenisPekerjaanSpinnerAdapter(this, spFilterLowonganJenisPekerjaan);
        new GajiSpinnerAdapter(this, spFilterLowonganGaji);

        rvListLowongan.setLayoutManager(new LinearLayoutManager(this));
        listLowonganAdapter = new ListLowonganAdapter(this);
        rvListLowongan.setAdapter(listLowonganAdapter);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.getLowongan().enqueue(new Callback<GetLowongan>(){
            public void onResponse(Call<GetLowongan> call, Response<GetLowongan> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ListLowongan> lowonganData = response.body().getListDataLowongan();
                    if (lowonganData != null && !lowonganData.isEmpty()) {
                        listLowonganAdapter.setLowongans(lowonganData);
                    } else {
                        Log.d("API Response", "Data pelamar kosong: " + response.body().toString());
                        Toast.makeText(HomePageActivity.this, "Data pelamar kosong" + response.message(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(HomePageActivity.this, "Gagal mengambil data " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetLowongan> call, Throwable t) {
                Toast.makeText(HomePageActivity.this, "Gagal memuat data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}