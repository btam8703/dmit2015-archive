package dmit2015.assignment03.view;

import dmit2015.assignment03.client.OscarReview;
import dmit2015.assignment03.client.OscarReviewService;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Messages;
import lombok.Getter;

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
public class OscarReviewListController implements Serializable {

    @Inject
    @RestClient
    private OscarReviewService _oscarReviewService;

    @Getter
    private List<OscarReview> oscarreviewList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            oscarreviewList = _oscarReviewService.findAll();
        } catch (RuntimeException ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}