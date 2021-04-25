package dmit2015.bentam.assignment05.entity;


import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.G2D;

/**
 This entity class contains a single covid case in an area
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-08
 */

@Entity
@Table(name = "current_cases_by_local_geographic_area")
@Getter
@Setter
public class CurrentCasesByLocalGeographicArea implements Serializable {

    @Id
    @NotBlank(message = "Location name is required")
    @Column(name = "location_name", nullable = false, length = 128)
    private String locationName;

    @NotNull(message = "Total cases are required")
    @Column(name = "total_cases",nullable = false)
    private int totalCases;

    @NotNull(message = "Active cases are required")
    @Column(name = "active_cases", nullable = false)
    private int activeCases;

    @NotNull(message = "Recovered cases are required")
    @Column(name = "recovered_cases", nullable = false)
    private int recoveredCases;

    @NotNull(message = "Deaths are required")
    @Column(name = "deaths", nullable = false)
    private int deaths;

    @JsonbTransient
    @NotNull(message = "Polygon is required")
    @Column(name = "polygon", nullable = false)
    private Polygon polygon;
}
