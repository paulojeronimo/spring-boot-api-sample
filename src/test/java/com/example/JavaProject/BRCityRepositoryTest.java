package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BRCityRepositoryTest {

    @Autowired
    private BRCityRepository brCityRepository;

    @Test
    void should_have_the_expected_number_of_cities() {
        assertEquals(Cities.total(), brCityRepository.findAll().size());
    }

    @Test
    void should_have_two_cities_named_trindade() {
        Set<BRCity> cities = brCityRepository.findByName("Trindade");
        assertEquals(2, cities.size());

        // An alternative (not so good: repetitive code)
        //boolean isFromGO = cities.stream().anyMatch(c -> c.getState() == BRState.GO);
        //boolean isFromPE = cities.stream().anyMatch(c -> c.getState() == BRState.PE);
        //assertTrue(isFromGO && isFromPE);

        // A better alternative:
        assertTrue(cities.stream()
                .map(BRCity::getState)
                .collect(Collectors.toSet())
                .containsAll(Set.of(BRState.GO, BRState.PE)));
    }

    @Test
    void should_have_the_expected_number_of_cities_by_state() {
        Cities.stream().
                forEach(i -> assertEquals(i.number, brCityRepository.findByState(BRState.valueOf(i.name())).size()));
    }

    enum Cities {
        DF(1), SP(2), RJ(3), GO(1), PE(1);
        private final int number;

        Cities(int number) {
            this.number = number;
        }

        static Stream<Cities> stream() {
            return Stream.of(Cities.values());
        }

        static int total() {
            return stream().mapToInt(i -> i.number).sum();
        }
    }
}
