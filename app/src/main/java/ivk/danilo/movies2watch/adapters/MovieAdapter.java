package ivk.danilo.movies2watch.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ivk.danilo.movies2watch.R;
import ivk.danilo.movies2watch.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private final List<Movie> movieList;

    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                                  inflate(R.layout.item_movie, parent, false);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = this.movieList.get(position);
        holder.title.setText(movie.title);
        holder.genre.setText(movie.genre);
        holder.year.setText(movie.formatYear());
        holder.rating.setText(movie.formatRating());

        holder.removeButton.setOnClickListener(v -> {
            this.movieList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, this.getItemCount());
        });
    }

    @Override
    public int getItemCount() {
        return this.movieList.size();
    }

    public static final class MovieViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView genre;
        public final TextView year;
        public final TextView rating;
        public final Button removeButton;

        public MovieViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.movie_title);
            this.genre = itemView.findViewById(R.id.movie_genre);
            this.year = itemView.findViewById(R.id.movie_year);
            this.rating = itemView.findViewById(R.id.movie_rating);
            this.removeButton = itemView.findViewById(R.id.remove_movie);
        }
    }
}
