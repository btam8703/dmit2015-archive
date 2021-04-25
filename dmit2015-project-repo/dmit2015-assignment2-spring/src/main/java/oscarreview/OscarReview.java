package oscarreview;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.*;


/**
 This class contains the OscarReview entity
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@Entity
class OscarReview {
    private @Id @GeneratedValue Long id;
    private String category;
    private String nominee;
    private String review;
    private String username;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;


    OscarReview() {}

    OscarReview(String category, String nominee, String review, String username) {
        this.category = category;
        this.nominee = nominee;
        this.review = review;
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNominee() {
        return this.nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }

    public String getReview() {
        return this.review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) { this.username = username; }

    public LocalDateTime getCreatedDateTime() { return this.createdDateTime; }

    public LocalDateTime getLastModifiedDateTime() { return this.lastModifiedDateTime; }

    @PrePersist
    private void beforePersist() {
        createdDateTime = LocalDateTime.now();
        lastModifiedDateTime = createdDateTime;
    }

    @PreUpdate
    private void beforeUpdate() {
        lastModifiedDateTime = LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof OscarReview))
            return false;
        OscarReview review = (OscarReview) o;
        return Objects.equals(this.id, review.id) && Objects.equals(this.category, review.category)
                && Objects.equals(this.nominee, review.nominee) && Objects.equals(this.review, review.review);
                //&& Objects.equals(this.createdDateTime, review.createdDateTime) && Objects.equals(this.lastModifiedDateTime, review.lastModifiedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.category, this.nominee, this.review, this.username/*, this.createdDateTime, this.lastModifiedDateTime*/);
    }

    @Override
    public String toString() {
        return "OscarReview{" + "id=" + this.id + ", category='" + this.category + '\'' + ", nominee='" + this.nominee
                + '\'' + ", review='" + this.review + '\'' + ", username='" + this.username + '\'' + ", createdDateTime='" + this.createdDateTime + '\'' + ", lastModifiedDateTime='" + this.lastModifiedDateTime + '\'' + '}';
    }


}
