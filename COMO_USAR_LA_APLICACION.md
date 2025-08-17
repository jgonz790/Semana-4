# 🎮 Cómo Usar Mi Aplicacion de Migracion-

##  Paso 1: Preparar todo

### 1.1 Encender Docker
```bash
# Primero asegúrate que Docker esté corriendo
docker ps
```
Si ves las bases de datos (mysql-batch y mongodb), ¡perfecto! Si no, pregúntame cómo levantarlas.

### 1.2 Ir a la carpeta del proyecto
```bash
cd "C:\Users\Admin\Semana-4\batch-migration\batch-migration"
```

##  Paso 2: Encender la aplicación

```bash
mvn spring-boot:run
```

**¿Cómo sé que funciona?**
- Verás muchos mensajes en verde
- Al final dirá: "Started BatchMigrationApplication"
- ¡Listo! Ya está corriendo

##  Paso 3: Abrir en el navegador

### 3.1 Ver la documentación (Swagger)
1. Abre tu navegador (Chrome, Firefox, etc.)
2. Ve a: `http://localhost:8080/swagger-ui.html`
3. ¡Ahí está toda mi aplicación explicada!

### 3.2 Usar las APIs

#### **Opción A: Desde Swagger (Más fácil)**
1. En Swagger, verás 2 secciones:
   - **batch-controller:** Para migrar datos
   - **test-data-controller:** Para crear datos de prueba

#### **Opción B: Desde Postman o navegador**
- **Crear usuarios de prueba:** `GET http://localhost:8080/api/test-data/create`
- **Migrar datos:** `POST http://localhost:8080/api/batch/migrate`

##  Paso 4: Probar la aplicación

### 4.1 Crear datos de prueba
1. En Swagger, busca "test-data-controller"
2. Click en "POST /api/test-data/create"
3. Click en "Try it out"
4. Click en "Execute"
5. ¡Ya tienes usuarios en MySQL!

### 4.2 Migrar los datos
1. Busca "batch-controller"
2. Click en "POST /api/batch/migrate"
3. Click en "Try it out"
4. Click en "Execute"
5. ¡Los usuarios se copiaron a MongoDB!

## 📊 Paso 5: Ver los reportes de tests

### 5.1 Ejecutar tests
```bash
mvn clean test
```

### 5.2 Ver cobertura de JaCoCo
1. Después de los tests, abre:
2. `target/site/jacoco/index.html`
3. ¡Verás que tengo 94% de cobertura!

## 🔧 Paso 6: Parar la aplicación

**Para detener todo:**
- En la terminal donde corre la app: `Ctrl + C`

##  Si algo no funciona

### Error común: "Puerto ocupado"
```bash
# Matar proceso en puerto 8080
taskkill /F /IM java.exe
```

### Error: "No encuentra MySQL"
```bash
# Verificar que Docker esté corriendo
docker ps
# Si no está, levanta los contenedores
docker start mysql-batch mongodb
```


Mi aplicación de migracion para semana 4 xideral
1. **Encender:** `mvn spring-boot:run`
2. **Abrir:** `http://localhost:8080/swagger-ui.html`
3. **Probar:** Click en los botones de Swagger
4. **Ver resultados:** Los datos se migran automáticamente

