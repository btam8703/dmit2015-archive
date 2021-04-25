package dmit2015.security;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.security.enterprise.SecurityContext;

/**
 This java class contains the SecurityInterceptor for the Bill and BillPayment entities
 @author Benjamin Tam
 @version 1.0
 @since 2021-3-28
 */

public class BillSecurityInterceptor {

    @Inject
    private SecurityContext _securityContext;

    @AroundInvoke
    public Object verifyAccess(InvocationContext ic) throws Exception {
        boolean havePermission = _securityContext.isCallerInRole("REGISTER_USER") || _securityContext.isCallerInRole("ADMIN");
        if (!havePermission) {
            throw new RuntimeException("Access denied. You do not have permission to execute this method.");
        }

        return ic.proceed();
    }
}
