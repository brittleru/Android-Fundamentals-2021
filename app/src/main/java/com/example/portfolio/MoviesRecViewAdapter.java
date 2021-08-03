package com.example.portfolio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesRecViewAdapter extends RecyclerView.Adapter<MoviesRecViewAdapter.ViewHolder> {

    private ArrayList<MovieCardView> movieCardViews = new ArrayList<>();
    private Context context;

    public MoviesRecViewAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.movieTitle.setText(movieCardViews.get(position).getMovieName());
        holder.releaseDate.setText(movieCardViews.get(position).getReleaseDate());
        holder.overview.setText(movieCardViews.get(position).getOverview());
        holder.parentMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movieCardViews.get(position).getMovieName() + " Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // For image loading
        Glide.with(context).asBitmap().load(movieCardViews.get(position).getImageURL()).into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return movieCardViews.size();
    }

    public void setMovieCardViews(ArrayList<MovieCardView> movieCardViews) {
        this.movieCardViews = movieCardViews;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView movieTitle, overview, releaseDate;
        private CardView parentMovie;
        private ImageView movieImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.movieName);
            releaseDate = itemView.findViewById(R.id.releaseDate);
            overview = itemView.findViewById(R.id.overview);
            parentMovie = itemView.findViewById(R.id.parentMovie);
            movieImage = itemView.findViewById(R.id.movieImage);
        }

    }
}