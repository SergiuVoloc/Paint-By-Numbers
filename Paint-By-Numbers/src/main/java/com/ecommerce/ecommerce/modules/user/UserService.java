package com.ecommerce.ecommerce.modules.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Iterable<User> all(){
        return userRepository.findAll();
    }

    public User read(@PathVariable(value = "id") UUID id){
        return userRepository.findById(id).orElseThrow();
    }

    public User create(User u){
        System.out.println(u.toString());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        u.setPassword(encoder.encode(u.getPassword()));
        userRepository.save(u);
        return u;
    }

    public User update(@PathVariable(value = "id") UUID id, User o){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        User c = userRepository.findById(id).orElseThrow();
        c.setAddresses(o.getAddresses());
        c.setEnabled(o.getEnabled());
        c.setDateOfBirth(o.getDateOfBirth());
        c.setFullName(o.getFullName());
        c.setPhone(o.getPhone());
        c.setUsername(o.getUsername());
        c.setRole(o.getRole());
        if (o.getPassword() != "")
            c.setPassword(encoder.encode(o.getPassword()));
        userRepository.save(c);
        return c;
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void delete(@PathVariable(value = "id") UUID id){
        userRepository.deleteById(id);
    }

    public User getCurrent(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return findByUsername(auth.getName());
    }
}
