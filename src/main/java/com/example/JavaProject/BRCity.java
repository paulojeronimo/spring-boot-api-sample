package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"name", "state"})
})
public class BRCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Convert(converter = BRStateJPAConverter.class)
    @Column(nullable = false, length = 2)
    private BRState state;
    @NotEmpty
    @Size(min = 3, max = 30, message="City must have between {min} and {max} characters")
    @Column(nullable = false, length = 30)
    private String name;
}
