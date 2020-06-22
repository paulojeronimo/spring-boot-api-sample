package com.example.JavaProject;

import com.example.JavaProject.enums.Gender;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerTest {
    @Test
    void customer_age_should_be_correctly_calculated() {
        final Customer customer = Customer.builder()
                .name("Paulo Jerônimo")
                .birthday(new GregorianCalendar(1974, 8, 4))
                .gender(Gender.M)
                .build();
        final LocalDate someDay = LocalDate.of(2020, 06, 21);
        assertEquals(45, customer.ageOn(someDay));
    }
}
