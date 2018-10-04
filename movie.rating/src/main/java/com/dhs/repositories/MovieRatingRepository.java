package com.dhs.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dhs.domain.MovieRatedStatus;

@RepositoryRestResource(collectionResourceRel = "movieRatings", path = "movieRatings")
public interface MovieRatingRepository extends CrudRepository<MovieRatedStatus, Long> {

	MovieRatedStatus findByMovieIdAndUserId(Long movieId, String userId);
	Long countByMovieIdAndUserId(Long movieId, String userId);
}