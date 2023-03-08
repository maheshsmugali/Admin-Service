package com.pws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(schema = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String email;
    @NonNull
    @CreatedDate
    private Date dob;
    @NonNull
    private Integer phone;
    @NonNull
    private String password;
//    @NonNull
//    @LastModifiedDate
//    private Date updatedAt=new Date();
//    @NonNull
//    @CreatedDate
//    private Date createdAt =new Date();
//    @NonNull
//    private Integer createdBy;
//    @NonNull
//    private Integer updatedBy;

}