package model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie extends BaseEntity<Long>{
    private String title;
    private String genre;
    private Integer duration;

    @ManyToMany()
    private Set<User> users = new HashSet<>();

    public Movie(String title, String genre, Integer duration,Set<User> users) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", duration='" + duration + '\'' +
                ", user=" + users +
                '}';
    }
}
