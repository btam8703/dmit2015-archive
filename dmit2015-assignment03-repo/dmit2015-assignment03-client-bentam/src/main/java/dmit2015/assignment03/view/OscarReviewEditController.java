package dmit2015.assignment03.view;

import dmit2015.assignment03.client.OscarReview;
import dmit2015.assignment03.client.OscarReviewService;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 This java class is a controller for the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2020-03-11
 */

@Named("currentOscarReviewEditController")
@ViewScoped
public class OscarReviewEditController implements Serializable {

    @Inject
    @RestClient
    private OscarReviewService _oscarReviewService;

    @Inject
    @ManagedProperty("#{param.editId}")
    @Getter
    @Setter
    private Long editId;

    @Getter
    private OscarReview existingOscarReview;

    @PostConstruct
    public void init() {
        if (!Faces.isPostback()) {
            existingOscarReview =_oscarReviewService.findOneById(editId);
        }
    }

    public String onUpdate() {
        String nextPage = "";
        try {
            _oscarReviewService.update(editId, existingOscarReview);
            Messages.addFlashGlobalInfo("Update was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Update was not successful.");
        }
        return nextPage;
    }

    public String onDelete() {
        String nextPage = "";
        try {
            _oscarReviewService.delete(editId);
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}