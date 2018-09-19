package com.example.vaadinexample.service.login;

import com.example.vaadinexample.model.User;
import com.example.vaadinexample.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegisterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void save(String username, String password) {
        User user = new User();
        user.setUsername(username);
        String encodedPassword = passwordEncoder.encode(password);
        log.info("Password: "+password);
        log.info("Password: "+encodedPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);

    }
}
