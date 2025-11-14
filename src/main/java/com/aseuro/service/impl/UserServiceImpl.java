package com.aseuro.service.impl;

import com.aseuro.entity.User;
import com.aseuro.exception.UserAlreadyExistsException;
import com.aseuro.payload.request.SignInRequest;
import com.aseuro.payload.request.SignUpRequest;
import com.aseuro.payload.response.APIResponse;
import com.aseuro.repository.UserRepository;
import com.aseuro.service.UserService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public APIResponse<String> signUp(SignUpRequest request) {
        APIResponse<String> response=new APIResponse<>();

        if(userRepository.existsByName(request.getName())){
            throw  new UserAlreadyExistsException("User with given name is already exists");
        }
        if(userRepository.existsByEmail(request.getEmail())){
            throw  new UserAlreadyExistsException("User with given email is already exists");

        }

        User user=new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(request.getName());
        user.setEmail(request.getEmail());
      user.setPassword(request.getPassword());

        User save = userRepository.save(user);


        if(save!=null){
            response.setStatus(201);
            response.setMessage("User registration completed");
            response.setData("Dear user "+request.getName()+" your registration is completed." +
                    "Your Registration Id is : "+save.getId());
            return response;
        }

        response.setStatus(500);
        response.setMessage("User registration failed");
        response.setData("Error during registration process");
        return response;
    }

    @Override
    public APIResponse<String> signIn(SignInRequest request) {
        return null;
    }
}
