package com.example.freelsapps.Fragment;

import static com.example.freelsapps.Fragment.TambahLowonganFragment.FirebaseURL;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.freelsapps.Adapter.Spinner.GajiSpinnerAdapter;
import com.example.freelsapps.Adapter.Spinner.JenisPekerjaanSpinnerAdapter;
import com.example.freelsapps.Firebase.LowonganFirebase;
import com.example.freelsapps.Firebase.LowonganFirebaseAdapter;
import com.example.freelsapps.R;
import com.example.freelsapps.Rest.ApiInterface;
import com.example.freelsapps.SqliteRoom.LogoPerusahaanDAO;
import com.example.freelsapps.SqliteRoom.LowonganDatabase;
import com.example.freelsapps.SqliteRoom.LowonganLogoPerusahaan;
import com.example.freelsapps.SqliteRoom.LowonganRoom;
import com.example.freelsapps.SqliteRoom.LowonganRoomAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomePageFragment extends Fragment {

    private Spinner spFilterLowonganJenisPekerjaan;
    private Spinner spFilterLowonganGaji;
    private SearchView svSearchLowongan;
    private RecyclerView rvListLowongan;
    private LowonganFirebaseAdapter listLowonganAdapter;
    private ApiInterface apiInterface;
    private LowonganDatabase db;
    private LogoPerusahaanDAO logoPerusahaanDAO;
    private List<LowonganFirebase> dataset = new ArrayList<>();
    private List<LowonganLogoPerusahaan> datasetLogo = new ArrayList<>();
    private FirebaseDatabase firebaseDB;
    private DatabaseReference appDB;

    public HomePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        this.spFilterLowonganJenisPekerjaan = view.findViewById(R.id.spFilterLowonganJenisPekerjaan);
        this.spFilterLowonganGaji = view.findViewById(R.id.spFilterLowonganGaji);
        this.svSearchLowongan = view.findViewById(R.id.svSearchLowongan);
        this.rvListLowongan = view.findViewById(R.id.rvListLowongan);

        svSearchLowongan.setIconified(false);

        new JenisPekerjaanSpinnerAdapter(getContext(), spFilterLowonganJenisPekerjaan);
        new GajiSpinnerAdapter(getContext(), spFilterLowonganGaji);

        rvListLowongan.setLayoutManager(new LinearLayoutManager(getContext()));
        listLowonganAdapter = new LowonganFirebaseAdapter(getContext(), dataset, datasetLogo);
        rvListLowongan.setAdapter(listLowonganAdapter);


        this.db = Room.databaseBuilder(
                getContext().getApplicationContext(),
                LowonganDatabase.class,
                "logo-db"
        ).build();
        this.logoPerusahaanDAO = this.db.logoPerusahaanDAO();

        this.firebaseDB = FirebaseDatabase.getInstance(FirebaseURL);
        this.appDB = this.firebaseDB.getReference("lowongan");
        this.listLowonganAdapter.setAppDb(this.appDB);

        this.appDB.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dataset.clear();
                        for (DataSnapshot s : snapshot.getChildren()){
                            LowonganFirebase lowongan = s.getValue(LowonganFirebase.class);
                            dataset.add(lowongan);
                        }
                        listLowonganAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            try {
                List<LowonganLogoPerusahaan> lowongans = logoPerusahaanDAO.getAllLogoPerusahaan();
                datasetLogo.clear();
                datasetLogo.addAll(lowongans);

                requireActivity().runOnUiThread(() -> {
                    listLowonganAdapter.notifyDataSetChanged();
                    if (datasetLogo.isEmpty()) {
                        Toast.makeText(getContext(), "Tidak ada data lowongan", Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Log.e("DatabaseError", "Error fetching data: " + e.getMessage());
                requireActivity().runOnUiThread(() ->
                        Toast.makeText(getContext(), "Gagal memuat data", Toast.LENGTH_SHORT).show()
                );
            }
        });


//        apiInterface = ApiClient.getClient().create(ApiInterface.class);
//
//        apiInterface.getLowongan().enqueue(new Callback<GetLowongan>(){
//            public void onResponse(Call<GetLowongan> call, Response<GetLowongan> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    List<ListLowongan> lowonganData = response.body().getListDataLowongan();
//                    if (lowonganData != null && !lowonganData.isEmpty()) {
//                        listLowonganAdapter.setLowongans(lowonganData);
//                    } else {
//                        Log.d("API Response", "Data pelamar kosong: " + response.body().toString());
//                        Toast.makeText(getContext(), "Data pelamar kosong" + response.message(), Toast.LENGTH_LONG).show();
//                    }
//                } else {
//                    Toast.makeText(getContext(), "Gagal mengambil data " + response.message(), Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<GetLowongan> call, Throwable t) {
//                Toast.makeText(getContext(), "Gagal memuat data: " + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
}