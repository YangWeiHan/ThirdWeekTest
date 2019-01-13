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
import com.example.mryang.thirdweektest.data.bean.ShoppingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class FoorerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private  Context context;
  private List<ShoppingBean.DataBean.ListBean> beanList;

    public FoorerAdapter(Context context) {
        this.context = context;
        beanList = new ArrayList<>();
    }

    public void setBeanList(List<ShoppingBean.DataBean.ListBean> beanList) {
        this.beanList=beanList;
        notifyDataSetChanged();
    }
    public void addBeanList(List<ShoppingBean.DataBean.ListBean> beanList) {
        if (beanList!=null){
            this.beanList.addAll(beanList);
        }
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder = null;
        View view = View.inflate(context,R.layout.footer_item,null);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder holder = (ViewHolder) viewHolder;
        String images = beanList.get(i).getImages();

        Pattern compile = Pattern.compile("\\|");

        String[] split = compile.split(images);

        Glide.with(context).load(split[0]).into(holder.footer_iv_icon);

        holder.footer_iv_name.setText(beanList.get(i).getTitle());
        holder.getFooter_iv_price.setText(beanList.get(i).getPrice()+"");

    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView footer_iv_icon;
        TextView footer_iv_name,getFooter_iv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            footer_iv_icon = itemView.findViewById(R.id.footer_iv_icon);
            getFooter_iv_price = itemView.findViewById(R.id.footer_iv_price);
            footer_iv_name = itemView.findViewById(R.id.footer_iv_name);

        }
    }
}
