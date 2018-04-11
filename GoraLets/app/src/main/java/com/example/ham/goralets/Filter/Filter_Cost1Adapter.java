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

public class Filter_Cost1Adapter extends RecyclerView.Adapter<Filter_Cost1Adapter.C1ViewHolder>{

    private Context mCtx;
    private List<Filter_Cost1GetSet> C1List;

    public Filter_Cost1Adapter(Context mCtx, List<Filter_Cost1GetSet> C1List){
        this.mCtx = mCtx;
        this.C1List = C1List;
    }

    @Override
    public Filter_Cost1Adapter.C1ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new Filter_Cost1Adapter.C1ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Filter_Cost1Adapter.C1ViewHolder holder, int position) {
        Filter_Cost1GetSet Filter_Cost1GetSet = C1List.get(position);

        Glide.with(mCtx)
                .load(Filter_Cost1GetSet.getImg())
                .into(holder.IVImg);
        holder.TVTitle.setText(Filter_Cost1GetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(Filter_Cost1GetSet.getPrice()));
        holder.TVReview.setText(String.valueOf(Filter_Cost1GetSet.getReview()));
    }

    @Override
    public int getItemCount() {
        return C1List.size();
    }

    class C1ViewHolder extends RecyclerView.ViewHolder{

        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;

        public C1ViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            TVReview = itemView.findViewById(R.id.TVReview);


        }
    }
}


