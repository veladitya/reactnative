package com.dhs.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role {

	@Id
	@GeneratedValue
	private Long id;
	private String name;

	@ManyToMany(mappedBy = "roles")
	private Collection<User> persons;

	public Collection<User> getPersons() {
		return persons;
	}

	public Role setPersons(Collection<User> persons) {
		this.persons = persons;
		return this;
	}

	public Long getId() {
		return id;
	}

	public Role setId(Long id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Role setName(String name) {
		this.name = name;
		return this;
	}

}