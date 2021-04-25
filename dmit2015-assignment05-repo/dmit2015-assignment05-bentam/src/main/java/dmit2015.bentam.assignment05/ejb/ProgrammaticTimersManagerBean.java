package dmit2015.bentam.assignment05.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import java.util.Collection;

/**
 This bean handles the cancelling and listing of timers
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Stateless
public class ProgrammaticTimersManagerBean {

	@Resource
	TimerService timerService;

    public void cancelAllTimers() {
        for(Timer singleTimer : timerService.getAllTimers()) {
            singleTimer.cancel();
        }
    }

    public void cancelTimer(Timer selectedTimer) {
        selectedTimer.cancel();
    }

    public Collection<Timer> listAllTimers() {
        return timerService.getAllTimers();
    }

}