package com.geekbrains.decembermarket.services;

import com.geekbrains.decembermarket.entities.Role;
import com.geekbrains.decembermarket.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolesService {
    private RolesRepository rolesRepository;

    @Autowired
    public void setRoleRepository(RolesRepository rolesRepository){
        this.rolesRepository = rolesRepository;
    }

    public Role findByName(String name){
        return rolesRepository.findOneByName(name);
    }
}
