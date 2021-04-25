package dmit2015.bentam.assignment05.ejb;

import dmit2015.bentam.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.bentam.assignment05.resource.BatchJobResource;

import javax.annotation.Resource;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 This bean class contains the automatic timers
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Singleton
@Startup
public class AutomaticTimersBean {

	private Logger _logger = Logger.getLogger(AutomaticTimersBean.class.getName());

	@Schedule(minute= "49", hour="9", info="Test timer that downloads data every minute")
	public void dmit2015MinuteTimerTest(Timer timer) {
		sendDownloadRequest();
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long jobId = jobOperator.start("batchletCurrentCasesByLocalGeographicArea", null);
		System.out.println("Minute timer has executed");
	}

	@Schedule(hour="16", dayOfWeek = "Mon-Fri", info="Download data at 4PM every weekday")
	public void dmit2015DownloadData(Timer timer) {
		sendDownloadRequest();
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long jobId = jobOperator.start("batchletCurrentCasesByLocalGeographicArea", null);
		System.out.println("4PM Timer has executed");
	}

	@Schedule(second="*", minute="*", hour="*", info="Test timer that sends a message every second")
	public void dmit2015SecondTimerTest(Timer timer) {
		System.out.println("Timer for each second has executed");
	}

	private void sendDownloadRequest () {
		DownloadFileController newController = new DownloadFileController();
		newController.setDownloadUri("https://data.edmonton.ca/api/views/ix8f-s9xp/rows.csv?accessType=DOWNLOAD");
		newController.setDownloadDirectory("/home/user2015/Downloads/");
		newController.redownloadData();
	}

}