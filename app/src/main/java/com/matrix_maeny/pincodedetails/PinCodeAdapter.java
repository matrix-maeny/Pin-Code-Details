package com.matrix_maeny.pincodedetails;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PinCodeAdapter extends RecyclerView.Adapter<PinCodeAdapter.viewHolder> {

    private Context context;
    private ArrayList<PinCodeModel> list;
    private final String village = "Village: ";
    private final String state = "State: ";
    private final String circle = "Circle: ";
    public static PinCodeModel dataModel = null;
    public static String pinCode = null;

    public PinCodeAdapter(Context context, ArrayList<PinCodeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picode_view_model, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        PinCodeModel model = list.get(position);

        String s = state+model.getState();
        String c = circle+model.getCircle();
        String vi = village+model.getVillageName();
        holder.villageNameTv.setText(vi);
        holder.stateTv.setText(s);
        holder.circleTv.setText(c);

        holder.cardView.setOnClickListener(v -> {
            dataModel = model;
            context.startActivity(new Intent(context.getApplicationContext(),DetailsActivity.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView villageNameTv, stateTv, circleTv;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            villageNameTv = itemView.findViewById(R.id.villageNameTv);
            stateTv = itemView.findViewById(R.id.stateTv);
            circleTv = itemView.findViewById(R.id.circleTv);
        }
    }
}
