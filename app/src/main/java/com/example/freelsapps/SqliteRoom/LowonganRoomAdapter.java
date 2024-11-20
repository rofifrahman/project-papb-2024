package com.example.freelsapps.SqliteRoom;

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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.freelsapps.ListLowongan;
import com.example.freelsapps.R;

import java.util.ArrayList;
import java.util.List;

public class LowonganRoomAdapter extends RecyclerView.Adapter {
    private Context ctx;
    private List<LowonganRoom> dataset = new ArrayList<>();

    public LowonganRoomAdapter(Context ctx){
        this.ctx = ctx;
    }

    public void setLowongans(List<LowonganRoom> lowongans) {
        this.dataset = lowongans;
        notifyDataSetChanged();
    }

    private class VH extends RecyclerView.ViewHolder {
        private ImageView ivLogoPerusahaan;
        private TextView tvPekerjaan;
        private TextView tvNamaPerusahaan;
        private TextView tvLokasi;
        private TextView tvRingkasanPekerjaan;
        private ImageView ivDeleteLowongan;
        private ListLowongan lowongan;

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
                    int position = getAdapterPosition();
                    LowonganRoom lowonganToDelete = dataset.get(position);
                    new Thread(() -> {
                        LowonganDatabase db = Room.databaseBuilder(ctx.getApplicationContext(),
                                LowonganDatabase.class, "lowongan-db").build();
                        db.lowonganDAO().delete(lowonganToDelete);

                        dataset.remove(position);
                        new Handler(Looper.getMainLooper()).post(() -> {
                            notifyItemRemoved(position);
                            notifyItemRangeChanged(position, dataset.size());
                        });
                    }).start();
                    Toast.makeText(ctx, "Lowongan berhasil dihapus", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bind(LowonganRoom lowongan){
            tvNamaPerusahaan.setText(lowongan.namaPerusahaan);
            tvPekerjaan.setText(lowongan.pekerjaan);
            tvLokasi.setText(lowongan.lokasi);
            tvRingkasanPekerjaan.setText(lowongan.ringkasanPekerjaan);
            if (lowongan != null && lowongan.logoPerusahaan != null) {
                byte[] imageBytes = lowongan.logoPerusahaan;
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
        vh.bind(dataset.get(position));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
