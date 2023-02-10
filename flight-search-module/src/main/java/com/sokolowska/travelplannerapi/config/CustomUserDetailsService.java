package com.sokolowska.travelplannerapi.config;

import com.sokolowska.travelplannerapi.model.CustomUser;
import com.sokolowska.travelplannerapi.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String userName, password = null;
        List<GrantedAuthority> authorities = null;
        CustomUser user = userRepository.findByEmail(username)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("User details not found for the user : " + username);
                });

            userName = user.getEmail();
            password = user.getPwd();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(userName,password,authorities);
    }
}
