package dmit2015.view;

import dmit2015.entity.Movie;
import dmit2015.repository.MovieRepository;
import org.omnifaces.util.Messages;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("currentMovieListController")
@ViewScoped
public class MovieListController implements Serializable {

    @Inject
    private MovieRepository _movieRepository;

    @Getter
    private List<Movie> movieList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            movieList = _movieRepository.findAll();
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}