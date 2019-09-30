package com.diki.submisisatu.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.diki.submisisatu.DetailMovieActivity;
import com.diki.submisisatu.R;
import com.diki.submisisatu.repo.dao.FavoriteMovieDB;

import java.util.List;

import static com.diki.submisisatu.BuildConfig.POSTER_PATH;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.Viewholder> {
    public class Viewholder extends RecyclerView.ViewHolder {
//        @BindView(R.id.img_item_fotoFav)
//        ImageView listMoviePoster;
//        @BindView(R.id.realease)
//        TextView listMovieReleaseDate;
//        @BindView(R.id.tvTitle)
//        TextView listMovieTitle;
//        @BindView(R.id.tv_item_remarksFav)
//        TextView listMovieRating;
//        @BindView(R.id.rv_listMovieFav)
//        RecyclerView btnMovie;

        //        Viewholder(View itemView, PostListListener postListListener) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//
//            this.mListListener = postListListener;
//            itemView.setOnClickListener(this);
//        }
        TextView listMovieTitle, listMovieDescription;
        ImageView listMovieImage;

        Viewholder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
            listMovieTitle = itemView.findViewById(R.id.tv_item_nameFav);
            listMovieDescription = itemView.findViewById(R.id.tv_item_remarksFav);
            listMovieImage = itemView.findViewById(R.id.img_item_fotoFav);


        }

    }

    private List<FavoriteMovieDB> favorites;
    private final Activity activity;

    public FavoriteAdapter(Activity activity, List<FavoriteMovieDB> favorites) {
        this.activity = activity;
        this.favorites = favorites;
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_row_favorite, parent, false);

        return new Viewholder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        FavoriteMovieDB favorite = favorites.get(position);

        if (favorite.getName().length() > 15) {
            holder.listMovieTitle.setText((favorite.getName().substring(0, 15) + "..."));
        } else {
            holder.listMovieTitle.setText(favorite.getName());
        }

        holder.listMovieDescription.setText(String.valueOf(favorite.getDescription()));
//        holder.listMovieReleaseDate.setText(favorite.getDate().split("-")[0]);
        Glide.with(activity)
                .load(POSTER_PATH + favorites.get(position).getPosterPath())
                .apply(RequestOptions.placeholderOf(R.color.colorPrimary))
                .into(holder.listMovieImage);

    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

}
