package dmit2015.bentam.assignment02.config;

import javax.annotation.sql.DataSourceDefinition;
import javax.annotation.sql.DataSourceDefinitions;
import javax.enterprise.context.ApplicationScoped;

/**
 This Config class defines the data source
 @author Benjamin Tam
 @version 1.0
 @since 2020-02-28
 */

@DataSourceDefinitions({
    @DataSourceDefinition(
            name="java:app/datasources/h2databaseDS",
            className="org.h2.jdbcx.JdbcDataSource",
            url="jdbc:h2:file:~/databases/dmit2015-assignment02-benjamintam",
            //            url="jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
            user="sa",
            password="sa"),
})

@ApplicationScoped
public class ApplicationConfig {
}

