package com._semana.batch_migration.controller;

import com._semana.batch_migration.entity.Usuario;
import com._semana.batch_migration.entity.UsuarioMongo;
import com._semana.batch_migration.repository.UsuarioRepository;
import com._semana.batch_migration.repository.UsuarioMongoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TestDataControllerTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private UsuarioMongoRepository usuarioMongoRepository;

    @InjectMocks
    private TestDataController testDataController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(testDataController).build();
    }

    @Test
    void testCreateTestData_Success() throws Exception {
        // Given
        Usuario mockUsuario = new Usuario("Test User", "test@email.com", 25);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(mockUsuario);

        // When & Then
        mockMvc.perform(post("/api/test/create-data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Datos de prueba creados en MySQL"));

        // Verify que se llamó save 3 veces (3 usuarios)
        verify(usuarioRepository, times(3)).save(any(Usuario.class));
    }

    @Test
    void testGetMongoData_Success() throws Exception {
        // Given
        List<UsuarioMongo> mockUsuarios = Arrays.asList(
            new UsuarioMongo("Juan Pérez", "juan@email.com", 25),
            new UsuarioMongo("María García", "maria@email.com", 30)
        );
        when(usuarioMongoRepository.findAll()).thenReturn(mockUsuarios);

        // When & Then
        mockMvc.perform(get("/api/test/mongo-data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre").value("Juan Pérez"))
                .andExpect(jsonPath("$[0].email").value("juan@email.com"))
                .andExpect(jsonPath("$[1].nombre").value("María García"));
    }

    @Test
    void testGetMongoData_EmptyList() throws Exception {
        // Given
        when(usuarioMongoRepository.findAll()).thenReturn(Arrays.asList());

        // When & Then
        mockMvc.perform(get("/api/test/mongo-data")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }
}