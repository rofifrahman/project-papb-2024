package com.example.freelsapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freelsfragment.ListLowongan;
import com.example.freelsfragment.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListLowonganAdapter extends RecyclerView.Adapter implements Filterable {
    private final Context context;
    private List<ListLowongan> dataListLowongan;
    private List<ListLowongan> dataListLowonganFilter;
    public ListLowongan listlowonganPilih;

    public ListLowonganAdapter(Context context) {
        this.context = context;
        this.dataListLowongan = new ArrayList<>();
        this.dataListLowonganFilter = new ArrayList<>(dataListLowongan);
    }

    public void setLowongans(List<ListLowongan> lowongans) {
        this.dataListLowongan = lowongans;
        this.dataListLowonganFilter = new ArrayList<>(lowongans);
        notifyDataSetChanged();
    }

    public class ListLowonganVH extends RecyclerView.ViewHolder {
        private final ImageView ivLogoPerusahaan;
        private final TextView tvPekerjaan;
        private final TextView tvNamaPerusahaan;
        private final TextView tvLokasi;
        private final TextView tvRingkasanPekerjaan;
        private CardView cvLowongan;
        private ListLowongan listLowongan;

        public ListLowonganVH(@NonNull View itemView) {
            super(itemView);
            ivLogoPerusahaan = itemView.findViewById(R.id.ivListLogoPerusahaan);
            tvPekerjaan = itemView.findViewById(R.id.tvListPekerjaan);
            tvNamaPerusahaan = itemView.findViewById(R.id.tvListNamaPerusahaan);
            tvLokasi = itemView.findViewById(R.id.tvListLokasi);
            tvRingkasanPekerjaan = itemView.findViewById(R.id.tvListRingkasanPekerjaan);
            cvLowongan = itemView.findViewById(R.id.cvListLowongan);
            cvLowongan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    for (ListLowongan lw : dataListLowongan) {
                        lw.lowonganTepilih = false;
                    }

                    listLowongan.lowonganTepilih = !listLowongan.lowonganTepilih;
                    listlowonganPilih = listLowongan.lowonganTepilih ? listLowongan : null;
                    notifyDataSetChanged();
                }
            });
        }

        private void setLowongan(ListLowongan lw) {
            this.listLowongan = lw;
            if (this.listLowongan.lowonganTepilih) {
                Toast.makeText(context, listLowongan.getPekerjaan() + " telah dipilih 11", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataListLowongan = LayoutInflater.from(context).inflate(R.layout.list_card_view_lowongan_pekerjaan, parent, false);
        return new ListLowonganVH(dataListLowongan);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListLowongan listLowongan = dataListLowonganFilter.get(position);
        ListLowonganVH listLowonganVH = (ListLowonganVH) holder;

        listLowonganVH.tvPekerjaan.setText(listLowongan.getPekerjaan());
        listLowonganVH.tvNamaPerusahaan.setText(listLowongan.getNamaPerusahaan());
        listLowonganVH.tvLokasi.setText(listLowongan.getLokasi());
        listLowonganVH.tvRingkasanPekerjaan.setText(listLowongan.getRingkasanPekerjaan());
        if (listLowongan.getLogoPerusahaan() != null && !listLowongan.getLogoPerusahaan().isEmpty()) {
//            Glide.with(context)
//                    .load(ApiClient.BASE_URL+listLowongan.getLogoPerusahaan())
//                    .into(listLowonganVH.ivLogoPerusahaan);
////            Glide.with(listLowonganVH.ivLogoPerusahaan.getContext())
////                    .load(response.body().byteStream())
////                    .into(listLowonganVH.ivLogoPerusahaan);
            Picasso.get().load(listLowongan.getLogoPerusahaan())
                    .placeholder(R.drawable.logo_perusahaan_ruangguru)
                    .error(R.drawable.logo_perusahaan_ruangguru)
                    .into(((ListLowonganVH) holder).ivLogoPerusahaan);
        }
        listLowonganVH.setLowongan(listLowongan);
    }

    @Override
    public int getItemCount() {
        return dataListLowonganFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            }
        };
    }
}
