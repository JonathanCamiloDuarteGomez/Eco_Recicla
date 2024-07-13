package com.example.eco_recicla;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VersionAdapter extends RecyclerView.Adapter<VersionAdapter.VersionViewHolder> {

    private final List<Version> versionList;
    protected static String nFactura = "";



    public VersionAdapter(List<Version> versionList) {
        this.versionList = versionList;
    }

    @NonNull
    @Override
    public VersionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new VersionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VersionViewHolder holder, int position) {
        Version version = versionList.get(position);
        holder.bind(version);
    }

    @Override
    public int getItemCount() {
        return versionList.size();
    }

    public class VersionViewHolder extends RecyclerView.ViewHolder {
        private final TextView bill_number;
        private final TextView user_name;
        private final TextView address;
        private final TextView buyer_name;
        private final TextView vehicle_plate;
        private final LinearLayout linearLayout;
        private final RelativeLayout expandableLayout;
        private final Button detail_button;

        public VersionViewHolder(@NonNull View itemView) {
            super(itemView);
            bill_number = itemView.findViewById(R.id.bill_number);
            user_name = itemView.findViewById(R.id.user_name);
            address = itemView.findViewById(R.id.address);
            buyer_name = itemView.findViewById(R.id.buyer_name);
            vehicle_plate = itemView.findViewById(R.id.vehicle_plate);
            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandableLayout = itemView.findViewById(R.id.expandable_layout);
            detail_button = itemView.findViewById(R.id.detail_button);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Version version = versionList.get(getAdapterPosition());
                    version.setExpanded(!version.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });

            // Agregar el listener para el botón
            detail_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetalleFactura.class);
                    context.startActivity(intent);
                    nFactura =  bill_number.getText().toString()  ;
                }
            });
        }

        public void bind(Version version) {
            bill_number.setText(version.getBill_number());
            user_name.setText(version.getUserName());
            address.setText(version.getAddress());
            buyer_name.setText(version.getBuyer_name());
            vehicle_plate.setText(version.getVehicle_plate());
            expandableLayout.setVisibility(version.isExpanded() ? View.VISIBLE : View.GONE);
        }
    }
}
