package com.dscatalog.dscatalog.dto;

import com.dscatalog.dscatalog.services.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Required Field.")
    @Size(min = 8, message = "Should be at least 8 characters.")
    private String password;

    UserInsertDTO(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
