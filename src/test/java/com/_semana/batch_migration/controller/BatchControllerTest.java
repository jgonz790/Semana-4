package com._semana.batch_migration.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BatchControllerTest {

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job job;

    @Mock
    private JobExecution jobExecution;

    @InjectMocks
    private BatchController batchController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(batchController).build();
    }

    @Test
    void testRunBatch_Success() throws Exception {
        // Given
        when(jobLauncher.run(eq(job), any(JobParameters.class))).thenReturn(jobExecution);

        // When & Then
        mockMvc.perform(post("/api/batch/migrate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Batch ejecutado exitosamente"));
    }

    @Test
    void testRunBatch_Exception() throws Exception {
        // Given
        when(jobLauncher.run(eq(job), any(JobParameters.class)))
                .thenThrow(new RuntimeException("Error de prueba"));

        // When & Then
        mockMvc.perform(post("/api/batch/migrate")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Error al ejecutar batch: Error de prueba"));
    }
}