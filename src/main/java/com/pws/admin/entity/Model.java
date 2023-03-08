package com.pws.admin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer modelId;

    private String name;
    @Column(name = "is_active",nullable = true)
     private Boolean isActive;


//    @NonNull
//    private Date updatedAt;
//    @NonNull
//    private Date createdAt;
//    @NotNull
//    private Integer createdBy;
//    @NotNull
//    private Integer updatedBy;

}
