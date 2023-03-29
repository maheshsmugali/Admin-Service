package com.pws.admin.dto;


import com.pws.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetUpdatepassword {

    private String token;

    private String newPassword;

    private String confirmPassword;

}

