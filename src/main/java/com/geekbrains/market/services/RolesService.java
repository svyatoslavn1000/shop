package com.geekbrains.market.services;

import com.geekbrains.market.entities.Role;
import com.geekbrains.market.repositories.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;

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
