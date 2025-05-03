package com.lld.stack.overflow.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "DB_User")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incremented by DB
    private Long id;
    private String name;
    private String contact;
    private String address;
    private String email;
    private String password;
}
