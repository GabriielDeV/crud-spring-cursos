package com.gabriel.crudsrping.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.crudsrping.model.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
}
