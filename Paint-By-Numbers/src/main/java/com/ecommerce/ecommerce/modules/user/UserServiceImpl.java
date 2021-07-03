package com.ecommerce.ecommerce.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    // return all users from db
    public Iterable<User> all(){
        return userRepository.findAll();
    }

    // return one uesr by id
    public User read(@PathVariable(value = "id") UUID id){
        return userRepository.findById(id).orElseThrow();
    }

    // create an user and save to db
    public User create(User u){
//        System.out.println(u.toString());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        u.setPassword(encoder.encode(u.getPassword()));
        u.setRole("ROLE_USER");
        u.setEnabled(true);
        userRepository.save(u);
        return u;
    }

    // update an existing user
    public User update(@PathVariable(value = "id") UUID id, User o){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User c = userRepository.findById(id).orElseThrow();
        c.setEnabled(o.getEnabled());
        c.setDateOfBirth(o.getDateOfBirth());
        c.setFullName(o.getFullName());
        c.setPhone(o.getPhone());
        c.setUsername(o.getUsername());
        c.setEmail(o.getEmail());
        c.setRole(o.getRole());
        if (o.getPassword() != "")
            c.setPassword(encoder.encode(o.getPassword()));
        userRepository.save(c);
        return c;
    }

    // find a user by name
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    // delete an user by id
    public void delete(@PathVariable(value = "id") UUID id){
        userRepository.deleteById(id);
    }

    // get authenticated user at the moment
    public User getCurrent(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(auth.getName());
    }

    // check if user already exists in db
    public boolean isUserAlreadyPresent(User user) {

        boolean isUserAlreadyExists = false;

        User existingUser = userRepository.findByUsername(user.getUsername());

        // If user is found in database, then user already exists.
        if(existingUser != null){
            isUserAlreadyExists = true;
        }
        return isUserAlreadyExists;
    }
}
