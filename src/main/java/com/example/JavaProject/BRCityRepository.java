package com.example.JavaProject;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "br-cities", itemResourceRel = "br-city", path="br-cities")
public interface BRCityRepository extends PagingAndSortingRepository<BRCity, Integer> {
    BRCity findById(int id);
}
