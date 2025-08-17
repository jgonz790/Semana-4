package com._semana.batch_migration.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com._semana.batch_migration.entity.UsuarioMongo;
//interface para Repositorio MongoDB
@Repository
public interface UsuarioMongoRepository extends MongoRepository<UsuarioMongo, String> {
}