package com.arms.domain.service;

/**
 * Created by arms20170106 on 8/2/2560.
 */
import com.arms.domain.entity.User;
import com.arms.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        GrantedAuthority authority = new SimpleGrantedAuthority("springHr2017");

        boolean isEnabled = true;
        boolean isAccountNonExpired = true;
        boolean isCredentialsNonExpired = true;
        boolean isAccountNonLocked = true;

        UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, Arrays.asList(authority));

        return userDetails;
    }
}
