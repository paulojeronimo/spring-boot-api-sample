package com.example.JavaProject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class CustomerRepositoryTest {
    @Autowired
    private BRCityRepository brCityRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void should_have_the_expected_number_of_customers() {
        assertEquals(Customers.total(), customerRepository.findAll().size());
    }

    @Test
    void should_have_the_expected_number_of_customers_by_city() {
        Customers.stream().
            forEach(i -> assertEquals(i.number, customerRepository.findByCityId(i.id).size()));
    }

    @Test
    void should_get_all_customers_ages_correctly() {
        customerRepository.findAll().stream()
            .map(c -> c.ageOn(LocalDate.of(2020, 06, 21)))
            .collect(Collectors.toSet())
            .containsAll(Set.of(45, 42, 21, 20));
    }

    enum Customers {
        Brasilia(1, 1), Sao_Paulo(2, 2), RioDeJaneiro(3, 1);
        final int id;
        final int number;

        Customers(int id, int number) {
            this.id = id;
            this.number = number;
        }

        static Stream<Customers> stream() {
            return Stream.of(Customers.values());
        }

        static int total() {
            return stream().mapToInt(i -> i.number).sum();
        }
    }
}
