package com.geekbrains.decembermarket.repositories;

import com.geekbrains.decembermarket.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
