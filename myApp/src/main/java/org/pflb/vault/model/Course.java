package org.pflb.vault.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "COURSE")
public class Course implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min = 3, max = 50, message = "name must be longer than 3 and less than 40 characters")
    @Column(name = "name")
    private String name;

    @Column(name = "dateStart")
    private Date dateStart;

    @Column(name = "dateEnd")
    private Date dateEnd;

    @Column(name = "numOfDays")
    private Long numOfDays;

}
