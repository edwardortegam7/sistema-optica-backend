Usar IntelliJ Idea
Para probar en el Postman:

POST:
http://localhost:8080/generate-token
Se copia esto en el body
{
    "username": "edward@gmail.com",
    "password": "12345"
}
Se envia y se genera un token


GET:
http://localhost:8080/usuarios/edward@gmail.com
Se copia el token en "Auth" y se selecciona "Bearer Token"
Y regresa lo siguiente:
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
http://localhost:8080/actual-usuario
Se copia el token en "Auth" y se selecciona "Bearer Token"
Y regresa lo siguiente:
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
