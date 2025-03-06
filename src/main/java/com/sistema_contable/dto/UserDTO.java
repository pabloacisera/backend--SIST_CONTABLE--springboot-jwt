package com.sistema_contable.dto;

import com.sistema_contable.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Boolean status;
    private UserEntity.Role role;
}
