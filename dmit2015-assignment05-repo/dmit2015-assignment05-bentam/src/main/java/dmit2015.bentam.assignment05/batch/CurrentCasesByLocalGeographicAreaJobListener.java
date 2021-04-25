package dmit2015.bentam.assignment05.batch;

import dmit2015.bentam.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.batch.api.listener.AbstractJobListener;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

/**
 This joblistener class handles the listening of the batchlet
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Named
public class CurrentCasesByLocalGeographicAreaJobListener extends AbstractJobListener {

    @Inject
    private JobContext _jobContext;

    @Inject
    private Logger _logger;

    private long startTime;

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;

    @Override
    public void beforeJob() throws Exception {
        _logger.info(_jobContext.getJobName() + " beforeJob");
        startTime = System.currentTimeMillis();
        _repository.deleteAll();

    }

    @Override
    public void afterJob() throws Exception {
        _logger.info(_jobContext.getJobName() + "afterJob");
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        String message = _jobContext.getJobName() + " completed in " + duration + " milliseconds";
        _logger.info(message);

    }

}