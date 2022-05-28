package com.example.employee.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String name;
    private String email;
    private String phone;

    @CreationTimestamp
    @Column(name = "CREATEDAT", updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "UPDATEDAT")
    private Date updatedAt;
}
