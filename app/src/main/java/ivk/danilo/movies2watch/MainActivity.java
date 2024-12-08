package ivk.danilo.movies2watch;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ivk.danilo.movies2watch.adapters.MovieAdapter;
import ivk.danilo.movies2watch.helpers.Comparator;
import ivk.danilo.movies2watch.models.Movie;

public class MainActivity extends AppCompatActivity {
    private final List<Movie> movieList = new ArrayList<>();
    private MovieAdapter movieAdapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.movieList.add(new Movie("Inception", "Sci-Fi", 2010, 8.8));
        this.movieList.add(new Movie("The Dark Knight", "Action", 2008, 9.0));
        this.movieList.add(new Movie("Austin Powers in Goldmember", "Comedy", 2002, 6.2));
        this.movieList.add(new Movie("Interstellar", "Sci-Fi", 2014, 8.6));
        this.movieList.add(new Movie("The Matrix", "Sci-Fi", 1999, 8.7));
        this.movieList.add(new Movie("Gladiator", "Action", 2000, 8.5));
        this.movieList.add(new Movie("Titanic", "Romance", 1997, 7.8));
        this.movieList.add(new Movie("The Social Network", "Drama", 2010, 7.7));
        this.movieList.add(new Movie("Parasite", "Thriller", 2019, 8.6));
        this.movieList.add(new Movie("Pulp Fiction", "Crime", 1994, 8.9));

        this.movieList.sort((first, second) -> Comparator.stringAsc(first.genre, second.genre));

        final Spinner sortSpinner = findViewById(R.id.sort_movies);
        final RecyclerView recyclerView = findViewById(R.id.movie_list);

        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.sort_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);

        this.movieAdapter = new MovieAdapter(this.movieList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.movieAdapter);

        this.initializeSortSpinner(sortSpinner);
    }

    private void initializeSortSpinner(Spinner sortSpinner) {
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        sortMoviesByGenre(true);
                        break;
                    case 1:
                        sortMoviesByGenre(false);
                        break;
                    case 2:
                        sortMoviesByReleaseDate(true);
                        break;
                    case 3:
                        sortMoviesByReleaseDate(false);
                        break;
                    case 4:
                        sortMoviesByRating(true);
                        break;
                    case 5:
                        sortMoviesByRating(false);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sortMoviesByGenre(boolean desc) {
        this.movieList.sort((first, second) -> desc
                ? Comparator.stringDesc(first.genre, second.genre)
                : Comparator.stringAsc(first.genre, second.genre)
        );

        this.movieAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sortMoviesByReleaseDate(boolean desc) {
        this.movieList.sort((first, second) -> desc
                ? Comparator.intDesc(first.year, second.year)
                : Comparator.intAsc(first.year, second.year)
        );

        this.movieAdapter.notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void sortMoviesByRating(boolean desc) {
        this.movieList.sort((first, second) -> desc
                ? Comparator.doubleDesc(first.rating, second.rating)
                : Comparator.doubleAsc(first.rating, second.rating)
        );

        this.movieAdapter.notifyDataSetChanged();
    }
}
