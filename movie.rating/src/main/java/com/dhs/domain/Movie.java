package com.dhs.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "title" }))
public class Movie {

	@Id
	@GeneratedValue
	private Long id;
	private String title;
	private String tagline;
	private String imageUrl;
	private Integer hitRate;
	private Integer flopRate;
	
	public Integer getHitRate() {
		return hitRate;
	}

	public Movie setHitRate(Integer hitRate) {
		this.hitRate = hitRate;
		return this;
	}

	public Integer getFlopRate() {
		return flopRate;
	}

	public Movie setFlopRate(Integer flopRate) {
		this.flopRate = flopRate;
		return this;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getTagline() {
		return tagline;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public Movie setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
		return this;
	}

	public Movie setTitle(String title) {
		this.title = title;
		return this;
	}

	public Movie setTagline(String tagline) {
		this.tagline = tagline;
		return this;
	}
}