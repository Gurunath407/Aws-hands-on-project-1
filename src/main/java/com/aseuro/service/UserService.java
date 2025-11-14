package com.aseuro.service;

import com.aseuro.payload.request.SignInRequest;
import com.aseuro.payload.request.SignUpRequest;
import com.aseuro.payload.response.APIResponse;

public interface UserService {

    APIResponse<String> signUp(SignUpRequest request);
    APIResponse<String> signIn(SignInRequest request);
}
