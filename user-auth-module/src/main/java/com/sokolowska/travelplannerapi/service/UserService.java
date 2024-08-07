package com.sokolowska.travelplannerapi.service;


import com.sokolowska.travelplannerapi.model.CustomUser;
import com.sokolowska.travelplannerapi.repository.CustomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public long saveNewUser(CustomUser user){
        CustomUser newCustomUser = new CustomUser();
        newCustomUser.setEmail(user.getEmail());
        newCustomUser.setPwd(passwordEncoder.encode(user.getPwd()));
        newCustomUser.setRole(user.getRole());
        return userRepository.save(newCustomUser).getId();
    }
}
