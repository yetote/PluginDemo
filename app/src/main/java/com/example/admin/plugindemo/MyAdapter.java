package com.example.admin.plugindemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * adapter
 *
 * @author Swg
 * @date 2017/11/21 16:26
 */
public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<String> list;
    private static final String TAG = "MyAdapter";
    public MyAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.item_tv);
        }

        public TextView getTv() {
            return tv;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: "+parent );
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my,null,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder vh= (MyViewHolder) holder;
        vh.getTv().setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
