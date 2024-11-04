package com.example.moviepostercs460;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Main activity of the Movie Poster app.
 * Displays a list of movie posters that users can select and add to a watchlist.
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PosterAdapter adapter;
    private Button watchlistButton;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The saved instance state.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        watchlistButton = findViewById(R.id.watchlist_button);
        watchlistButton.setVisibility(View.GONE);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        List<MoviePoster> posters = getMoviePosters();
        adapter = new PosterAdapter(posters, this::onPosterSelectionChanged);
        recyclerView.setAdapter(adapter);

        watchlistButton.setOnClickListener(v -> {
            Set<Integer> selectedPositions = adapter.getSelectedPositions();
            List<String> selectedMovies = new ArrayList<>();
            for (int position : selectedPositions) {
                selectedMovies.add(posters.get(position).getTitle());
            }
            Toast.makeText(this, "Watchlist: " + String.join(", ", selectedMovies), Toast.LENGTH_LONG).show();
        });
    }

    /**
     * Inflates the options menu.
     *
     * @param menu The options menu.
     * @return true if the menu is created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles menu item selections.
     *
     * @param item The selected menu item.
     * @return true if the item selection is handled successfully.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.action_search) {
            Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.action_menu) {
            Toast.makeText(this, "Menu clicked", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Provides a list of movie posters.
     *
     * @return A list of MoviePoster objects.
     */
    private List<MoviePoster> getMoviePosters() {
        List<MoviePoster> posters = new ArrayList<>();
        posters.add(new MoviePoster(R.drawable.avengers, "Avengers", "4.8", "Marvel Studios", "Superhero action movie"));
        posters.add(new MoviePoster(R.drawable.batman, "Batman", "4.7", "DC Comics", "Dark Knight's saga"));
        posters.add(new MoviePoster(R.drawable.bullet, "Bullet Train", "4.3", "Action Studio", "High-octane action"));
        posters.add(new MoviePoster(R.drawable.deadpool, "Deadpool", "4.6", "Marvel Studios", "Merc with a mouth"));
        posters.add(new MoviePoster(R.drawable.dune, "Dune", "4.2", "Denis Villeneuve", "Epic sci-fi saga"));
        posters.add(new MoviePoster(R.drawable.harry_potter, "Harry Potter", "4.8", "J.K. Rowling", "Wizarding world adventure"));
        posters.add(new MoviePoster(R.drawable.hobbit, "The Hobbit", "4.4", "Peter Jackson", "Middle-earth journey"));
        posters.add(new MoviePoster(R.drawable.joker, "Joker", "4.9", "Todd Phillips", "Origin of Joker"));
        posters.add(new MoviePoster(R.drawable.nacho, "Nacho Libre", "4.1", "Jared Hess", "Comedy wrestling"));
        posters.add(new MoviePoster(R.drawable.spiderman, "Spiderman", "4.0", "Marvel Studios", "Web-slinging hero"));
        return posters;
    }

    /**
     * Handles changes in poster selection.
     *
     * @param selectedCount The number of selected posters.
     */
    private void onPosterSelectionChanged(int selectedCount) {
        watchlistButton.setVisibility(selectedCount > 0 ? View.VISIBLE : View.GONE);
    }
}
