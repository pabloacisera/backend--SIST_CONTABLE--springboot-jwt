package com.sistema_contable.dto;

import com.sistema_contable.entities.UserEntity;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private UserEntity.Role role;
}
