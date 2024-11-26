package com.example.freelsapps.Firebase;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.freelsapps.R;
import com.example.freelsapps.SqliteRoom.LowonganDatabase;
import com.example.freelsapps.SqliteRoom.LowonganLogoPerusahaan;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class LowonganFirebaseAdapter extends RecyclerView.Adapter {
    private Context ctx;
    private List<LowonganFirebase> dataset;
    private List<LowonganLogoPerusahaan> datasetLogo;
    private DatabaseReference appDb;

    public LowonganFirebaseAdapter(Context ctx, List<LowonganFirebase> dataset, List<LowonganLogoPerusahaan> datasetLogo){
        this.ctx = ctx;
        this.dataset = dataset;
        this.datasetLogo = datasetLogo;
    }

    public void setAppDb(DatabaseReference appDb) {
        this.appDb = appDb;
    }

    private class VH extends RecyclerView.ViewHolder {
        private ImageView ivLogoPerusahaan;
        private TextView tvPekerjaan;
        private TextView tvNamaPerusahaan;
        private TextView tvLokasi;
        private TextView tvRingkasanPekerjaan;
        private ImageView ivDeleteLowongan;
        private LowonganFirebase lowongan;

        public VH(@NonNull View itemView) {
            super(itemView);
            ivLogoPerusahaan = itemView.findViewById(R.id.ivListLogoPerusahaan);
            tvPekerjaan = itemView.findViewById(R.id.tvListPekerjaan);
            tvNamaPerusahaan = itemView.findViewById(R.id.tvListNamaPerusahaan);
            tvLokasi = itemView.findViewById(R.id.tvListLokasi);
            tvRingkasanPekerjaan = itemView.findViewById(R.id.tvListRingkasanPekerjaan);
            ivDeleteLowongan = itemView.findViewById(R.id.ivDeleteLowongan);

            ivDeleteLowongan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appDb.child(lowongan.getId()).removeValue();

                    int position = getAdapterPosition();
                    LowonganLogoPerusahaan lowonganToDelete = datasetLogo.get(position);
                    new Thread(() -> {
                        LowonganDatabase db = Room.databaseBuilder(ctx.getApplicationContext(),
                                LowonganDatabase.class, "logo-db").build();
                        db.logoPerusahaanDAO().delete(lowonganToDelete);

                        datasetLogo.remove(position);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, datasetLogo.size());
                        });
                    }).start();
                    Toast.makeText(ctx, "Lowongan berhasil dihapus", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bind(LowonganFirebase lowongan, LowonganLogoPerusahaan logoPerusahaan){
            this.lowongan = lowongan;
            this.tvNamaPerusahaan.setText(lowongan.getNamaPerusahaan());
            this.tvPekerjaan.setText(lowongan.getPekerjaan());
            this.tvLokasi.setText(lowongan.getLokasi());
            this.tvRingkasanPekerjaan.setText(lowongan.getRingkasanPekerjaan());
            if (lowongan != null && logoPerusahaan.logoPerusahaan != null) {
                byte[] imageBytes = logoPerusahaan.logoPerusahaan;
                Bitmap bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
                ivLogoPerusahaan.setImageBitmap(bitmap);
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.list_card_view_lowongan_pekerjaan, parent, false);
        VH vh = new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH) holder;
        vh.bind(dataset.get(position), datasetLogo.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
