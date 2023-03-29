package com.pws.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {

    private int id;

    private String firstName;

    private String lastName;

    private Date dob;

    private String email;

    private long phoneNumber;

    private String password;

    private Boolean isActive;

}

