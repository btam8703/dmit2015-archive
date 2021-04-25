package dmit2015.bentam.assignment05.batch;

import dmit2015.bentam.assignment05.entity.CurrentCasesByLocalGeographicArea;
import dmit2015.bentam.assignment05.repository.CurrentCasesByLocalGeographicAreaRepository;

import javax.annotation.Resource;
import javax.batch.api.AbstractBatchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Logger;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.codec.Wkt;
import org.geolatte.geom.crs.CoordinateReferenceSystems;

/**
 This batchlet class handles the importing of data from the csv file
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Named
public class CurrentCasesByLocalGeographicAreaBatchlet extends AbstractBatchlet {

    @Inject
    private JobContext _jobContext;

    @Inject
    private CurrentCasesByLocalGeographicAreaRepository _repository;

    @Inject
    private Logger _logger;

    @Resource(name="dmit2015.bentam.assignment05.config.filepath")
    private String filepath;

    @Transactional
    @Override
    public String process() throws Exception {
        String jobStatus = "FAILED";

        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(filepath).toFile()))) {
            String lineText;
            final String delimiter = ",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)";
            reader.readLine();
            while ((lineText = reader.readLine()) != null) {
                String[] values = lineText.split(delimiter, -1);

                CurrentCasesByLocalGeographicArea currentCurrentCasesByLocalGeographicArea = new CurrentCasesByLocalGeographicArea();
                currentCurrentCasesByLocalGeographicArea.setLocationName(values[0]);
                currentCurrentCasesByLocalGeographicArea.setTotalCases(Integer.parseInt(values[1]));
                currentCurrentCasesByLocalGeographicArea.setActiveCases(Integer.parseInt(values[2]));
                currentCurrentCasesByLocalGeographicArea.setRecoveredCases(Integer.parseInt(values[3]));
                currentCurrentCasesByLocalGeographicArea.setDeaths(Integer.parseInt(values[4]));
                Polygon<G2D> polygon = (Polygon<G2D>) Wkt.fromWkt(values[5].replaceAll("\"",""),
                        CoordinateReferenceSystems.WGS84);
                currentCurrentCasesByLocalGeographicArea.setPolygon(polygon);
                _repository.create(currentCurrentCasesByLocalGeographicArea);
            }
            jobStatus = "COMPLETED";
        } catch (Exception ex) {
            ex.printStackTrace();
            _logger.fine("Batchlet failed with exception: " + ex.getMessage());
        }

        return jobStatus;
    }
}