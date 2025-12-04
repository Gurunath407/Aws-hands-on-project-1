package com.aseuro.payload.request;

import com.aseuro.annotation.EmailValidation;
import com.aseuro.annotation.PasswordValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignUpRequest {

    @NotNull(message = "Name should not be null")
    @Size(min = 3,message = "Name should at least contains 3 characters")
    @NotBlank(message = "Name should not be blank")
    private String name;

    @NotNull(message = "Email should not be null")
    @NotBlank(message = "Email should not be blank")
    @EmailValidation
    private String email;

    @NotNull(message = "Password should not be null")
    @NotBlank(message = "Password should not be blank")
    @PasswordValidation
    private String password;



    public SignUpRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;

    }

    public SignUpRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
