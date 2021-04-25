package dmit2015.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 This class contains the CallerUser entity
 @author Benjamin Tam
 @version 1.0
 @since 2021-04-21
 */

@Entity
@Data
public class CallerUser implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Size(min = 3, max = 32, message = "Enter a username that contains {min} to {max} characters")
    @Column(length = 32, unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "CallerGroup", joinColumns = {@JoinColumn(name = "username")})
    @Column(name = "groupname", nullable = false)
    private Set<String> groups = new HashSet<>();

    @Version
    private Integer version;
}
