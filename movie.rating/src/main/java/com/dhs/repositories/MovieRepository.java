package com.dhs.repositories;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dhs.domain.Movie;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends CrudRepository<Movie, Long> {

	Movie findByTitle(@Param("title") String title);

	Collection<Movie> findByTitleLike(@Param("title") String title);	
}