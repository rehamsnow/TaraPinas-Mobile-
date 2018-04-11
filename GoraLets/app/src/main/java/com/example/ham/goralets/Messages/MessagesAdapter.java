package com.example.ham.goralets.Messages;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.R;

import java.util.List;

/**
 * Created by Ham on 4/9/2018.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder>{

    private Context mCtx;
    private List<MessagesGetSet> dealList;

    private static final String TAG = "dealAdapter";

    public MessagesAdapter(Context mCtx, List<MessagesGetSet> dealList){
        this.mCtx = mCtx;
        this.dealList = dealList;
    }

    @Override
    public MessagesAdapter.MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.message_list,null);
        return new MessagesAdapter.MessagesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MessagesAdapter.MessagesViewHolder holder, int position) {
        final MessagesGetSet dealsGetSet = dealList.get(position);

        holder.message.setText(dealsGetSet.getMessage());
        holder.BtnSendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Message sent!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dealList.size();
    }

    class MessagesViewHolder extends RecyclerView.ViewHolder{

       TextView message;
       Button BtnSendMsg;
       EditText InquiryMsg;

        public MessagesViewHolder(View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            BtnSendMsg = itemView.findViewById(R.id.BtnSendMsg);
            InquiryMsg = itemView.findViewById(R.id.InquiryMsg);

        }
    }
}
