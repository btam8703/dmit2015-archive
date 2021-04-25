package dmit2015.bentam.assignment05.producer;

/**
 This class produces the logger
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

import java.util.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
public class LoggerProducer {
    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return
                Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }
}
