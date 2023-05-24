package com.example.service;

import com.example.models.UserRole;
import com.example.dto.PersonDto;
import com.example.models.Person;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Person getUserById(Long id) {
        return userRepository.findById(id).isPresent() ? userRepository.findById(id).get() : null;
    }

    public Person getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public boolean saveUser(PersonDto personDto) {
        Set<UserRole> roles = new HashSet<>();
        roles.add(UserRole.USER);
        Person person = new Person(personDto.getUsername(), personDto.getPassword(), roles);
        userRepository.save(person);
        return true;
    }


    public boolean deleteUserById(Long id) {
        userRepository.deleteById(id);
        return true;
    }
}
