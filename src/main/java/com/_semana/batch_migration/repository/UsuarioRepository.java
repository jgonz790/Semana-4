package com._semana.batch_migration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com._semana.batch_migration.entity.Usuario;
//Repositorio  para MySQL
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}