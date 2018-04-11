package com.example.ham.goralets.Feedback;

import android.content.Context;
import android.content.Intent;
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
import com.example.ham.goralets.Feedback.FeedbackGetSet;
import com.example.ham.goralets.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ham on 2/23/2018.
 */

public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.FeedbackViewHolder>{

    private Context mCtx;
    private List<FeedbackGetSet> dealList;

    private static final String TAG = "dealAdapter";

    public FeedbackAdapter(Context mCtx, List<FeedbackGetSet> dealList){
        this.mCtx = mCtx;
        this.dealList = dealList;
    }

    @Override
    public FeedbackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_feedback,null);
        return new FeedbackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedbackAdapter.FeedbackViewHolder holder, final int position) {
        final FeedbackGetSet feedbackGetSet = dealList.get(position);

        holder.FeedFname.setText(feedbackGetSet.getFname());
        holder.FeedLname.setText(feedbackGetSet.getLname());
        holder.FeedComment.setText(feedbackGetSet.getComment());
        holder.FeedRating.setRating(feedbackGetSet.getFeedbackrating());
        /*
        Glide.with(mCtx)
                .load(feedbackGetSet.getFeedbackimg())
                .into(holder.FeedImg);
        */
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class FeedbackViewHolder extends RecyclerView.ViewHolder{

        TextView FeedFname, FeedLname, FeedComment;
        RatingBar FeedRating;
        ImageView FeedImg, FeedLeftBtn, FeedRightBtn;

        public FeedbackViewHolder(View itemView) {
            super(itemView);

            FeedFname = itemView.findViewById(R.id.FeedFname);
            FeedLname = itemView.findViewById(R.id.FeedLname);
            FeedRating = itemView.findViewById(R.id.FeedRating);
            FeedComment = itemView.findViewById(R.id.FeedComment);
           // FeedImg = itemView.findViewById(R.id.FeedImg);

           // FeedLeftBtn = itemView.findViewById(R.id.FeedLeftBtn);
            //FeedRightBtn = itemView.findViewById(R.id.FeedRightBtn);



        }
    }
}
