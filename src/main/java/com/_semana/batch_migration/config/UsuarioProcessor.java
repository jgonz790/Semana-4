package com._semana.batch_migration.config;

import org.springframework.batch.item.ItemProcessor;
import com._semana.batch_migration.entity.Usuario;
import com._semana.batch_migration.entity.UsuarioMongo;

public class UsuarioProcessor implements ItemProcessor<Usuario, UsuarioMongo> {

    @Override
    public UsuarioMongo process(Usuario usuario) throws Exception {
        return new UsuarioMongo(usuario.getNombre(), usuario.getEmail(), usuario.getEdad());
    }
}