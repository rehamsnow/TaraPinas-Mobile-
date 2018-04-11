package com.example.ham.goralets.TravelAgency;

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
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.R;

import java.util.List;

/**
 * Created by Ham on 4/9/2018.
 */

public class AgencyAdapter extends RecyclerView.Adapter<AgencyAdapter.AgencyViewHolder>{

    private Context mCtx;
    private List<AgencyGetSet> dealList;

    private static final String TAG = "dealAdapter";

    public AgencyAdapter(Context mCtx, List<AgencyGetSet> dealList){
        this.mCtx = mCtx;
        this.dealList = dealList;
    }

    @Override
    public AgencyAdapter.AgencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_feedback,null);
        return new AgencyAdapter.AgencyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AgencyAdapter.AgencyViewHolder holder, int position) {
        final AgencyGetSet dealsGetSet = dealList.get(position);

        holder.FeedComment.setText(dealsGetSet.getComment());
        holder.FeedRating.setRating(dealsGetSet.getFeedbackrating());
        holder.FeedFname.setText(dealsGetSet.getFname());
        holder.FeedLname.setText(dealsGetSet.getLname());

    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class AgencyViewHolder extends RecyclerView.ViewHolder{

        TextView FeedComment, FeedLname, FeedFname;
        RatingBar FeedRating;

        public AgencyViewHolder(View itemView) {
            super(itemView);

            FeedComment = itemView.findViewById(R.id.FeedComment);
            FeedLname = itemView.findViewById(R.id.FeedLname);
            FeedFname = itemView.findViewById(R.id.FeedFname);

            FeedRating = itemView.findViewById(R.id.FeedRating);

        }
    }
}
