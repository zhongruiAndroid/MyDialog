package com.test.mydialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/14.
 */

public class ListAdapter extends Adapter<ListAdapter.ViewHolder> {
    List<String> list=new ArrayList<>();
    Context mContext;
    int layoutResid;
    public ListAdapter(Context context,int id) {
        layoutResid=id;
        mContext=context;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitem, parent,false);
        ViewHolder holder=new ViewHolder(view);
        ViewGroup itemView = (ViewGroup) holder.itemView;

        View childerView = LayoutInflater.from(mContext).inflate(R.layout.listitem, null);
        itemView.addView(childerView);
        return holder;
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv_item.setText(position+"listitem");
    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_item;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
