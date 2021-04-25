package dmit2015.assignment03.view;

import dmit2015.assignment03.client.OscarReview;
import dmit2015.assignment03.client.OscarReviewService;
import lombok.Getter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
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
    @RestClient
    private OscarReviewService _oscarReviewService;

    @Getter
    private OscarReview newOscarReview = new OscarReview();

    public String onCreate() {
        String nextPage = "";
        try {
            _oscarReviewService.create(newOscarReview);
            Messages.addFlashGlobalInfo("Create was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Create was not successful.");
        }
        return nextPage;
    }

}