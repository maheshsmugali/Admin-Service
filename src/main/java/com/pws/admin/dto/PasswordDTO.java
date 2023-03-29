package com.pws.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {


    private String email;
    private String oldPassword;

    private String newPassword;

    private String confirmPassword;


}
