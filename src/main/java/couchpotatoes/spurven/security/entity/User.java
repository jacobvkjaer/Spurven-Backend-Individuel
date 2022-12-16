package couchpotatoes.spurven.security.entity;

import couchpotatoes.spurven.security.dto.UserWithRolesRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
public class User implements UserDetails {

    @Transient
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Id
    @Column(nullable = false,length = 50, unique = true)
    String username;

    //60 = length of a bcrypt encoded password
    @Column(nullable = false, length = 60)
    String password;

    @Column(length = 255)
    private String firstName;
    @Column(length = 255)
    private String lastName;
    @Column(length = 255)
    private String email;
    @Column(length = 255)
    private String phone;

    private boolean enabled = true;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime edited;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('USER','ADMIN')")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "security_role")
    List<Role> roles = new ArrayList<>();

    public User() {}


   // We will use this constructor when/if users must be created via an HTTP-request
    public User(UserWithRolesRequest body) {
        this.username = body.getUsername();
        this.setPassword(body.getPassword());
    }

    public User(String user, String password){
        this.username = user;
        setPassword(password);
    }

    public User(String username, String password, String firstName, String lastName, String email, String phone) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        setPassword(password);
    }

    public void setPassword(String pw){
        this.password = passwordEncoder.encode(pw);
    }

    public void addRole(Role role){
        roles.add(role);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
    }

    //You can, but are NOT expected to use the fields below
    @Override
    public boolean isAccountNonExpired() {return enabled;}

    @Override
    public boolean isAccountNonLocked() { return enabled;}

    @Override
    public boolean isCredentialsNonExpired() { return enabled; }
}

