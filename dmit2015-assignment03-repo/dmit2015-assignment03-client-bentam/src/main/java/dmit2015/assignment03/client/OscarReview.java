package dmit2015.assignment03.client;

/**
 This persistence class contains information about an OscarReview
 @author Benjamin Tam
 @version 1.1
 @since 2020-02-28
 */

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class OscarReview implements Serializable {

    private Long id;

    private String category; //film, actor, actress, editing, effects

    private String nominee;

    private String review;

    private String username;
}


