# MsSpecialistTest

Este projeto foi gerado com:
- Java 11
- Spring Boot
- MongoDB
- Auth JWT
- Maven

## Build

Execute `mvn clean install`

## Docker

- Antes de executar o `docker-compose` é necessário realizar o build da aplicação para que seja gerada a pasta `target`;
- Execute `docker-compose -f "docker-compose.yml" up -d --build` para executar a aplicação em um container Docker;
- Url de acesso: http://localhost:8080

## Development server

- Configurar perfil `dev` 
  - IntelliJ: 'Add VM Options' -> `-Dspring.profiles.active=dev`
- Execute a aplicação
- Url de acesso: http://localhost:8080

## APIs

- Credenciais Padrão:
  - Usuário: `admin@teste.com`
  - Senha: `123456`

- Login: POST - `/ms-st/login` - HTTP 200
  - Request:
  ```json
  {
    "username": "admin@teste.com",
    "password": "123456"
  }
  ```
  - Response:
  ```json
  {
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJmdWxsTmFtZSI6IlVzZXIgQWRtaW4gSW5pdCIsImV4cCI6MTY0NTA1NDgyNiwidXNlcklkIjoiNjIwZDhhMTEyZTZmNjYzYTA4N2RjMWM2IiwiaWF0IjoxNjQ1MDU0Nzg0LCJhdXRob3JpdGllcyI6W119.Zu0Y2D8ODYARawgbBjZfR0kd1VFeEcaUIu2D3pVGOAV1OCY6vST4M1Vv027-jJMIsGwJpFqSAzC1QChYFhDv0w"
  }
  ```
- Cliente - GET - `/ms-st/client - HTTP 200
  - Header: `Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJmdWxsTmFtZSI6IlVzZXIgQWRtaW4gSW5pdCIsImV4cCI6MTY0NTA1NDU0NCwid` 
  - Response:
   ```json
  [
  {
    "operadora": "MASTERCARD",
    "cliente": "cliente 33",
    "valorTotal": 44
  },
  {
    "operadora": "ELO",
    "cliente": "cliente-test",
    "valorTotal": 256.33
  }
  ]
  ```
- SendLog - POST - `/ms-st/log` - HTTP 201
  - Header: `Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJmdWxsTmFtZSI6IlVzZXIgQWRtaW4gSW5pdCIsImV4cCI6MTY0NTA1NDU0NCwid` 
  - Request:
   ```json
  {"flag":"AMERICANEXPRESS","transactionAt":"2022-02-17T03:00:00.000Z","client":"cliente-test","value":222}
  ```
