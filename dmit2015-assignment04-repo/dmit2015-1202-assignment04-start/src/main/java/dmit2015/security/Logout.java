package dmit2015.security;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 This java class contains the Logout class
 @author Benjamin Tam
 @version 1.0
 @since 2021-3-28
 */

@Named
@RequestScoped
public class Logout {

    @Inject
    private HttpServletRequest request;

    public String submit() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index?faces-redirect=true";
    }
}