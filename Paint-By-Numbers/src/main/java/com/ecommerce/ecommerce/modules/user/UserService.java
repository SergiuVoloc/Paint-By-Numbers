package com.ecommerce.ecommerce.modules.user;

import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface UserService {
     Iterable<User> all();
     User read(@PathVariable(value = "id") UUID id);
     User create(User u);
     User update(@PathVariable(value = "id") UUID id, User o);
     User findByUsername(String username);
     void delete(@PathVariable(value = "id") UUID id);
     User getCurrent();
     boolean isUserAlreadyPresent(User user);
}
