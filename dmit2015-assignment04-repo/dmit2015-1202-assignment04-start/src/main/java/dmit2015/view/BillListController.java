package dmit2015.view;

import dmit2015.entity.Bill;
import dmit2015.repository.BillRepository;
import org.omnifaces.util.Messages;
import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 This java class contains the list controller for the Bill entity
 @author Benjamin Tam
 @version 1.0
 @since 2021-3-28
 */

@Named("currentBillListController")
@ViewScoped
public class BillListController implements Serializable {

    @Inject
    private BillRepository _billRepository;

    @Getter
    private List<Bill> billList;

    @PostConstruct  // After @Inject is complete
    public void init() {
        try {
            billList = _billRepository.findAll();
        } catch (RuntimeException e) {
            Messages.addGlobalInfo(e.getMessage());
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }
}