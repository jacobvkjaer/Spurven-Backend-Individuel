package couchpotatoes.spurven.application.service;

import couchpotatoes.spurven.security.entity.User;
import couchpotatoes.spurven.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(String username) {
        return userRepository.findById(username).
                orElseThrow(() -> new ResponseStatusException
                        (HttpStatus.NOT_FOUND, "User with username: " + username + ", could not be found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User editUser(User body, String username){
        User user = getUserById(username);
        user.setUsername(body.getUsername());
        user.setPassword(body.getPassword());
        user.setFirstName(body.getFirstName());
        user.setLastName(body.getLastName());
        user.setEmail(body.getEmail());
        user.setPhone(body.getPhone());
        return userRepository.save(user);
    }

}
