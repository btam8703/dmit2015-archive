package dmit2015.bentam.assignment02.entity;

/**
 This persistence class contains information about an OscarReview
 @author Benjamin Tam
 @version 1.1
 @since 2020-03-28
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

    @Column(nullable = false, length=32)
    @NotBlank(message = "Username is required")
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


