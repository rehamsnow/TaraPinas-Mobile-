package com.example.ham.goralets.Deals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.Booking.Bookings;
import com.example.ham.goralets.LoginF.LoginGetSet;
import com.example.ham.goralets.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ham on 2/23/2018.
 */

public class DealsAdapter extends RecyclerView.Adapter<DealsAdapter.DealsViewHolder>{

    private Context mCtx;
    private List<DealsGetSet> dealList;

    private static final String TAG = "dealAdapter";

    public DealsAdapter(Context mCtx, List<DealsGetSet> dealList){
        this.mCtx = mCtx;
        this.dealList = dealList;
    }

    @Override
    public DealsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new DealsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(DealsAdapter.DealsViewHolder holder, int position) {
        final DealsGetSet dealsGetSet = dealList.get(position);

        Glide.with(mCtx)
                .load(dealsGetSet.getImg())
                .into(holder.IVImg);

        holder.TVTitle.setText(dealsGetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(dealsGetSet.getPrice()));
        holder.TVReview.setText(String.valueOf(dealsGetSet.getRating()));
        holder.ratingBar.setRating(dealsGetSet.getRating());
        

        holder.BtnDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: clicked");

                Intent intent = new Intent(mCtx, DealLayout.class);

                intent.putExtra("DealImage", dealsGetSet.getImg());
                intent.putExtra("DealImage2", dealsGetSet.getImg2());
                intent.putExtra("DealImage3", dealsGetSet.getImg3());

                intent.putExtra("DealTitle", dealsGetSet.getTitle());
                intent.putExtra("DealStartdate", dealsGetSet.getStartdate());
                intent.putExtra("DealEnddate", dealsGetSet.getEnddate());
                intent.putExtra("DealPrice", String.valueOf(dealsGetSet.getPrice()));
                intent.putExtra("DealRating", String.valueOf(dealsGetSet.getRating()));
                intent.putExtra("DealInclusions", dealsGetSet.getInclusions());
                intent.putExtra("DealExclusions", dealsGetSet.getExclusions());

                intent.putExtra("agency", dealsGetSet.getAgency());
                mCtx.startActivity(intent);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("DealID", String.valueOf(dealsGetSet.getId()));
                editor.putString("TravelAgency", String.valueOf(dealsGetSet.getAgency()));
                editor.putString("DEALPRICE", String.valueOf(dealsGetSet.getPrice()));

                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class DealsViewHolder extends RecyclerView.ViewHolder{

        Button BtnDeal;
        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;
        RatingBar ratingBar;

        TextView FeedFname, FeedLname, FeedComment;
        RatingBar FeedRating;
        ImageView FeedImg;

        public DealsViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            TVReview = itemView.findViewById(R.id.TVReview);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            BtnDeal = itemView.findViewById(R.id.BtnDeal);

            FeedFname = itemView.findViewById(R.id.FeedFname);
            FeedLname = itemView.findViewById(R.id.FeedLname);
            FeedRating = itemView.findViewById(R.id.FeedRating);
            FeedComment = itemView.findViewById(R.id.FeedComment);
            //FeedImg = itemView.findViewById(R.id.FeedImg);

        }
    }
}
