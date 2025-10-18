package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie extends BaseEntity<Long>{
    private String title;
    private String genre;
    private String description;
    private LocalDate date;
    private Double rating;

    @ManyToMany(mappedBy = "movies")
    private Set<User> users = new HashSet<>();

    public Movie(String title, String genre, String description, LocalDate date, Double rating, Set<User> users) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.date = date;
        this.rating = rating;
        this.users = users;
    }

    public Movie() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", description=" + description +
                ", date=" + date +
                ", rating=" + rating +
                ", users=" + users +
                '}';
    }
}
