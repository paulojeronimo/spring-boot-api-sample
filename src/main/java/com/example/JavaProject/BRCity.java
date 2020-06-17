package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class BRCity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private BRState state;
    @Size(min = 2, max = 30, message="City must have between {min} and {max} characters")
    private String name;
}
