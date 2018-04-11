package com.example.ham.goralets.Filter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.R;
import com.example.ham.goralets.TravelAgency.AgencyAcct;

import java.util.List;

/**
 * Created by Ham on 3/2/2018.
 */

public class Filter_TravelAgencyAdapter extends RecyclerView.Adapter<Filter_TravelAgencyAdapter.TAViewHolder>{

    private Context mCtx;
    private List<Filter_TravelAgencyGetSet> TAList;

    public Filter_TravelAgencyAdapter(Context mCtx, List<Filter_TravelAgencyGetSet> TAList){
        this.mCtx = mCtx;
        this.TAList = TAList;
    }

    @Override
    public TAViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.travelagency_list,null);
        return new TAViewHolder(view);

    }

    @Override
    public void onBindViewHolder(Filter_TravelAgencyAdapter.TAViewHolder holder, int position) {
        final Filter_TravelAgencyGetSet Filter_TravelAgencyGetSet = TAList.get(position);

        Glide.with(mCtx)
                .load(Filter_TravelAgencyGetSet.getImg())
                .into(holder.ImgTA);
        holder.TAName.setText(Filter_TravelAgencyGetSet.getName());
        holder.TAReviews.setText(String.valueOf(Filter_TravelAgencyGetSet.getReview()));
        holder.TAStars.setRating(Filter_TravelAgencyGetSet.getStars());

        holder.BtnAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("A_Name", Filter_TravelAgencyGetSet.getName());
                editor.putString("A_Info", Filter_TravelAgencyGetSet.getInfo());
                editor.putString("A_Contact", Filter_TravelAgencyGetSet.getContact() );
                editor.putString("A_Img", Filter_TravelAgencyGetSet.getImg());
                editor.putString("A_Review", String.valueOf(Filter_TravelAgencyGetSet.getReview()));
                editor.putString("A_Stars", String.valueOf(Filter_TravelAgencyGetSet.getStars()));

                editor.commit();

                Intent i = new Intent(mCtx, AgencyAcct.class);
                mCtx.startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return TAList.size();
    }

    class TAViewHolder extends RecyclerView.ViewHolder{

        TextView TAName, TAReviews;
        RatingBar TAStars;
        ImageView ImgTA;

        LinearLayout BtnAgency;

        public TAViewHolder(View itemView) {
            super(itemView);

            ImgTA = itemView.findViewById(R.id.ImgTA);
            TAName = itemView.findViewById(R.id.TAName);
            TAReviews = itemView.findViewById(R.id.TAReview);
            TAStars = itemView.findViewById(R.id.TARating);

            BtnAgency = itemView.findViewById(R.id.BtnAgency);


        }
    }
}

