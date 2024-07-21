package com.sokolowska.travelplannerapi.controller;


import com.sokolowska.travelplannerapi.model.CustomUser;
import com.sokolowska.travelplannerapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody CustomUser customUser){
        Long userId = userService.saveNewUser(customUser);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("New User is successfully registered");
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Welcome!", HttpStatus.OK);
    }
}
