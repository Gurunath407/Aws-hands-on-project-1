package com.aseuro.controller;

import com.aseuro.payload.request.SignUpRequest;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<APIResponse<String>> signUp(@Valid @RequestBody SignUpRequest request){
        APIResponse<String> response = userService.signUp(request);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatus()));
    }
}
