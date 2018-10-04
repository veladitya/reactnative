package com.dhs.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhs.domain.Movie;
import com.dhs.domain.MovieRatedStatus;
import com.dhs.domain.Role;
import com.dhs.domain.User;
import com.dhs.repositories.MovieRatingRepository;
import com.dhs.repositories.MovieRepository;
import com.dhs.repositories.UserRepository;
import com.dhs.repositories.RoleRepository;

@Service
public class MovieService {

	private final static Logger LOG = LoggerFactory.getLogger(MovieService.class);

	@Autowired
	private MovieRepository movieRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private MovieRatingRepository movieRatingRepository;

	public Movie insertOrUpdate(Movie movie) {
		Movie existing = movieRepository.findByTitle(movie.getTitle());
		if (existing == null) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Inserting new movie...");
			}
			return movieRepository.save(movie);
		}
		existing.setImageUrl(movie.getImageUrl());
		existing.setTagline(movie.getTagline());
		if (LOG.isInfoEnabled()) {
			LOG.info("Updating exisitng movie...");
		}
		return movieRepository.save(existing);
	}

	public User insertOrUpdate(User person) {
		User existing = userRepository.findByName(person.getName());
		if (existing == null) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Inserting new User with Role: COMMON MAN/PEOPLE...");
			}
			person.setRoles(Arrays.asList(roleRepository.findByName("COMMON PEOPLE")));
			return userRepository.save(person);
		}
		return existing;
	}

	public Role insertOrUpdate(Role role) {
		Role existing = roleRepository.findByName(role.getName());
		if (existing == null) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Inserting new Role...");
			}
			return roleRepository.save(role);
		}
		return existing;
	}

	public MovieRatedStatus insertOrUpdate(MovieRatedStatus movieRating) {
		if (movieRating.getMovieId() == null) {
			movieRating.setMovieId(getMovie(movieRating.getMovieTitle()).getId());
		}

		if (movieRating.getUserId() == null) {
			movieRating.setUserId(getUser(movieRating.getUserName()).getId());
		}
		if (LOG.isInfoEnabled()) {
			LOG.info("Inserting new MovieRatedStatus...");
		}
		return movieRatingRepository.save(movieRating);
	}

	public Movie getMovie(String title) {
		return movieRepository.findByTitle(title);
	}

	public Movie getMovie(Long id) {
		Optional<Movie> optional = movieRepository.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public User getUser(String name) {
		return userRepository.findByName(name);
	}

	public List<Movie> getMovies() {
		return (List<Movie>) movieRepository.findAll();
	}

	public MovieRatedStatus getMovieRatingStatus(Long movieId, String userId) {
		return movieRatingRepository.findByMovieIdAndUserId(movieId, userId);
	}

	public List<MovieRatedStatus> getMovieRatings(String userId) {
		List<MovieRatedStatus> movieRatings = null;
		List<Movie> allMovies = getMovies();
		if (allMovies != null && !allMovies.isEmpty()) {
			MovieRatedStatus movieRatedStatus = null;
			movieRatings = new ArrayList<>();
			for (Movie m : allMovies) {
				movieRatedStatus = getMovieRatingStatus(m.getId(), userId);
				if(movieRatedStatus != null)
					movieRatings.add(movieRatedStatus);
			}
		}
		return movieRatings;
	}

	public Movie addUserRatings(MovieRatedStatus movieRatings) {
		Boolean isValid = isValidUserRating(movieRatings);
		if (isValid) {
			movieRatings = insertOrUpdate(movieRatings);
		}

		Movie movie = getMovie(movieRatings.getMovieId());
		if (movie != null && isValid) {
			if (movieRatings.getRating().equalsIgnoreCase("HIT")) {
				movie.setHitRate(movie.getHitRate() + 1);
			}

			if (movieRatings.getRating().equalsIgnoreCase("FLOP")) {
				movie.setFlopRate(movie.getFlopRate() + 1);
			}

			return insertOrUpdate(movie);
		}
		return movie;
	}

	private Boolean isValidUserRating(MovieRatedStatus movieRatings) {
		return movieRatingRepository.countByMovieIdAndUserId(movieRatings.getMovieId(), movieRatings.getUserId()) <= 0;
	}
}