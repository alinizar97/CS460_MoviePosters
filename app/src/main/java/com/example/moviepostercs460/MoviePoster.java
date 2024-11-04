package com.example.moviepostercs460;

/**
 * Represents a movie poster with associated details.
 */
public class MoviePoster {
    private int imageResId;
    private String title;
    private String rating;
    private String author;
    private String description;

    /**
     * Constructor for MoviePoster.
     *
     * @param imageResId   The resource ID of the poster image.
     * @param title        The title of the movie.
     * @param rating       The rating of the movie.
     * @param author       The author or studio of the movie.
     * @param description  A brief description of the movie.
     */
    public MoviePoster(int imageResId, String title, String rating, String author, String description) {
        this.imageResId = imageResId;
        this.title = title;
        this.rating = rating;
        this.author = author;
        this.description = description;
    }

    /**
     * Gets the resource ID of the poster image.
     *
     * @return The image resource ID.
     */
    public int getImageResId() {
        return imageResId;
    }

    /**
     * Gets the title of the movie.
     *
     * @return The movie title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the rating of the movie.
     *
     * @return The movie rating.
     */
    public String getRating() {
        return rating;
    }

    /**
     * Gets the author or studio of the movie.
     *
     * @return The movie author or studio.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the description of the movie.
     *
     * @return The movie description.
     */
    public String getDescription() {
        return description;
    }
}
