package com.prm392.restapi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.prm392.restapi.R;
import com.prm392.restapi.activity.MainActivity;
import com.prm392.restapi.model.Trainee;

import java.util.List;

public class TraineeListViewAdapter extends BaseAdapter {

    private final MainActivity context;
    private List<Trainee> traineeList;
    private final int rowTraineeLayout;

    public TraineeListViewAdapter(MainActivity context, List<Trainee> traineeList, int rowTraineeLayout) {
        this.context = context;
        this.traineeList = traineeList;
        this.rowTraineeLayout = rowTraineeLayout;
    }

    @Override
    public int getCount() {
        return traineeList.size();
    }

    @Override
    public Object getItem(int position) {
        return traineeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return traineeList.get(position).getId();
    }

    public void setTraineeList(List<Trainee> traineeList) {
        this.traineeList = traineeList;
    }

    private static class ViewHolder {
        TextView tvName;
        TextView tvEmail;
        TextView tvPhone;
        TextView tvGender;
        ImageView icTrash;
        ImageView icEdit;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(rowTraineeLayout, null);
            holder.tvName = view.findViewById(R.id.tvName);
            holder.tvEmail = view.findViewById(R.id.tvEmail);
            holder.tvPhone = view.findViewById(R.id.tvPhone);
            holder.tvGender = view.findViewById(R.id.tvGender);
            holder.icTrash = view.findViewById(R.id.icTrash);
            holder.icEdit = view.findViewById(R.id.icEdit);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Trainee trainee = traineeList.get(position);
        holder.tvName.setText(trainee.getName());
        holder.tvEmail.setText(trainee.getEmail());
        holder.tvPhone.setText(trainee.getPhone());
        holder.tvGender.setText(trainee.getGender());

        holder.icTrash.setOnClickListener(v -> context.deleteTrainee(trainee));
        holder.icEdit.setOnClickListener(v -> context.showDialog(MainActivity.DialogType.UPDATE, trainee));
        return view;
    }
}
