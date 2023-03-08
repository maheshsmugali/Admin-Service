package com.pws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserRoleXRef implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ref_id", unique = true, nullable = false)
    private Integer refId;
    //    @NonNull
//    private Date updatedAt;
//    @NonNull
//    private Date createdAt;
//    @NonNull
//    private Integer createdBy;
//    @NonNull
//    private Integer updatedBy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
}
