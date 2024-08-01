package com.example.kinobox.service;

import com.example.kinobox.model.Users;
import com.example.kinobox.model.UsersType;
import com.example.kinobox.repository.UsersRepository;
import com.example.kinobox.repository.UsersTypeRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, UsersTypeRepository usersTypeRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersTypeRepository = usersTypeRepository;
    }

    public Users registerNewUser(Users user) {
        UsersType userType = usersTypeRepository.findByUserTypeName("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setUserTypeId(userType);
        user.setActive(true);
        int userTyperId = user.getUserTypeId().getUserTypeId();
        Users savedUser =  usersRepository.save(user);
        if(userTyperId == 1 ){

        }
        return usersRepository.save(user);

    }
    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
}
