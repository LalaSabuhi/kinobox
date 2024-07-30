package com.example.kinobox.service;

import com.example.kinobox.model.Users;
import com.example.kinobox.model.UsersType;
import com.example.kinobox.repository.UsersRepository;
import com.example.kinobox.repository.UsersTypeRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final UsersTypeRepository usersTypeRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, UsersTypeRepository usersTypeRepository) {
        this.usersRepository = usersRepository;
        this.usersTypeRepository = usersTypeRepository;
    }
    public void getUserType(Users user) {
        UsersType userType = usersTypeRepository.findByUsersType("user");
        if (userType == null) {
            throw new IllegalArgumentException("Role 'user' not found");
        }

    }

}
