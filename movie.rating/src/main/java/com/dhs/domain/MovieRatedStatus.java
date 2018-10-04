package com.dhs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class MovieRatedStatus {

	@Id
	@GeneratedValue
	private Long id;
	private Long movieId;
	@Transient
	private String movieTitle;
	private String userId;
	@Transient
	private String userName;
	private String rating;

	public String getMovieTitle() {
		return movieTitle;
	}

	public MovieRatedStatus setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
		return this;
	}

	public Long getId() {
		return id;
	}

	public MovieRatedStatus setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getMovieId() {
		return movieId;
	}

	public MovieRatedStatus setMovieId(Long movieId) {
		this.movieId = movieId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public MovieRatedStatus setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public MovieRatedStatus setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getRating() {
		return rating;
	}

	public MovieRatedStatus setRating(String rating) {
		this.rating = rating;
		return this;
	}

}