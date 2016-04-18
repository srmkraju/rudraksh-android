package com.rudraksh.food.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rudraksh.food.R;
import com.rudraksh.food.models.MainMenuModel;
import com.rudraksh.food.utils.OnRecyclerViewItemClickListener;

import java.util.List;

/**
 * Created by Raju on 4/14/2016.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder> {

    private List<MainMenuModel> mainMenuModelList;
    private Context context;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public MainRecyclerAdapter(Context context,List<MainMenuModel> mainMenuModelList) {
        this.context = context;
        this.mainMenuModelList = mainMenuModelList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView rowMainRecyclerTextView;
        private CardView rowMainRecyclerCardView;

        public MyViewHolder(View view) {
            super(view);
            rowMainRecyclerTextView = (TextView) view.findViewById(R.id.row_main_recycler_tv);
            rowMainRecyclerCardView = (CardView) view.findViewById(R.id.row_main_recycler_cardView);

            rowMainRecyclerCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onRecyclerViewItemClickListener != null) {
                        onRecyclerViewItemClickListener.onItemClick(getAdapterPosition(), view);
                    }
                }
            });
        }
    }

    @Override
    public MainRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_main_recycler_adapter, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MainRecyclerAdapter.MyViewHolder holder, int position) {
        MainMenuModel menuModel = mainMenuModelList.get(position);
        holder.rowMainRecyclerTextView.setText(menuModel.getCardViewName());
        holder.rowMainRecyclerTextView.setCompoundDrawablesWithIntrinsicBounds(menuModel.getCardViewImage(),0,0,0);

        holder.rowMainRecyclerCardView.setTag(menuModel);
    }

    @Override
    public int getItemCount() {
        return mainMenuModelList.size();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }
}
