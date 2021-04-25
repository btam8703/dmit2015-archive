package dmit2015.listener;

import dmit2015.entity.CallerUser;
import dmit2015.repository.CallerUserRepository;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

/**
 This java class contains the ApplicationStartupListener class
 @author Benjamin Tam
 @version 1.0
 @since 2021-3-28
 */

@WebListener
public class ApplicationStartupListener implements ServletContextListener {
    private static final Logger logger = Logger.getLogger(ApplicationStartupListener.class.getName());

    @Inject
    CallerUserRepository callerUserRepository;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (callerUserRepository.list().size() == 0) {
            logger.info("Creating accounts for application");
            CallerUser user01 = new CallerUser();
            user01.setUsername("moe@3stooges.com");
            callerUserRepository.add(user01, "Password2015", new String[]{"REGISTER_USER"});

            CallerUser user02 = new CallerUser();
            user02.setUsername("curly@3stooges.com");
            callerUserRepository.add(user02, "Password2015", new String[]{"REGISTER_USER"});

            CallerUser admin01 = new CallerUser();
            admin01.setUsername("larry@3stooges.com");
            callerUserRepository.add(admin01, "Password2015", new String[]{"ADMIN"});
        } else {
            logger.info("Application has user accounts");
        }
    }
}