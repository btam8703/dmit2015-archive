package dmit2015.bentam.assignment03.view;

import dmit2015.bentam.assignment03.entity.OscarReview;
import dmit2015.bentam.assignment03.repository.OscarReviewRepository;
import lombok.Getter;
import org.omnifaces.util.Messages;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 This java class is a controller for the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2020-03-11
 */

@Named("currentOscarReviewCreateController")
@RequestScoped
public class OscarReviewCreateController {

    @Inject
    private OscarReviewRepository _oscarreviewRepository;

    @Getter
    private OscarReview newOscarReview = new OscarReview();

    public String onCreate() {
        String nextPage = "";
        try {
            _oscarreviewRepository.add(newOscarReview);
            Messages.addFlashGlobalInfo("Create was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful.");
        }
        return nextPage;
    }

}