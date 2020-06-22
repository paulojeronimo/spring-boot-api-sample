package com.example.JavaProject;

import com.example.JavaProject.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(nullable = false, length = 40)
    @Size(min = 2, max = 40)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "char(1)")
    private Gender gender;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Calendar birthday;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id",
        foreignKey = @ForeignKey(name = "fk_city"))
    private BRCity city;

    @Transient
    private int age;

    @PostLoad
    @PostUpdate
    private void updateAge() {
        this.age = age();
    }

    public int ageOn(LocalDate date) {
        return Period.between(LocalDate.ofInstant(
                birthday.toInstant(), ZoneId.systemDefault()),
                date).getYears();
    }

    public int age() {
        return ageOn(LocalDate.now());
    }
}
