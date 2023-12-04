package com.example.userservice.services;

import com.example.userservice.entities.User;
import com.example.userservice.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User create(User user) {
        User createdUser = new User();
        BeanUtils.copyProperties(user, createdUser);
        return userRepository.save(createdUser);
    }

    public User update(User input) {
        User customer = userRepository.findById(input.getId())
                .orElseThrow(EntityNotFoundException::new);
        BeanUtils.copyProperties(input, customer);
        return userRepository.save(customer);
    }

    public User delete(Long id) {
        User customer = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepository.delete(customer);
        return customer;
    }

    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<User> listAll() {
        return userRepository.findAll(Sort.by("lastName", "firstName"));
    }
}
