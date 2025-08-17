package com._semana.batch_migration.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioMongoTest {

    @Test
    void testConstructorVacio() {
        UsuarioMongo usuario = new UsuarioMongo();
        assertNotNull(usuario);
        assertNull(usuario.getId());
        assertNull(usuario.getNombre());
        assertNull(usuario.getEmail());
        assertNull(usuario.getEdad());
    }

    @Test
    void testConstructorConParametros() {
        String nombre = "María López";
        String email = "maria@example.com";
        Integer edad = 28;
        
        UsuarioMongo usuario = new UsuarioMongo(nombre, email, edad);
        
        assertNotNull(usuario);
        assertEquals(nombre, usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals(edad, usuario.getEdad());
        assertNull(usuario.getId()); // ID es null hasta que se persiste
    }

    @Test
    void testGettersYSetters() {
        UsuarioMongo usuario = new UsuarioMongo();
        
        // Test ID
        String id = "507f1f77bcf86cd799439011";
        usuario.setId(id);
        assertEquals(id, usuario.getId());
        
        // Test nombre
        String nombre = "Carlos Ruiz";
        usuario.setNombre(nombre);
        assertEquals(nombre, usuario.getNombre());
        
        // Test email
        String email = "carlos@example.com";
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
        
        // Test edad
        Integer edad = 35;
        usuario.setEdad(edad);
        assertEquals(edad, usuario.getEdad());
    }

    @Test
    void testSettersConValoresNulos() {
        UsuarioMongo usuario = new UsuarioMongo("Test", "test@example.com", 20);
        
        usuario.setNombre(null);
        usuario.setEmail(null);
        usuario.setEdad(null);
        usuario.setId(null);
        
        assertNull(usuario.getNombre());
        assertNull(usuario.getEmail());
        assertNull(usuario.getEdad());
        assertNull(usuario.getId());
    }
}