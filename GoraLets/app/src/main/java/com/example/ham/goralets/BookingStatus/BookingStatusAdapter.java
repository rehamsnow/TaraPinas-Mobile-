package com.example.ham.goralets.BookingStatus;

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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ham.goralets.Deals.DealLayout;
import com.example.ham.goralets.Deals.DealsAdapter;
import com.example.ham.goralets.Deals.DealsGetSet;
import com.example.ham.goralets.R;

import java.util.List;

/**
 * Created by Ham on 4/3/2018.
 */

public class BookingStatusAdapter extends RecyclerView.Adapter<BookingStatusAdapter.BookingStatusViewHolder>{

    private Context mCtx;
    private List<BookingStatusGetSet> BookingStatusList;

    private static final String TAG = "BookingStatusAdapter";

    public BookingStatusAdapter(Context mCtx, List<BookingStatusGetSet> BookingStatusList){
        this.mCtx = mCtx;
        this.BookingStatusList = BookingStatusList;
    }

    @Override
    public BookingStatusAdapter.BookingStatusViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.activity_booking_status,null);
        return new BookingStatusAdapter.BookingStatusViewHolder(view);

    }

    @Override
    public void onBindViewHolder(BookingStatusAdapter.BookingStatusViewHolder holder, int position) {
        final BookingStatusGetSet BookingStatusGetSet = BookingStatusList.get(position);


        Glide.with(mCtx)
                .load(BookingStatusGetSet.getImg1())
                .into(holder.BSImg);

        holder.BSLocation.setText(BookingStatusGetSet.getLocation());
        holder.BSStartdate.setText(BookingStatusGetSet.getStart());
        holder.BSEnddate.setText(BookingStatusGetSet.getEnd());
        holder.BSStatus.setText(BookingStatusGetSet.getStatus());

        holder.BtnFollowup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mCtx, "Booking request follow up is successfully sent!", Toast.LENGTH_SHORT).show();
            }
        });

        holder.BtnViewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "OnClick: clicked");

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(mCtx);
                SharedPreferences.Editor editor = prefs.edit();

                editor.putString("BS_id", String.valueOf(BookingStatusGetSet.getBookid()));
                /*

                editor.putString("BS_dealid", String.valueOf(BookingStatusGetSet.getDealid()));
                */
                editor.putString("BS_img", BookingStatusGetSet.getImg1());
                editor.putString("BS_location", BookingStatusGetSet.getLocation());

                editor.putString("BS_status", BookingStatusGetSet.getStatus());
                editor.putString("BS_price", String.valueOf(BookingStatusGetSet.getPrice()));
                editor.putString("BS_start", BookingStatusGetSet.getStart());
                editor.putString("BS_end", BookingStatusGetSet.getEnd());
                editor.putString("BS_pax", String.valueOf(BookingStatusGetSet.getPax()));
                editor.putString("BS_fname", BookingStatusGetSet.getFname());
                editor.putString("BS_lname", BookingStatusGetSet.getLname());
                editor.putString("BS_email", BookingStatusGetSet.getEmail());
                editor.putString("BS_contact", BookingStatusGetSet.getContact());

                editor.commit();

                Intent i = new Intent (mCtx, booking_status_details.class);
                mCtx.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return BookingStatusList.size();
    }

    class BookingStatusViewHolder extends RecyclerView.ViewHolder{

       ImageView BSImg;
       TextView BSLocation, BSStartdate, BSEnddate, BSStatus;
       Button BtnViewBook, BtnFollowup;

        public BookingStatusViewHolder(View itemView) {
            super(itemView);

           BSImg = (ImageView) itemView.findViewById(R.id.BSImg);
           BSLocation = (TextView) itemView.findViewById(R.id.BSLocation);
           BSStartdate = (TextView) itemView.findViewById(R.id.BSStartdate);
           BSEnddate = (TextView) itemView.findViewById(R.id.BSEnddate);
           BSStatus = (TextView) itemView.findViewById(R.id.BSStatus);

           BtnViewBook = (Button) itemView.findViewById(R.id.BtnViewBook);
           BtnFollowup = (Button) itemView.findViewById(R.id.BtnFollowup);
        }
    }
}

