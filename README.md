# README

## Uso de IntelliJ IDEA y Postman para probar la API

Este documento proporciona instrucciones paso a paso sobre cómo probar la API utilizando IntelliJ IDEA y Postman.

### Requisitos previos

- IntelliJ IDEA instalado
- Postman instalado
- API en funcionamiento en `http://localhost:8080`

### Instrucciones

#### Generar un Token

Para generar un token, sigue los siguientes pasos:

1. Abre Postman.
2. Configura una nueva petición POST:
   - URL: `http://localhost:8080/generate-token`
   - Body: Selecciona `raw` y `JSON` e introduce el siguiente contenido:

   ```json
   {
       "username": "edward@gmail.com",
       "password": "12345"
   }
3. Se obtiene un token

GET:
1. Configura una nueva petición GET:
   - URL: `http://localhost:8080/employees/edward@gmail.com`
   - Auth: Selecciona `Bearer Token` e introduce el token
2. Regresa lo siguiente:
   ```json
   {
    "id": 1,
    "username": "edward@gmail.com",
    "password": "$2a$10$ekFZGbSNmyDhv4e9u8wn9.xch2pKCwQG6X8WxyA8M/nfcu6wjzr9G",
    "nombres": "Edward",
    "apellidos": "Ortega",
    "telefono": "987654321",
    "enabled": true,
    "perfil": "foto.png",
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "authorities": [
        {
            "authority": "ADMINISTRADOR"
        }
    ],
    "accountNonLocked": true
    }

GET:
1. Configura una nueva petición GET:
   - URL: `http://localhost:8080/actual-employee`
   - Auth: Selecciona `Bearer Token` e introduce el token
2. Regresa lo siguiente:
   ```json
   {
    "id": 1,
    "username": "edward@gmail.com",
    "password": "$2a$10$ekFZGbSNmyDhv4e9u8wn9.xch2pKCwQG6X8WxyA8M/nfcu6wjzr9G",
    "nombres": "Edward",
    "apellidos": "Ortega",
    "telefono": "987654321",
    "enabled": true,
    "perfil": "foto.png",
    "credentialsNonExpired": true,
    "accountNonExpired": true,
    "authorities": [
        {
            "authority": "ADMINISTRADOR"
        }
    ],
    "accountNonLocked": true
    }
