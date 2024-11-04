package com.example.moviepostercs460;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Adapter class for managing movie posters in a RecyclerView.
 */
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private final List<MoviePoster> moviePosters;
    private final OnPosterSelectListener listener;
    private final Set<Integer> selectedPositions = new HashSet<>();

    /**
     * Listener interface for handling poster selection events.
     */
    public interface OnPosterSelectListener {
        /**
         * Called when poster selection changes.
         *
         * @param selectedCount The number of selected posters.
         */
        void onPosterSelected(int selectedCount);
    }

    /**
     * Constructor for PosterAdapter.
     *
     * @param moviePosters The list of MoviePoster objects.
     * @param listener     The listener for poster selection events.
     */
    public PosterAdapter(List<MoviePoster> moviePosters, OnPosterSelectListener listener) {
        this.moviePosters = moviePosters;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poster, parent, false);
        return new PosterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        MoviePoster poster = moviePosters.get(position);
        holder.imageView.setImageResource(poster.getImageResId());
        holder.title.setText(poster.getTitle());
        holder.rating.setText(poster.getRating());
        holder.author.setText(poster.getAuthor());
        holder.description.setText(poster.getDescription());

        // Update selection visual
        boolean isSelected = selectedPositions.contains(position);
        holder.itemView.setBackgroundColor(isSelected ?
                holder.itemView.getContext().getResources().getColor(R.color.selected_color) :
                holder.itemView.getContext().getResources().getColor(R.color.default_color));
        holder.checkmark.setVisibility(isSelected ? View.VISIBLE : View.GONE);

        holder.itemView.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position);
            } else {
                selectedPositions.add(position);
            }
            notifyItemChanged(position);
            listener.onPosterSelected(selectedPositions.size());
        });
    }

    @Override
    public int getItemCount() {
        return moviePosters.size();
    }

    /**
     * Returns the set of selected poster positions.
     *
     * @return A Set of selected positions.
     */
    public Set<Integer> getSelectedPositions() {
        return selectedPositions;
    }

    /**
     * ViewHolder class for movie posters.
     */
    static class PosterViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, checkmark;
        TextView title, rating, author, description;

        /**
         * Constructor for PosterViewHolder.
         *
         * @param itemView The item view.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.poster_image);
            checkmark = itemView.findViewById(R.id.checkmark);
            title = itemView.findViewById(R.id.poster_title);
            rating = itemView.findViewById(R.id.poster_rating);
            author = itemView.findViewById(R.id.poster_author);
            description = itemView.findViewById(R.id.poster_description);
        }
    }
}
