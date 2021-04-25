package dmit2504.entity;

/*
    This persistence class contains information about a Jitter (Twitter clone):
    ID
    dateTime
    username
    message
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="Jitters")
public class Jitter implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false, length =32)
    private String username;

    @Column(nullable = false, length = 280)
    private String message;

}
