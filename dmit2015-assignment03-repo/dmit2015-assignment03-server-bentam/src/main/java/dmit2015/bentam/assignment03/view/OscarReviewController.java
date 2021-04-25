package dmit2015.bentam.assignment03.view;

import dmit2015.bentam.assignment03.entity.OscarReview;
import dmit2015.bentam.assignment03.repository.OscarReviewRepository;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 This java class is a controller for the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2020-03-11
 */

@Named("currentOscarReviewListController")
@ViewScoped
public class OscarReviewController implements Serializable {

    @Inject
    private OscarReviewRepository _oscarreviewRepository;

    @Getter
    private List<OscarReview> oscarreviewList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            oscarreviewList = _oscarreviewRepository.findAll();
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}