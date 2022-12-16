package couchpotatoes.spurven.security;

import couchpotatoes.spurven.security.entity.User;
import couchpotatoes.spurven.security.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

  UserRepository userWithRolesRepository;

  public UserDetailsServiceImp(UserRepository userWithRolesRepository) {
    this.userWithRolesRepository = userWithRolesRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    System.out.println("loadUsersByUsername");
    final Optional<User> optionalUser = userWithRolesRepository.findById(username);
    return optionalUser.orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Incorrect username or password"));
  }
}
