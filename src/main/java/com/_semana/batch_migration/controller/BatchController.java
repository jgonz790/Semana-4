package com._semana.batch_migration.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// ⬇️ NUEVAS IMPORTS PARA SWAGGER
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/batch")
// ⬇️ NUEVA ANOTACIÓN
@Tag(name = "Batch Migration", description = "Ejecutar migración de datos MySQL a MongoDB")
public class BatchController {
    
    @Autowired
    private JobLauncher jobLauncher;
    
    @Autowired
    private Job job;
    
    // ⬇️ NUEVAS ANOTACIONES
    @Operation(
        summary = "Ejecutar migración de usuarios",
        description = "Inicia el proceso de migración de usuarios desde MySQL hacia MongoDB usando Spring Batch"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Migración ejecutada exitosamente"),
        @ApiResponse(responseCode = "500", description = "Error durante la migración")
    })
    @PostMapping("/migrate")
    public String runBatch() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis())
                    .toJobParameters();
            
            jobLauncher.run(job, jobParameters);
            return "Batch ejecutado exitosamente";
        } catch (Exception e) {
            return "Error al ejecutar batch: " + e.getMessage();
        }
    }
}