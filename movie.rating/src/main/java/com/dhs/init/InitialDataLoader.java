package com.dhs.init;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.dhs.domain.Movie;
import com.dhs.domain.User;
import com.dhs.domain.Role;
import com.dhs.repositories.MovieRepository;
import com.dhs.repositories.UserRepository;
import com.dhs.repositories.RoleRepository;

//@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

	boolean alreadySetup = false;

	@Autowired
	private UserRepository personRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Override
	@Transactional
	public void onApplicationEvent(ContextRefreshedEvent event) {

		if (alreadySetup)
			return;
		Movie firstMovie = createMovieIfNotFound("Geetha Govindham", "Family Movie", "abc.jpg");
		Movie secondMovie = createMovieIfNotFound("RX 100", "Family Movie", "abc.jpg");

		List<Movie> moviesList = Arrays.asList(firstMovie, secondMovie);
		createRoleIfNotFound("DIRECTOR", moviesList);
		createRoleIfNotFound("SCREENWRITER", Arrays.asList(firstMovie));
		createRoleIfNotFound("COSTUME DESIGNER", Arrays.asList(firstMovie));
		createRoleIfNotFound("CINEMATOGRAPHER", Arrays.asList(firstMovie));
		createRoleIfNotFound("ACTORS", Arrays.asList(firstMovie));		

		Role directorRole = roleRepository.findByName("DIRECTOR");
		User user = new User();
		user.setName("Chiru");
		user.setRoles(Arrays.asList(directorRole));
		personRepository.save(user);

		alreadySetup = true;
	}

	@Transactional
	private Movie createMovieIfNotFound(String name, String tagline, String imageUrl) {

		Movie privilege = movieRepository.findByTitle(name);
		if (privilege == null) {
			privilege = new Movie();
			movieRepository.save(privilege.setTitle(name).setImageUrl(imageUrl).setTagline(tagline));
		}
		return privilege;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Collection<Movie> movies) {

		Role role = roleRepository.findByName(name);
		if (role == null) {
			role = new Role();
			roleRepository.save(role);
		}
		return role;
	}
}