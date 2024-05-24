package com.example.gticsejercicioclase7.repository;

import com.example.gticsejercicioclase7.entity.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Usuarios, Integer> {

    Usuarios findByCorreo(String correo);

}
