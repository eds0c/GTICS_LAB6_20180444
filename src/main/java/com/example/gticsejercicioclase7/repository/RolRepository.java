package com.example.gticsejercicioclase7.repository;

import com.example.gticsejercicioclase7.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Rol, Integer> {
}
