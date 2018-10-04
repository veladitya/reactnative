package com.dhs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dhs.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

	User findByName(String name);

}