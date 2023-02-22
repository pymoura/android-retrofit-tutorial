package edu.uci.swe264p.retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Creates Adapter extending RecyclerView.Adapter to convert object at a position
 * into a list row item. It also has an inner-class to describe and provide access
 * to all view within each item row.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder>{
    private final List<Movie> mData;
    private final Context context;

    MovieListAdapter(Context context, List<Movie> mData) {
        this.mData = mData;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // views that will be set when rendering each row
        TextView tvTitle;
        TextView tvRelease;
        TextView tvVote;
        TextView tvOverview;
        private final ImageView ivMovie;

        // constructor that accepts itemView View and does view lookups to find each subview.
        ViewHolder(View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvRelease = itemView.findViewById(R.id.tvReleaseDate);
            tvVote = itemView.findViewById(R.id.tvVote);
            tvOverview = itemView.findViewById(R.id.tvOverview);
        }
    }

    @Override
    public MovieListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflates a layout from movie_row.xml and returns a new holder instance
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieListAdapter.ViewHolder holder, int position) {
        // Binds each item to a view.
        holder.tvTitle.setText(mData.get(position).getTitle());
        holder.tvVote.setText(mData.get(position).getVoteAverage().toString());
        holder.tvRelease.setText(mData.get(position).getReleaseDate());
        holder.tvOverview.setText(mData.get(position).getOverview());

        //Use Picasso to download and render the poster image on list
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load("https://image.tmdb.org/t/p/w500" + mData.get(position).getPosterPath())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}


