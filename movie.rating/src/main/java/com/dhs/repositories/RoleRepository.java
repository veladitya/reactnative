package com.dhs.repositories;

import org.springframework.data.repository.CrudRepository;

import com.dhs.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}