package com.rudraksh.food.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.rudraksh.food.R;

import com.rudraksh.food.models.ProductListModel;
import com.rudraksh.food.utils.Constant;

import com.rudraksh.food.utils.OnRecyclerViewItemClickListener;
import com.rudraksh.food.widgets.AppImageView;


import java.util.ArrayList;


/**
 * Created by Raju on 4/14/2016.
 */
public class FoodTypeAdapter extends RecyclerView.Adapter<FoodTypeAdapter.MyViewHolder> {


    private Context context;

    private static OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    private ArrayList<ProductListModel.ProductResponseData>productListModelArrayList ;

    public FoodTypeAdapter(Context context, ArrayList<ProductListModel.ProductResponseData> productListModelArrayList,OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.context = context;
        this.productListModelArrayList = productListModelArrayList;
        this.onRecyclerViewItemClickListener=onRecyclerViewItemClickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView foodFragmentRecyclerTextView;
        private AppImageView foodFragmentRecyclerImageView;
        public MyViewHolder(View view) {
            super(view);
            foodFragmentRecyclerTextView = (TextView) view.findViewById(R.id.fragment_food_name_tv);
            foodFragmentRecyclerImageView = (AppImageView) view.findViewById(R.id.fragment_food_name_iv);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(),v);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_food_type_recycler_adapter, parent, false);
        MyViewHolder myViewHolder=new MyViewHolder(itemView);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(FoodTypeAdapter.MyViewHolder holder, int position) {
        final ProductListModel.ProductResponseData data=productListModelArrayList.get(position);
        holder.foodFragmentRecyclerImageView.loadImage(Constant.BASE_URL+"uploads/"+data.getImage());
        holder.foodFragmentRecyclerTextView.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return productListModelArrayList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
