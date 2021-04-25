package dmit2015.bentam.assignment05.view;


import dmit2015.bentam.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.bentam.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Optional;

/**
 This controller handles the searching of cases
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */


@Named("CurrentCasesByLocalGeographicAreaSearchController")
@ViewScoped
public class CurrentCasesByLocalGeographicAreaSearchController implements Serializable {

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _currentcasesbylocalgeographicareaRepository;

    @Getter
    private CurrentCasesByLocalGeographicArea findOneResult;

    @Getter @Setter
    private double longitude;

    @Getter @Setter
    private double latitude;

    public void onSearch() {
        try {
            Optional<CurrentCasesByLocalGeographicArea> optionalArea = _currentcasesbylocalgeographicareaRepository.contains(longitude, latitude);
            if (optionalArea.isPresent()) {
                findOneResult = optionalArea.get();
            } else {
                findOneResult = null;
            }
        } catch (Exception ex) {
            Messages.addGlobalError(ex.getMessage());
        }
    }

    public void onClear() {
        findOneResult = null;
    }
}
