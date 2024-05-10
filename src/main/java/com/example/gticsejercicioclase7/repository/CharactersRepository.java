package com.example.gticsejercicioclase7.repository;

import com.example.gticsejercicioclase7.entity.Characters;
import org.hibernate.Incubating;
import org.hibernate.Internal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharactersRepository extends JpaRepository<Characters, Integer> {
}
