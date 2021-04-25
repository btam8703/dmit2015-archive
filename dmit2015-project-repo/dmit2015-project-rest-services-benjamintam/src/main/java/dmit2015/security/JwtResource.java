package dmit2015.security;

import dmit2015.entity.CallerUser;
import dmit2015.repository.CallerUserRepository;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.Optional;
import java.util.Set;

/**
 curl -k -i -X POST https://localhost:8443/dmit2015-project-rest-services-benjamintam/webapi/jwt/jsonLogin \
	-d '{"username":"usermovie","password":"Password2015"}' \
	-H 'Content-Type:application/json'

 curl -k -i -X POST https://localhost:8443/dmit2015-project-rest-services-benjamintam/webapi/jwt/jsonLogin \
	-d '{"username":"adminmovie","password":"Password2015"}' \
	-H 'Content-Type:application/json'
 */

/**
 This class contains the resource for any JWT
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@RequestScoped
@Path("jwt")
public class JwtResource {

	@Inject
	private CallerUserRepository callerUserRepository;	// for accessing our database of users

	@Inject
	private Pbkdf2PasswordHash passwordHash;	// for hashing the plain text password to cipher text

	@Inject
	private JsonWebToken callerPrincipal;		// CDI managed bean must be @RequestScoped to inject JWT token


	@Path("formLogin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response formLogin(
			@FormParam("j_username") String username,
			@FormParam("j_password") String password,
			@Context ServletContext servletContext) {
		JsonObject credential = Json.createObjectBuilder()
				.add("username", username)
				.add("password", password)
				.build();
		return jsonLogin(credential, servletContext);
	}

	@POST
	@Path("jsonLogin")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response jsonLogin(JsonObject credential, @Context ServletContext servletContext) {
		String username = credential.getString("username");
		String password = credential.getString("password");
		String privateKeyPath = servletContext.getInitParameter("jwt.privatekey.filepath");

		Optional<CallerUser> optionalCallerUser = callerUserRepository.findById(username);
		if (optionalCallerUser.isPresent()) {
			CallerUser existingCallerUser = optionalCallerUser.get();
			if (passwordHash.verify(password.toCharArray(), existingCallerUser.getPassword())) {
				String[] groups = existingCallerUser.getGroups().toArray(String[]::new);
				try {
					String token = TokenUtil.generateJWT(privateKeyPath, username, groups);
					return Response.ok(token).build();
				} catch (Exception e) {
					e.printStackTrace();
					return Response.serverError().entity(e.getMessage()).build();
				}
			}
		}

		String message = "Incorrect username and/or password.";
		return Response.status(Status.BAD_REQUEST).entity(message).build();
	}


}
