package lld_problems.movieticketbookingsystem;

public class Movie {
    private String title;
    private String description;
    private final int movieDurationInMinutes;
    public Movie(String title,int duration, String description) {
        this.title = title;
        this.description=description;
        this.movieDurationInMinutes=duration;
    }
    public int getMovieDuration() { return movieDurationInMinutes;}
}
