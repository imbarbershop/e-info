package com.example.e_info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterData extends RecyclerView.Adapter<AdapterData.HolderData>{
    private Context ctx;
    private List<DataModel> listData;

    public AdapterData(Context ctx, List<DataModel> listData) {
        this.ctx = ctx;
        this.listData = listData;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = listData.get(position);

        holder.tvId.setText(String.valueOf(dm.getId_tugas()));
        holder.tvMk.setText(dm.getMk());
        holder.tvJudul.setText(dm.getJudul());
        holder.tvDeskripsi.setText(dm.getDeskripsi());
        holder.tvMasuk.setText(dm.getMasuk());
        holder.tvDeadline.setText(dm.getDeadline());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class HolderData extends RecyclerView.ViewHolder {
        TextView tvId, tvMk, tvJudul, tvDeskripsi, tvMasuk, tvDeadline;

        public HolderData(@NonNull View itemView) {
            super(itemView);

            tvId =(TextView) itemView.findViewById(R.id.id_tugas);
            tvMk = (TextView) itemView.findViewById(R.id.mk_title);
            tvJudul = (TextView) itemView.findViewById(R.id.judul_tugas);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.deskripsi);
            tvMasuk = (TextView) itemView.findViewById(R.id.masuk);
            tvDeadline = (TextView) itemView.findViewById(R.id.deadline);

        }
    }
}
