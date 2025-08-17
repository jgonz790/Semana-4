package com._semana.batch_migration.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com._semana.batch_migration.entity.Usuario;
import com._semana.batch_migration.entity.UsuarioMongo;
import com._semana.batch_migration.repository.UsuarioRepository;
import com._semana.batch_migration.repository.UsuarioMongoRepository;



import org.springframework.data.domain.Sort;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class BatchConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioMongoRepository usuarioMongoRepository;
 // Reader - Lee de MySQL
    @Bean
    public RepositoryItemReader<Usuario> reader() {
        RepositoryItemReader<Usuario> reader = new RepositoryItemReader<>();
        reader.setRepository(usuarioRepository);
        reader.setMethodName("findAll");
        
        // Agregar sort - ESTO FALTABA
        Map<String, Sort.Direction> sorts = new HashMap<>();
        sorts.put("id", Sort.Direction.ASC);
        reader.setSort(sorts);
        
        return reader;
    }
    // Writer - Escribe en MongoDB
    @Bean
    public RepositoryItemWriter<UsuarioMongo> writer() {
        RepositoryItemWriter<UsuarioMongo> writer = new RepositoryItemWriter<>();
        writer.setRepository(usuarioMongoRepository);
        writer.setMethodName("save");
        return writer;
    }
    
    
 // Processor - Convierte Usuario a UsuarioMongo
    @Bean
    public UsuarioProcessor processor() {
        return new UsuarioProcessor();
    }
    
    // Step - Define el paso del batch
    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("migrateStep", jobRepository)
                .<Usuario, UsuarioMongo>chunk(10, transactionManager)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    
    // Job - Define el trabajo completo
    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("migrateJob", jobRepository)
                .start(step)
                .build();
    }
    
}