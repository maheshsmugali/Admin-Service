package com.pws.admin.entity;

import com.pws.admin.utility.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends AuditModel {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "r_name", unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean isActive;

}
