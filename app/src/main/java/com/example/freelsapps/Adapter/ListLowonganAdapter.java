package com.example.freelsapps.Adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
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

import com.bumptech.glide.Glide;
import com.example.freelsapps.ListLowongan;
import com.example.freelsapps.R;
import com.example.freelsapps.Rest.ApiClient;
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
        private ListLowongan lowongan;

        public ListLowonganVH(@NonNull View itemView) {
            super(itemView);
            ivLogoPerusahaan = itemView.findViewById(R.id.ivListLogoPerusahaan);
            tvPekerjaan = itemView.findViewById(R.id.tvListPekerjaan);
            tvNamaPerusahaan = itemView.findViewById(R.id.tvListNamaPerusahaan);
            tvLokasi = itemView.findViewById(R.id.tvListLokasi);
            tvRingkasanPekerjaan = itemView.findViewById(R.id.tvListRingkasanPekerjaan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, lowongan.getPekerjaan(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void bind(ListLowongan listLowongan) {
            tvPekerjaan.setText(listLowongan.getPekerjaan());
            tvNamaPerusahaan.setText(listLowongan.getNamaPerusahaan());
            tvLokasi.setText(listLowongan.getLokasi());
            String ringkasan = listLowongan.getRingkasanPekerjaan();
            if (ringkasan.length() > 155) {
                String truncatedText = ringkasan.substring(0, 155);
                String ellipsis = ". . .lihat selengkapnya";

                SpannableString spannableRingkasan = new SpannableString(truncatedText + ellipsis);
                spannableRingkasan.setSpan(
                        new ForegroundColorSpan(context.getResources().getColor(R.color.blue)),
                        truncatedText.length(),
                        truncatedText.length() + ellipsis.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                );
                tvRingkasanPekerjaan.setText(spannableRingkasan);
            } else {
                tvRingkasanPekerjaan.setText(ringkasan);
            }
            if (listLowongan.getLogoPerusahaan() != null && !listLowongan.getLogoPerusahaan().isEmpty()) {
                Glide.with(context)
                        .load(ApiClient.BASE_URL+"/logoPerusahaan/"+listLowongan.getLogoPerusahaan())
                        .into(ivLogoPerusahaan);
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
        ListLowonganVH vh = (ListLowonganVH) holder;
        vh.bind(dataListLowonganFilter.get(position));
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
