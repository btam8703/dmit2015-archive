package dmit2015.assessment01.bentam.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 This class contains info about an animal
 @author Benjamin Tam
 @version 1.0
 @since 2021-02-12
 */

@Data
@Entity
@Table(name="bentam_animal")
public class Animal implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false, length=20)
    private String type;
}
