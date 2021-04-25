package dmit2015.bentam.assignment03.view;

import dmit2015.bentam.assignment03.entity.OscarReview;
import dmit2015.bentam.assignment03.repository.OscarReviewRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

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
    private OscarReviewRepository _oscarreviewRepository;

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
            Optional<OscarReview> optionalEntity = _oscarreviewRepository.findById(editId);
            optionalEntity.ifPresent(entity -> existingOscarReview = entity);
        }
    }

    public String onUpdate() {
        String nextPage = "";
        try {
            _oscarreviewRepository.update(existingOscarReview);
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
            _oscarreviewRepository.remove(existingOscarReview.getId());
            Messages.addFlashGlobalInfo("Delete was successful.");
            nextPage = "index?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            Messages.addGlobalError("Delete not successful.");
        }
        return nextPage;
    }
}