package com.example.zuoye.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zuoye.R;
import com.example.zuoye.bean.GoodsBean;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

    public class GoodsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<GoodsBean.DataBean> data;
    private Context context;
    private boolean boo=true;

    public GoodsAdapter(Context context, boolean boo) {
        this.context = context;
        this.boo = boo;
        data = new ArrayList<>();
    }

    public void setData(List<GoodsBean.DataBean> datas){
        data.clear();
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }
    public void addData(List<GoodsBean.DataBean> datas){
        if(datas!=null){
            data.addAll(datas);
        }
        notifyDataSetChanged();
    }


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (boo){
            View view = LayoutInflater.from(context).inflate(R.layout.goods_item_grid,viewGroup,false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }else {
            ViewHolder2 vh = null;
            View view = LayoutInflater.from(context).inflate(R.layout.goods_item_linear,viewGroup,false);
            vh = new ViewHolder2(view);
            return vh;
        }

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

           if (boo){
               ViewHolder holder = (ViewHolder) viewHolder;
               final int position = i;

               holder.product.setText(data.get(i).getTitle());
               holder.price.setText( "￥"+data.get(i).getPrice()+"");
               final List<String> images = new ArrayList<>();
               String images2 = data.get(i).getImages();
               Pattern pattern = Pattern.compile("\\|");
               final String[] img = pattern.split(images2);
               Glide.with(context).load(img[0]).into(holder.imageView);


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       clickCallBack.setOnClick(data.get(position).getTitle(),data.get(position).getPrice());
                   }
               });
           }else {
               ViewHolder2 holder = (ViewHolder2) viewHolder;
               final int position = i;
               holder.product.setText(data.get(i).getTitle());
               holder.price.setText( "￥"+data.get(i).getPrice()+"");
               final List<String> images = new ArrayList<>();
               String images2 = data.get(i).getImages();
               Pattern pattern = Pattern.compile("\\|");
               final String[] img = pattern.split(images2);
               Glide.with(context).load(img[0]).into(holder.imageView);


               holder.itemView.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       clickCallBack.setOnClick(data.get(position).getTitle(),data.get(position).getPrice());
                   }
               });
           }

        }


        @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView product;
        TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_image);
            product = itemView.findViewById(R.id.item_product);
            price = itemView.findViewById(R.id.item_price);
        }
    }
        public class ViewHolder2 extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView product;
            TextView price;
            public ViewHolder2(@NonNull View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.image_item);
                product = itemView.findViewById(R.id.product_item);
                price = itemView.findViewById(R.id.price_item);
            }
        }
    private onClickCallBack clickCallBack;
    public interface onClickCallBack{
        void setOnClick(String title,String price);
    }
    public void setOnClickListener(onClickCallBack clickCallBack){
        this.clickCallBack = clickCallBack;
    }
}
