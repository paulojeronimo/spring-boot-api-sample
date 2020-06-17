package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Data
@Entity
public class BRCity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "State must be a valid Brazilian state")
    private BRState state;
    @NotBlank
    private String name;
}
