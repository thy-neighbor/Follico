package com.neighbor.userservice.service;

import com.neighbor.userservice.entity.Hair;
import com.neighbor.userservice.entity.User;
import com.neighbor.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan("com.neighbor.userservice")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Lazy   //had to add this to fix circular reference error
    @Autowired
    private PasswordEncoder passwordEncoder;

/*
    private List<User> testUsers = new ArrayList<>(Arrays.asList(
            new User("Porsha Test", "test", "test@test.com", "chef", "picture.com", "I love cooking"),
            new User("Porsha1 Test", "test", "test1@test.com", "driver", "picture.com", "I love cooking"),
            new User("Porsha2 Test", "test", "test2@test.com", "chef", "picture.com", "I love cooking")
    ));


    public List<User> getTestUsers() {
        return testUsers;
    }
*/
    @Transactional
    public boolean isUserPresent(Integer id){
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.isPresent();
    }

    @Transactional
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Transactional
    public User getByUsername(String subjectUsername) {
        return userRepository.findByUsername(subjectUsername);
    }

    @Transactional
    public boolean createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("Create user " + user.getUsername() + " " + user.getEmail());
        return userRepository.save(user) != null;
    }

    //wrong return optional
    @Transactional
    public Optional<User> findUserById(int id) {   //we can use get to return user
        return userRepository.findById(id);
    }

    @Transactional
    public void deleteUserById(Integer userId) {
        userRepository.deleteById(userId);
    }

/*    @PostConstruct
    private void setupDefaultProduct() {

        if (userRepository.count() == 0) {//if nothing in database

            userRepository.save(new User("test", passwordEncoder.encode("test"), "test@test.com", "admin", "picture.com", "I love cooking"));
            userRepository.save(new User("chef", passwordEncoder.encode("chef"), "test1@test.com", "chef", "picture.com", "I love cooking"));
            userRepository.save(new User("driver", passwordEncoder.encode("driver"), "test2@test.com", "driver", "picture.com", "I love cooking"));
        }
    }*/
}
