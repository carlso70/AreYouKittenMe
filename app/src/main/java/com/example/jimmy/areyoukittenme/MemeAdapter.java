package com.example.jimmy.areyoukittenme;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by jamescarlson on 5/26/17.
 */

public class MemeAdapter extends RecyclerView.Adapter<MemeAdapter.MemeViewHolder>  {
    private List<Meme> data;
    private Context context;

    public static class MemeViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;
        private TextView memeTextView;
        private ImageView memeThumbnail;
        public MemeViewHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.card_view);
            memeTextView = (TextView) v.findViewById(R.id.meme_title);
            memeThumbnail = (ImageView) v.findViewById(R.id.meme_thumbnail);
        }
    }

    public MemeAdapter(List<Meme> meme, Context context) {
        this.data = meme;
        this.context = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MemeAdapter.MemeViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_meme, parent, false);

        // set the view's size, margins, paddings and layout parameters
        MemeViewHolder viewHolder = new MemeViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(MemeViewHolder holder, int position) {
        holder.memeTextView.setText(data.get(position).getTitle());
        Picasso.with(context).load(data.get(position).getThumbnail()).into(holder.memeThumbnail);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
