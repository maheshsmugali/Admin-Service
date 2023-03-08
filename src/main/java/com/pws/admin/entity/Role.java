package com.pws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "r_name", unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean isActive;
//    @NonNull
//    private Date updatedAt;
//    @NonNull
//    private Date createdAt;
//    @NonNull
//    private Integer createdBy;
//    @NonNull
//    private Integer updatedBy;

}
