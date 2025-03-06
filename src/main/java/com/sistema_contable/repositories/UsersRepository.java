package com.sistema_contable.repositories;

import com.sistema_contable.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Integer> {

    /*
    * crear metodo personalizado de busqueda por email
     */

    Optional<UserEntity> findByEmail(String email);
}