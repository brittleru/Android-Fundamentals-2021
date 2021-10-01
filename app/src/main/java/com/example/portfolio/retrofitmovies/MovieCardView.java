package com.example.portfolio.retrofitmovies;

public class MovieCardView {
    private String movieName;
    private String releaseDate;
    private String imageURL;
    private String overview;

    public MovieCardView(String movieName, String releaseDate, String imageURL, String overview) {
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.imageURL = imageURL;
        this.overview = overview;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override
    public String toString() {
        return "MovieCardView{" +
                "movieName='" + movieName + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", overview='" + overview + '\'' +
                '}';
    }
}