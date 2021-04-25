package dmit2015.bentam.assignment05.ejb;

import javax.ejb.Timer;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 This controller class handles the Timer
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Named
@SessionScoped
public class TimerController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private ProgrammaticTimersManagerBean timerBean;

    public String cancelAllTimers() {
        timerBean.cancelAllTimers();;
        return "";
    }

    public Collection<Timer> list() {
        return timerBean.listAllTimers();
    }

    public void cancelTimer(Timer selectedTimer) {
        timerBean.cancelTimer(selectedTimer);
    }
}