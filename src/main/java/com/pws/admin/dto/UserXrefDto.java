package com.pws.admin.dto;

import com.pws.admin.entity.Role;
import com.pws.admin.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserXrefDto {

    private  Integer refId;

    private Integer userId;

    private Integer roleId;

    private Boolean isActive;
}
