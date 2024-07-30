package com.example.kinobox.repository;

import com.example.kinobox.model.UsersType;
import org.hibernate.usertype.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersTypeRepository extends JpaRepository<UsersType, Integer> {

    UsersType findByUsersType(String name);


}
