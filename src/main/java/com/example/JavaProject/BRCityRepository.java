package com.example.JavaProject;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "city", path="city")
public interface BRCityRepository extends PagingAndSortingRepository<BRCity, Integer> {
    BRCity findById(int id);
}
