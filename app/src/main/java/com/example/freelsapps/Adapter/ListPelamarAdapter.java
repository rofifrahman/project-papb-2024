package com.example.freelsapps.Adapter;

import android.content.Context;
import android.graphics.Color;
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

import com.example.freelsapps.R;
import com.example.freelsapps.Pelamar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListPelamarAdapter extends RecyclerView.Adapter implements Filterable {
    private final Context context;
    private List<com.example.freelsapps.Pelamar> dataListPelamar;
    private List<com.example.freelsapps.Pelamar> dataListPelamarFilter;
    public com.example.freelsapps.Pelamar listPelamarDelete;

    public ListPelamarAdapter(Context context) {
        this.context = context;
        this.dataListPelamar = new ArrayList<>();
        this.dataListPelamarFilter = new ArrayList<>(dataListPelamar);
    }

    public void setPelamar(List<com.example.freelsapps.Pelamar> pelamar) {
        this.dataListPelamar = pelamar;
        this.dataListPelamarFilter = new ArrayList<>(pelamar);
        notifyDataSetChanged();
    }

    public class ListPelamarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final TextView tvListNamaPelamar;
        private final TextView tvPendidikan;
        private final ImageView ivProfilePicture;
        private final ImageView ivDelete;
        private CardView cvListPelamar;
        private com.example.freelsapps.Pelamar listPelamar;

        public ListPelamarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListNamaPelamar = itemView.findViewById(R.id.tvListNamaPelamar);
            tvPendidikan = itemView.findViewById(R.id.tvPendidikan);
            ivProfilePicture = itemView.findViewById(R.id.ivProfilePicture);
            ivDelete = itemView.findViewById(R.id.ivDelete);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    com.example.freelsapps.Pelamar listPelamarCalonDelete = listPelamarDelete;
                    if (listPelamarCalonDelete != null) {
                        Toast.makeText(context, listPelamarCalonDelete.nama + " telah dihapus", Toast.LENGTH_SHORT).show();
                        dataListPelamar.remove(listPelamarCalonDelete);
                        dataListPelamarFilter.remove(listPelamarCalonDelete);
                        notifyDataSetChanged();
                    }
                }
            });
            cvListPelamar = itemView.findViewById(R.id.cvListPelamar);
            cvListPelamar.setOnClickListener(this);
        }

        private void setPelamarDIpilih(com.example.freelsapps.Pelamar lp) {
            this.listPelamar = lp;
            if (this.listPelamar.terpilih) {
                Toast.makeText(context, listPelamar.nama + " telah dipilih", Toast.LENGTH_SHORT).show();
                this.cvListPelamar.setCardBackgroundColor(Color.RED);
            } else {
                this.cvListPelamar.setCardBackgroundColor(Color.WHITE);
            }
        }

        @Override
        public void onClick(View view) {
            for (com.example.freelsapps.Pelamar lp : dataListPelamar) {
                lp.terpilih = false;
            }

            this.listPelamar.terpilih = !this.listPelamar.terpilih;
            listPelamarDelete = this.listPelamar.terpilih ? this.listPelamar : null;
            notifyDataSetChanged();
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View dataListPelamarView = LayoutInflater.from(context).inflate(R.layout.list_pelamar, parent, false);
        return new ListPelamarViewHolder(dataListPelamarView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        com.example.freelsapps.Pelamar listPelamar = dataListPelamarFilter.get(position);
        ListPelamarViewHolder viewHolder = (ListPelamarViewHolder) holder;

        viewHolder.tvListNamaPelamar.setText(listPelamar.nama);
        viewHolder.ivProfilePicture.setImageResource(listPelamar.imageProfile);
        viewHolder.tvPendidikan.setText(listPelamar.pendidikan);
        viewHolder.ivDelete.setVisibility(listPelamar.terpilih ? View.VISIBLE : View.GONE);
        viewHolder.setPelamarDIpilih(listPelamar);
    }

    @Override
    public int getItemCount() {
        return this.dataListPelamarFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults hasilFilter = new FilterResults();
                List<com.example.freelsapps.Pelamar> listPelamarFilter = new ArrayList<>();
                if (constraint.equals("Pendidikan Terakhir")) {
                    listPelamarFilter.addAll(dataListPelamar);
                } else {
                    for (com.example.freelsapps.Pelamar lp : dataListPelamar) {
                        if (lp.pendidikan.toLowerCase().contains(constraint.toString().toLowerCase())) {
                            listPelamarFilter.add(lp);
                        }
                    }
                }
                hasilFilter.values = listPelamarFilter;
                hasilFilter.count = listPelamarFilter.size();
                return hasilFilter;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                dataListPelamarFilter.clear();
                dataListPelamarFilter.addAll((ArrayList<com.example.freelsapps.Pelamar>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }
}
