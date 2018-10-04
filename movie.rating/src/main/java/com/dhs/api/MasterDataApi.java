package com.dhs.api;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.dhs.domain.Movie;
import com.dhs.domain.MovieRatedStatus;
import com.dhs.domain.Role;
import com.dhs.domain.User;

@ConfigurationProperties(prefix = "moviesapi")
public class MasterDataApi {

	private List<Role> roles;
	private List<User> persons;
	private List<Movie> movies;
	private List<MovieRatedStatus> movieRatings;
	
	
	
	public List<MovieRatedStatus> getMovieRatings() {
		return movieRatings;
	}

	public MasterDataApi setMovieRatings(List<MovieRatedStatus> movieRatings) {
		this.movieRatings = movieRatings;
		return this;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public MasterDataApi setRoles(List<Role> roles) {
		this.roles = roles;
		return this;
	}

	public List<User> getPersons() {
		return persons;
	}

	public MasterDataApi setPersons(List<User> persons) {
		this.persons = persons;
		return this;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public MasterDataApi setMovies(List<Movie> movies) {
		this.movies = movies;
		return this;
	}
}
