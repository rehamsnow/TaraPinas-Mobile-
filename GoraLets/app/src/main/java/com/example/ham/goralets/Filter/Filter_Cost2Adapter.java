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

public class Filter_Cost2Adapter extends RecyclerView.Adapter<Filter_Cost2Adapter.C2ViewHolder>{

    private Context mCtx;
    private List<Filter_Cost2GetSet> C2List;

    public Filter_Cost2Adapter(Context mCtx, List<Filter_Cost2GetSet> C2List){
        this.mCtx = mCtx;
        this.C2List = C2List;
    }

    @Override
    public Filter_Cost2Adapter.C2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new Filter_Cost2Adapter.C2ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Filter_Cost2Adapter.C2ViewHolder holder, int position) {
        Filter_Cost2GetSet Filter_Cost2GetSet = C2List.get(position);

        Glide.with(mCtx)
                .load(Filter_Cost2GetSet.getImg())
                .into(holder.IVImg);
        holder.TVTitle.setText(Filter_Cost2GetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(Filter_Cost2GetSet.getPrice()));
        holder.TVReview.setText(String.valueOf(Filter_Cost2GetSet.getReview()));
    }

    @Override
    public int getItemCount() {
        return C2List.size();
    }

    class C2ViewHolder extends RecyclerView.ViewHolder{

        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;

        public C2ViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            TVReview = itemView.findViewById(R.id.TVReview);


        }
    }
}


