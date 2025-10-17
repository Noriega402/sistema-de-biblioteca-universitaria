# Sistema de Gestión de Biblioteca Universitaria

Proyecto modular basado en **microservicios Spring Boot 3**, diseñado para administrar usuarios, libros, préstamos y notificaciones dentro de una biblioteca universitaria.  
Cada servicio funciona de manera independiente, comunicándose mediante **APIs REST** y autenticación **JWT**.

---

## Índice

1. [Descripción general](#descripción-general)   
2. [Tecnologías principales](#tecnologías-principales)  
3. [Cómo ejecutar los microservicios](#cómo-ejecutar-los-microservicios)  
4. [Autenticación (Auth Service)](#autenticación-auth-service)  
5. [Catálogo (Catalog Service)](#catálogo-catalog-service)  
6. [Préstamos (Loans Service)](#préstamos-loans-service)  
7. [Notificaciones (Notifications Service)](#notificaciones-notifications-service)  
8. [Roles y permisos](#roles-y-permisos)  
9. [Recomendaciones de prueba con Postman](#recomendaciones-de-prueba-con-postman)  
10. [Futuras mejoras](#futuras-mejoras)  
11. [Autor](#autor)

---

## Descripción general

El sistema permite:

- Registrar y autenticar usuarios mediante **JWT**.  
- Gestionar un catálogo de libros con operaciones CRUD.  
- Controlar préstamos y devoluciones.  
- Enviar notificaciones automáticas o manuales.  

El diseño basado en microservicios garantiza escalabilidad, mantenimiento independiente y seguridad basada en roles.

---


| Servicio | Puerto | Descripción |
|-----------|---------|-------------|
| **auth-service** | 8081 | Registro, login y validación JWT |
| **catalog-service** | 8082 | Gestión de libros y autores |
| **loans-service** | 8083 | Creación y control de préstamos |
| **notifications-service** | 8084 | Envío de notificaciones por correo o sistema |

---

## Tecnologías principales

- Java 21  
- Spring Boot 3.5.6  
- Spring Security + JWT  
- Spring Data JPA
- Spring Web
- PostgreSQL  
- Lombok  
- Maven  
- Docker (opcional)  
- Postman (para pruebas de endpoints)

---

## Cómo ejecutar los microservicios

Cada servicio se ejecuta de manera independiente.  
Asegúrate de configurar las credenciales de PostgreSQL en el archivo `application.yml`.

### Configurar credenciales para la DB en application.yml
```sql
datasource:
    url: jdbc:postgresql://localhost:5432/<nombre de la DB>
    username: <usuario>
    password: <password>
    driver-class-name: org.postgresql.Driver
```
**Nota:** El port por defecto en Postgres es **5432**, de ser necesario cambialo a tus necesidades

### 1. Auth Service
```bash
cd auth-service
mvn spring-boot:run
```

### 2. Catalog Service
```bash
cd catalog-service
mvn spring-boot:run
```

### 3. Loans Service
```bash
cd loans-service
mvn spring-boot:run
```

### 4. Notifications Service
```bash
cd notifications-service
mvn spring-boot:run
```

---

## Autenticación (Auth Service)

### Registro de usuario
**POST** `http://localhost:8081/api/v1/auth/register`

**Body**
```json
{
  "username": "juanperez",
  "email": "juan@example.com",
  "password": "123456",
  "role": "ESTUDIANTE"
}
```

**Respuesta**
```json
{
  "message": "Usuario registrado correctamente",
  "userId": 1
}
```

---

### Inicio de sesión
**POST** `http://localhost:8081/api/v1/auth/login`

**Body**
```json
{
  "username": "juanperez",
  "password": "123456"
}
```

**Respuesta**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "role": "ESTUDIANTE"
}
```

**Encabezado para usar el token JWT en las demás peticiones:**
```
Authorization: Bearer <tu_token>
```

---

## Catálogo (Catalog Service)

### Consultar libros
**GET** `http://localhost:8082/api/v1/books`

**Respuesta**
```json
[
  {
    "id": 1,
    "title": "Cien años de soledad",
    "author": "Gabriel García Márquez",
    "availableCopies": 4
  }
]
```

---

### Registrar un nuevo libro
**POST** `http://localhost:8082/api/v1/books`

**Body**
```json
{
  "title": "El Principito",
  "author": "Antoine de Saint-Exupéry",
  "isbn": "978-3-16-148410-0",
  "copies": 5
}
```

**Respuesta**
```json
{
  "message": "Libro registrado correctamente",
  "bookId": 2
}
```

---

## Préstamos (Loans Service)

### Crear préstamo
**POST** `http://localhost:8083/api/v1/loans`

**Body**
```json
{
  "userId": 1,
  "bookId": 2,
  "loanDate": "2025-10-16",
  "returnDate": "2025-10-23"
}
```

**Respuesta**
```json
{
  "loanId": 1,
  "status": "EN_CURSO"
}
```

---

### Finalizar préstamo
**PUT** `http://localhost:8083/api/v1/loans/1/return`

**Respuesta**
```json
{
  "message": "Préstamo finalizado correctamente"
}
```

---

## Notificaciones (Notifications Service)

### Enviar correo de notificación
**POST** `http://localhost:8084/api/v1/notifications/email`

**Body**
```json
{
  "to": "juan@example.com",
  "subject": "Recordatorio de devolución",
  "message": "Estimado usuario, recuerde devolver su libro antes del 23/10/2025."
}
```

**Respuesta**
```json
{
  "status": "ENVIADO",
  "timestamp": "2025-10-16T22:00:00"
}
```

---

## Roles y permisos

| Rol | Permisos |
|------|-----------|
| **ESTUDIANTE** | Consultar libros, crear préstamos |
| **BIBLIOTECARIO** | Agregar libros, cerrar préstamos |
| **ADMIN** | Acceso completo |

---

## Recomendaciones de prueba con Postman

1. Registra tres usuarios: **ADMIN**, **BIBLIOTECARIO** y **ESTUDIANTE**.  
2. Inicia sesión con cada uno y guarda sus **tokens JWT**.  
3. Usa el token de **ADMIN** para registrar libros.  
4. Usa el token de **ESTUDIANTE** para solicitar préstamos.  
5. Usa el token de **BIBLIOTECARIO** para finalizar préstamos.  
6. Usa el servicio de **notificaciones** para confirmar envíos de correo.

---

## Futuras mejoras

- Integración con **Spring Cloud Gateway** para unificar el acceso.  
- Implementación de **notificaciones automáticas** para préstamos vencidos.  
- Creación del **frontend** (React o Vite) con autenticación JWT.  
- Monitoreo con **Prometheus + Grafana**.  
- Pruebas unitarias y de integración automatizadas.

---
