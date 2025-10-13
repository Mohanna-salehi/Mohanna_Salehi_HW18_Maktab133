package model;

import jakarta.persistence.*;
import org.hibernate.Remove;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="users")
public class User extends BaseEntity<Long>{
    private String username;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade ={ CascadeType.PERSIST})
    @JoinTable(
            name = "user_movie", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies= new HashSet<>();

    public User(String username, String email, String password, Set<Movie> movies) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.movies = movies;
    }

    public User() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", movies=" + movies +
                '}';
    }
}
