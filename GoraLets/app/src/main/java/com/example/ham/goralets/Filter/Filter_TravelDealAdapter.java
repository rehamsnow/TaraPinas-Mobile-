package com.example.ham.goralets.Filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.R;

import java.util.List;

/**
 * Created by Ham on 3/2/2018.
 */

public class Filter_TravelDealAdapter extends RecyclerView.Adapter<Filter_TravelDealAdapter.TDViewHolder>{

    private Context mCtx;
    private List<Filter_TravelDealGetSet> TDList;

    public Filter_TravelDealAdapter(Context mCtx, List<Filter_TravelDealGetSet> TDList){
        this.mCtx = mCtx;
        this.TDList = TDList;
    }

    @Override
    public Filter_TravelDealAdapter.TDViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new Filter_TravelDealAdapter.TDViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Filter_TravelDealAdapter.TDViewHolder holder, int position) {
        Filter_TravelDealGetSet Filter_TravelDealGetSet = TDList.get(position);

        Glide.with(mCtx)
                .load(Filter_TravelDealGetSet.getImg())
                .into(holder.IVImg);
        holder.TVTitle.setText(Filter_TravelDealGetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(Filter_TravelDealGetSet.getPrice()));
        holder.TVReview.setText(String.valueOf(Filter_TravelDealGetSet.getReview()));
    }

    @Override
    public int getItemCount() {
        return TDList.size();
    }

    class TDViewHolder extends RecyclerView.ViewHolder{

        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;

        public TDViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            TVReview = itemView.findViewById(R.id.TVReview);


        }
    }
}

