package com.geekbrains.decembermarket.services;

import com.geekbrains.decembermarket.entities.Role;
import com.geekbrains.decembermarket.entities.User;
import com.geekbrains.decembermarket.repositories.RolesRepository;
import com.geekbrains.decembermarket.repositories.UserRepository;
import com.geekbrains.decembermarket.utils.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private RolesService rolesService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RolesRepository rolesRepository) {
        this.rolesService = rolesService;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User findByPhone(String phone) {
        return userRepository.findOneByPhone(phone);
    }

    public User getAnonymousUser() {
        return userRepository.findOneByPhone("anonymous");
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByPhone(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public boolean isUserExist(String phone) {
        return userRepository.existsByPhone(phone);
    }

    @Transactional
    public User save(SystemUser systemUser) {
        User user = new User();

        if (findByPhone(systemUser.getPhone()) != null) {
            throw new RuntimeException("User with phone " + systemUser.getPhone() + " is already exist");
        }
        user.setPhone(systemUser.getPhone());
        if (systemUser.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(systemUser.getPassword()));
        }
        user.setFirstName(systemUser.getFirstName());
        user.setLastName(systemUser.getLastName());
        user.setEmail(systemUser.getEmail());
        user.setRoles(Arrays.asList(rolesService.findByName("ROLE_CUSTOMER")));
        return userRepository.save(user);
    }
}