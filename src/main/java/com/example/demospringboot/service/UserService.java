package com.example.demospringboot.service;

import com.example.demospringboot.dto.request.UserCreationRequest;
import com.example.demospringboot.dto.request.UserUpdateRequest;
import com.example.demospringboot.entity.User;
import com.example.demospringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(UserCreationRequest request){
        User user = new User();

        if (userRepository.existsByUsername(request.getUsername()))
            throw new RuntimeException("User existed");

        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public User updateUser(String userId, UserUpdateRequest request){
        User user = getUser(userId);

        user.setPassword(request.getPassword());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        return userRepository.save(user);
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

    public List<User> getUsers(){
//        return userRepository.findAll();
        return userRepository.getAllUsers();
    }

    public User getUser(String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
