package com._semana.batch_migration.entity;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testConstructorVacio() {
        Usuario usuario = new Usuario();
        assertNotNull(usuario);
        assertNull(usuario.getId());
        assertNull(usuario.getNombre());
        assertNull(usuario.getEmail());
        assertNull(usuario.getEdad());
    }

    @Test
    void testConstructorConParametros() {
        String nombre = "Juan Pérez";
        String email = "juan@example.com";
        Integer edad = 30;
        
        Usuario usuario = new Usuario(nombre, email, edad);
        
        assertNotNull(usuario);
        assertEquals(nombre, usuario.getNombre());
        assertEquals(email, usuario.getEmail());
        assertEquals(edad, usuario.getEdad());
        assertNull(usuario.getId()); // ID es null hasta que se persiste
    }

    @Test
    void testGettersYSetters() {
        Usuario usuario = new Usuario();
        
        // Test ID
        Long id = 1L;
        usuario.setId(id);
        assertEquals(id, usuario.getId());
        
        // Test nombre
        String nombre = "Ana García";
        usuario.setNombre(nombre);
        assertEquals(nombre, usuario.getNombre());
        
        // Test email
        String email = "ana@example.com";
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail());
        
        // Test edad
        Integer edad = 25;
        usuario.setEdad(edad);
        assertEquals(edad, usuario.getEdad());
    }

    @Test
    void testSettersConValoresNulos() {
        Usuario usuario = new Usuario("Test", "test@example.com", 20);
        
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