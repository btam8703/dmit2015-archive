package dmit2015.bentam.assignment05.resource;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Set;

/**
 This resource class handles batch jobs
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Path("batch-jobs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BatchJobResource {

    @POST
    @Path("{filename}")
    public Response startBatchJob(@PathParam("filename") String jobXMLName, @Context UriInfo uriInfo) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();

        try {
            long jobId = jobOperator.start(jobXMLName, null);

            URI location = uriInfo.getAbsolutePathBuilder()
                    .path(jobId + "")
                    .build();

            return Response
                    .created(location)
                    .build();
        } catch (JobStartException ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).entity(ex.getMessage()).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }

    @GET
    @Path("{id}")
    public Response getBatchStatus(@PathParam("id") Long jobId) {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        try {
            JobExecution jobExecution = jobOperator.getJobExecution(jobId);
            String jobStatus = jobExecution.getBatchStatus().toString();
            return Response.ok(jobStatus).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("jobnames")
    public Response getJobNames() {
        JobOperator jobOperator = BatchRuntime.getJobOperator();
        try {
            Set<String> jobNames = jobOperator.getJobNames();
            return Response.ok(jobNames).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.serverError().entity(ex.getMessage()).build();
        }
    }

}