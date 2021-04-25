package dmit2015.config;

import org.eclipse.microprofile.auth.LoginConfig;

import javax.annotation.security.DeclareRoles;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 This class contains the JAXRSConfiguration
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */


@ApplicationPath("webapi")
@LoginConfig(authMethod="MP-JWT", realmName="MP JWT Realm")
@DeclareRoles({"USER","ADMIN"})
public class JAXRSConfiguration extends Application {

}
