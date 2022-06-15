package com.prm392.lab5_ex2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.prm392.lab5_ex2.R;
import com.prm392.lab5_ex2.model.Phone;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder> {
    private List<Phone> phones;

    public PhoneAdapter(List<Phone> phones) {
        this.phones = phones;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View userView = inflater.inflate(R.layout.row_phone, parent, false);
        ViewHolder viewHolder = new ViewHolder(userView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Phone phone = phones.get(position);
        holder.imagePhone.setImageResource(phone.getImage());
        holder.tvPhoneName.setText(phone.getName());
        holder.tvBrandValue.setText(phone.getBrand());
        holder.tvYearValue.setText(String.valueOf(phone.getReleaseYear()));
        holder.tvPrice.setText("$" + phone.getPrice());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imagePhone;
        TextView tvPhoneName;
        TextView tvBrandValue;
        TextView tvYearValue;
        TextView tvPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imagePhone = itemView.findViewById(R.id.imagePhone);
            tvPhoneName = itemView.findViewById(R.id.tvPhoneName);
            tvBrandValue = itemView.findViewById(R.id.tvBrandValue);
            tvYearValue = itemView.findViewById(R.id.tvYearValue);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
