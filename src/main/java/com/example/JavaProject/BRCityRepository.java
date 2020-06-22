package com.example.JavaProject;

import com.example.JavaProject.enums.BRState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Set;

@RepositoryRestResource(collectionResourceRel = "br-cities", itemResourceRel = "br-city", path = "br-cities")
public interface BRCityRepository extends JpaRepository<BRCity, Integer> {
    Set<BRCity> findByName(String name);

    @Query(value = "select c from BRCity c where c.state = :state")
    Set<BRCity> findByState(@Param("state") BRState state);
}
