package dmit2015.assessment01.bentam.config;

/**
 This config class identifies the datasource
 @author Benjamin Tam
 @version 1.0
 @since 2021-02-12
 */

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;

@DataSourceDefinitions({
		@DataSourceDefinition(
				name="java:app/datasources/h2databaseDS",
				className="org.h2.jdbcx.JdbcDataSource",
				url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
				user="sa",
				password="sa"),

})


@ApplicationScoped
public class ApplicationConfig {

}