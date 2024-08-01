package com.example.kinobox.service;

import com.example.kinobox.model.Admin;
import com.example.kinobox.model.Users;
import com.example.kinobox.model.UsersType;
import com.example.kinobox.model.Watchers;
import com.example.kinobox.repository.AdminRepository;
import com.example.kinobox.repository.UsersRepository;
import com.example.kinobox.repository.UsersTypeRepository;
import com.example.kinobox.repository.WatchesRepository;
import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final WatchesRepository watchesRepository;
    private final AdminRepository adminRepository;

    @Autowired
    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, UsersTypeRepository usersTypeRepository, WatchesRepository watchesRepository, AdminRepository adminRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.usersTypeRepository = usersTypeRepository;
        this.watchesRepository = watchesRepository;
        this.adminRepository = adminRepository;
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
            Watchers watchersProfile = new Watchers();
        }else{
            Admin admin = new Admin();
        }
        return savedUser;

    }
    public Optional<Users> getUserByEmail(String email){
        return usersRepository.findByEmail(email);
    }
    public Object getCurrentUserProfile(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            Users users = usersRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("colud not found user"));
            int userId  = users.getId();
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
                Watchers watchersProfile = watchesRepository.findById(userId).orElse(new Watchers());
                return watchersProfile;
            }else {
                Admin adminProfile = adminRepository.findById(userId).orElse(new Admin());
                return adminProfile;
            }
        }
        return null;
    }
}
