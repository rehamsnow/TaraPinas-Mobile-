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

public class Filter_Cost4Adapter extends RecyclerView.Adapter<Filter_Cost4Adapter.C4ViewHolder>{

    private Context mCtx;
    private List<Filter_Cost4GetSet> C4List;

    public Filter_Cost4Adapter(Context mCtx, List<Filter_Cost4GetSet> C4List){
        this.mCtx = mCtx;
        this.C4List = C4List;
    }

    @Override
    public Filter_Cost4Adapter.C4ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new Filter_Cost4Adapter.C4ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Filter_Cost4Adapter.C4ViewHolder holder, int position) {
        Filter_Cost4GetSet Filter_Cost4GetSet = C4List.get(position);

        Glide.with(mCtx)
                .load(Filter_Cost4GetSet.getImg())
                .into(holder.IVImg);
        holder.TVTitle.setText(Filter_Cost4GetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(Filter_Cost4GetSet.getPrice()));
        holder.TVReview.setText(String.valueOf(Filter_Cost4GetSet.getReview()));
    }

    @Override
    public int getItemCount() {
        return C4List.size();
    }

    class C4ViewHolder extends RecyclerView.ViewHolder{

        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;

        public C4ViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            TVReview = itemView.findViewById(R.id.TVReview);


        }
    }
}


