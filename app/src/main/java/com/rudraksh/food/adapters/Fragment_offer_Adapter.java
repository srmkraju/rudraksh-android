package com.rudraksh.food.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.models.OfferResponseModel;

import java.util.ArrayList;

/**
 * Created by dell8 on 20/5/16.
 */
public class Fragment_offer_Adapter extends RecyclerView.Adapter<Fragment_offer_Adapter.MyViewHolder> {
    private Context context;

    private ArrayList<OfferResponseModel.OfferData> offerdataArrayList;

    public Fragment_offer_Adapter(Context context, ArrayList<OfferResponseModel.OfferData> offerData) {
        this.context=context;
        offerdataArrayList=offerData;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
       MyViewHolder myViewHolder;
        View view = LayoutInflater.from(context).inflate(R.layout.row_fragment_offers_adapter,parent,false);
        myViewHolder = new MyViewHolder(view);
        return  myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final OfferResponseModel.OfferData  offerData = offerdataArrayList.get(position);
        holder.offerDataTextView.setText(offerData.getText());

    }

    @Override
    public int getItemCount() {
        return offerdataArrayList.size();
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView offerDataTextView;
        public MyViewHolder(View itemView)
        {
            super(itemView);
            offerDataTextView = (TextView)itemView.findViewById(R.id.row_fragment_offer_recycler_tv);

        }
    }
}
