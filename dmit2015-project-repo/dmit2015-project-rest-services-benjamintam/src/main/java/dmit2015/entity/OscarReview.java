package dmit2015.entity;

/**
 This persistence class contains information about an OscarReview
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="OscarReview")
public class OscarReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length=32)
    @NotBlank(message = "Category is required")
    private String category; //film, actor, actress, editing, effects

    @Column(nullable = false, length=32)
    @NotBlank(message = "Nominee is required")
    private String nominee;

    @Column(nullable = false, length=320)
    @NotBlank(message = "Review is required")
    private String review;

    @Column(nullable = true, length=32)
    private String username;

    @Column(nullable = false)
    private LocalDateTime createdDateTime;

    @Column(nullable = false)
    private LocalDateTime lastModifiedDateTime;

    @Version
    private Integer version;

    @PrePersist
    private void beforePersist() {
        createdDateTime = LocalDateTime.now();
        lastModifiedDateTime = createdDateTime;
    }

    @PreUpdate
    private void beforeUpdate() {
        lastModifiedDateTime = LocalDateTime.now();
    }
}


