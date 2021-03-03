package com.geekbrains.market.repositories;

import com.geekbrains.market.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends CrudRepository<Role, Long> {
	Role findOneByName(String name);
}
