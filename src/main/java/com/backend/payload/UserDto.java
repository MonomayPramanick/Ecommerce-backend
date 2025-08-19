package com.backend.payload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	 	
		private int id;

	    @NotEmpty
	    @Size(min=4,message="Username must be min of 4 characters !!")
	    private String  username;

	    @Email(message = "Email should be valid")
	    @NotNull(message = "Email cannot be null")
	    private String email;
	    
	   

	    @Column(length = 15) 
		private String phone;
	    @NotEmpty(message = "Password cannot be null")
	    @Pattern(
	    	    regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
	    	    message = "Password must be at least 8 characters long and include an uppercase letter, lowercase letter, number, and special character"
	    	)
	    private String password;

	
}
