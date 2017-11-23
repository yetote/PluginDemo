package adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.plugone.R;

import java.util.ArrayList;

/**
 * adapter
 *
 * @author Swg
 * @date 2017/11/21 16:26
 */
public class MyAdapter extends RecyclerView.Adapter {
    private Context context;
    private ArrayList<Uri> list;

    public MyAdapter(Context context, ArrayList<Uri> list) {
        this.context = context;
        this.list = list;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.item_tv);
        }

        public ImageView getIv() {
            return iv;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_my,null,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder , int position) {
        MyViewHolder vh= (MyViewHolder) holder;
        Glide.with(context).load(list.get(position)).into(vh.getIv());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
