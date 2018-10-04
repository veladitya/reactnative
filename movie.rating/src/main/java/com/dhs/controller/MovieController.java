package com.dhs.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dhs.domain.Movie;
import com.dhs.domain.MovieRatedStatus;
import com.dhs.services.MovieService;

@RestController
@RequestMapping("/api/")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@GetMapping("/uuid/")
	public String getUid() {
		return "{\"uid\":\""+UUID.randomUUID().toString()+"\"}";
	}
	
	@GetMapping("/movies/")
	public List<Movie> getMovies() {
		return movieService.getMovies();
	}
	
	@GetMapping("/movie/{movieId}/user/{userId}/rating/")
	public MovieRatedStatus getUserRating(@PathVariable("movieId") Long movieId, @PathVariable("userId") String userId) {
		MovieRatedStatus movieratedStatus = movieService.getMovieRatingStatus(movieId, userId);
		if(movieratedStatus == null) {
			return  new MovieRatedStatus().setMovieId(movieId).setUserId(userId);
		}
		return movieService.getMovieRatingStatus(movieId, userId);
	}
	
	@GetMapping("/movie/ratings/{userId}/")
	public List<MovieRatedStatus> getUserRatings(@PathVariable("userId") String userId) {
		return movieService.getMovieRatings(userId);
	}
	
	@PostMapping("/movie/rating/")
	public Movie addUserRatings(@RequestBody MovieRatedStatus movieRatings) {
		return movieService.addUserRatings(movieRatings);
	}
}