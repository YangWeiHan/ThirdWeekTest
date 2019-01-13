package com.example.mryang.thirdweektest.data.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mryang.thirdweektest.R;
import com.example.mryang.thirdweektest.data.bean.NineGongGeBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class NineGongGeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<NineGongGeBean.DataBean> list;

    public NineGongGeAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<NineGongGeBean.DataBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context,R.layout.hread_itme,null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        holder.iv_text.setText(list.get(i).getName());
        Glide.with(context).load(list.get(i).getIcon()).into(holder.iv_icon);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_icon;
        TextView iv_text;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_icon = itemView.findViewById(R.id.iv_icon);
            iv_text = itemView.findViewById(R.id.iv_text);
        }
    }
}
