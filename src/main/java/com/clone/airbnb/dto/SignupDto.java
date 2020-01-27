package com.clone.airbnb.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.password.PasswordEncoder;
import com.clone.airbnb.entity.User;
import com.clone.airbnb.validator.annotation.UniqueUsername;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupDto {
    @Size(min = 1, max = 150)
    @Email
    @UniqueUsername
    private String username;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,30}$", message = "validation.password.not_properly")
    private String password;
    private String retypePassword;
    @Size(min = 1, max = 30)
    private String firstName;
    @Size(min = 1, max = 30)
    private String lastName;
    
    
    
    public User toOriginal(PasswordEncoder encoder) {
    	User user = new User();
    	user.setUsername(this.getUsername());
    	user.setPassword(encoder.encode(this.getPassword()));
    	user.setFirstName(this.getFirstName());
    	user.setLastName(this.getLastName());
    	
    	return user;
    }
    
}
