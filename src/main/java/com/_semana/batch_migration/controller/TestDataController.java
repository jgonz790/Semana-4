package com._semana.batch_migration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com._semana.batch_migration.entity.Usuario;
import com._semana.batch_migration.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com._semana.batch_migration.entity.UsuarioMongo;
import com._semana.batch_migration.repository.UsuarioMongoRepository;

// ⬇️ NUEVAS IMPORTS PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/test")
// ⬇️ NUEVA ANOTACIÓN
@Tag(name = "Test Data", description = "Crear datos de prueba y consultar resultados")
public class TestDataController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioMongoRepository usuarioMongoRepository;
    
    // ⬇️ NUEVAS ANOTACIONES
    @Operation(
        summary = "Crear datos de prueba en MySQL",
        description = "Crea 3 usuarios de ejemplo en la base de datos MySQL para probar la migración"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Datos de prueba creados exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al crear datos de prueba")
    })
    @PostMapping("/create-data")
    public String createTestData() {
        usuarioRepository.save(new Usuario("Juan Pérez", "juan@email.com", 25));
        usuarioRepository.save(new Usuario("María García", "maria@email.com", 30));
        usuarioRepository.save(new Usuario("Carlos López", "carlos@email.com", 22));
        
        return "Datos de prueba creados en MySQL";
    }
    
    // ⬇️ NUEVAS ANOTACIONES
    @Operation(
        summary = "Obtener usuarios migrados de MongoDB",
        description = "Retorna la lista de todos los usuarios que han sido migrados a MongoDB"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de usuarios obtenida exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error al consultar MongoDB")
    })
    @GetMapping("/mongo-data")
    public List<UsuarioMongo> getMongoData() {
        return usuarioMongoRepository.findAll();
    }
}