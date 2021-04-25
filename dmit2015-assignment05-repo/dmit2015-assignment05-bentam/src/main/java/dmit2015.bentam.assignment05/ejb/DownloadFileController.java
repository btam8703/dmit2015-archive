package dmit2015.bentam.assignment05.ejb;

import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Messages;

import javax.ejb.Timer;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 This controller class handles the downloading of the csv file
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

public class DownloadFileController {

    @NotBlank(message = "Download URI field value is required.")
    @Getter @Setter
    private String downloadUri;

    @NotBlank(message = "Download Directory field value is required.")
    @Getter @Setter
    private String downloadDirectory;

    private Logger _logger = Logger.getLogger(DownloadFileController.class.getName());

    public void redownloadData() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(downloadUri))
                .build();

        Path downloadPath = Path.of(downloadDirectory);
        try {
            HttpResponse<Path> response = client.send(request,
                    HttpResponse.BodyHandlers.ofFileDownload(downloadPath, StandardOpenOption.CREATE, StandardOpenOption.WRITE));
            _logger.info("Finished download file to " + response.body());
        } catch (Exception e) {
            _logger.fine("Error downloading file. " + e.getMessage());
            e.printStackTrace();
        }
    }
}