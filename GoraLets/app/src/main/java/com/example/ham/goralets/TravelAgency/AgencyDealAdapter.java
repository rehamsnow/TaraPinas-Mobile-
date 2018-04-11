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

public class AgencyDealAdapter extends RecyclerView.Adapter<AgencyDealAdapter.AgencyViewHolder>{

    private Context mCtx;
    private List<AgencyDealGetSet> agencyList;

    private static final String TAG = "dealAdapter";

    public AgencyDealAdapter(Context mCtx, List<AgencyDealGetSet> agencyList){
        this.mCtx = mCtx;
        this.agencyList = agencyList;
    }

    @Override
    public AgencyDealAdapter.AgencyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.deals_list,null);
        return new AgencyDealAdapter.AgencyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(AgencyDealAdapter.AgencyViewHolder holder, int position) {
        final AgencyDealGetSet dealsGetSet = agencyList.get(position);

        Glide.with(mCtx)
                .load(dealsGetSet.getImg())
                .into(holder.IVImg);

        holder.TVTitle.setText(dealsGetSet.getTitle());
        holder.TVPrice.setText(String.valueOf(dealsGetSet.getPrice()));

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
               // intent.putExtra("DealRating", String.valueOf(dealsGetSet.getRating()));
                intent.putExtra("DealInclusions", dealsGetSet.getInclusions());
                intent.putExtra("DealExclusions", dealsGetSet.getExclusions());
                mCtx.startActivity(intent);

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
                SharedPreferences.Editor editor = prefs.edit();

                //editor.putString("DealID", String.valueOf(dealsGetSet.getId()));

                editor.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return agencyList.size();
    }

    class AgencyViewHolder extends RecyclerView.ViewHolder{

        Button BtnDeal;
        TextView TVTitle, TVPrice, TVReview;
        ImageView IVImg;



        public AgencyViewHolder(View itemView) {
            super(itemView);

            IVImg = itemView.findViewById(R.id.IVImg);
            TVTitle = itemView.findViewById(R.id.TVTitle);
            TVPrice = itemView.findViewById(R.id.TVPrice);
            BtnDeal = itemView.findViewById(R.id.BtnDeal);


        }
    }
}
