# ApiRestFullE

Este proyecto es una API REST desarrollada con **Spring Boot 4** que permite gestionar empleados, géneros, puestos de trabajo y horas trabajadas. Proporciona operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para las siguientes entidades:

- **Employees**: Empleados.
- **Genders**: Géneros.
- **Jobs**: Puestos de trabajo.
- **EmployeeWorkedHours**: Horas trabajadas por empleados.

La API se puede probar utilizando **Postman** y utiliza **Oracle SQL Developer** como base de datos.

## 🚀 Características principales

- **Gestión de empleados**: Permite registrar, editar, eliminar y listar empleados.
- **Gestión de géneros**: Permite registrar, editar, eliminar y listar géneros.
- **Gestión de puestos de trabajo**: Permite registrar, editar, eliminar y listar puestos de trabajo.
- **Gestión de horas trabajadas**: Permite registrar, editar, eliminar y listar las horas trabajadas por los empleados.
- **Validaciones**: Incluye validaciones como:
  - Verificar que un empleado sea mayor de edad.
  - Evitar duplicados en nombres y apellidos de empleados.
  - Validar que los géneros y puestos de trabajo existan antes de asignarlos a un empleado.

## 🛠️ Tecnologías utilizadas

- **Spring Boot 4**: Framework para desarrollar aplicaciones Java.
- **Maven**: Gestión de dependencias y construcción del proyecto.
- **Oracle SQL Developer**: Base de datos relacional para almacenar la información.
- **Postman**: Herramienta para probar las peticiones HTTP a la API.
- **Lombok**: Para reducir el código boilerplate (getters, setters, constructores, etc.).

## 📦 Estructura del proyecto

El proyecto sigue la estructura MVC (Modelo-Vista-Controlador) y está organizado en los siguientes paquetes:

- **controller**: Contiene los controladores REST.
- **entity**: Contiene las clases `@Entity` que representan las tablas de la base de datos.
- **repository**: Contiene las interfaces que extienden `JpaRepository` para acceder a la base de datos.
- **service**: Contiene las clases `@Service` que implementan la lógica de negocio.

## 📋 Estructura de la base de datos

### Tablas

1. **GENDERS**: Almacena los géneros.

   ```sql
    CREATE TABLE GENDERS (
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(255)
    );
    ```

2. **JOBS**: Almacena los puestos de trabajo.

    ```sql
    CREATE TABLE JOBS(
    ID NUMBER PRIMARY KEY,
    NAME VARCHAR2(255),
    SALARY NUMBER(9,2)
    );
    ```

3. **EMPLOYEES**: Almacena los empleados.

    ```sql
    CREATE TABLE EMPLOYEES(
    ID NUMBER PRIMARY KEY,
    GENDER_ID NUMBER,
    JOB_ID NUMBER,
    NAME VARCHAR2(255),
    LAST_NAME VARCHAR2(255),
    BIRTHDATE DATE,
    FOREIGN KEY (GENDER_ID) REFERENCES GENDERS(ID),
    FOREIGN KEY (JOB_ID) REFERENCES JOBS(ID)
    );
    ```
    

4. **EMPLOYEE_WORKED_HOURS**: Almacena las horas trabajadas por los empleados.

    ```sql
    CREATE TABLE EMPLOYEE_WORKED_HOURS(
    ID NUMBER PRIMARY KEY,
    EMPLOYEE_ID NUMBER,
    WORKED_HOURS NUMBER,
    WORKED_DATE DATE,
    FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEES(ID)
    );
    ```

### Datos de ejemplo

#### Registros de Generos

    ```sql
    -- Géneros
    INSERT INTO GENDERS (ID, NAME) VALUES (1, 'Masculino');
    INSERT INTO GENDERS (ID, NAME) VALUES (2, 'Femenino');
    INSERT INTO GENDERS (ID, NAME) VALUES (3, 'No Binario');
    COMMIT;
    ```
#### Registros de Puesto de trabajo 

    ```sql
    -- Puestos de trabajo
    INSERT INTO JOBS (ID, NAME, SALARY) VALUES (1, 'Desarrollador', 25000.00);
    INSERT INTO JOBS (ID, NAME, SALARY) VALUES (2, 'Gerente', 40000.00);
    INSERT INTO JOBS (ID, NAME, SALARY) VALUES (3, 'Diseñador', 20000.00);
    INSERT INTO JOBS (ID, NAME, SALARY) VALUES (4, 'Analista', 30000.00);
    COMMIT;
    ```

#### Registro de Empleados

    ```sql
    -- Empleados
    INSERT INTO EMPLOYEES (ID, GENDER_ID, JOB_ID, NAME, LAST_NAME, BIRTHDATE)
    VALUES (1, 1, 1, 'Juan', 'Pérez', TO_DATE('1990-05-15', 'YYYY-MM-DD'));
    COMMIT;
    
    INSERT INTO EMPLOYEES (ID, GENDER_ID, JOB_ID, NAME, LAST_NAME, BIRTHDATE)
    VALUES (2, 2, 2, 'María', 'Gómez', TO_DATE('1985-10-22', 'YYYY-MM-DD'));
    COMMIT;

    ```


#### Registro de horas trabajadas

    ```sql
    -- Horas trabajadas
    INSERT INTO EMPLOYEE_WORKED_HOURS (ID, EMPLOYEE_ID, WORKED_HOURS, WORKED_DATE)
    VALUES (1, 1, 8, TO_DATE('2023-10-01', 'YYYY-MM-DD'));
    COMMIT;
    ```



## 📦 Instalación y configuración
Sigue estos pasos para configurar y ejecutar el proyecto en tu máquina local.

### Requisitos previos
- **Java JDK 17** : Asegúrate de tener Java instalado. Puedes descargarlo aquí.

- **Maven**: Asegúrate de tener Maven instalado. Puedes descargarlo aquí.

- **Oracle SQL Developer**: Instala Oracle SQL Developer y configura la base de datos.

- **Postman**: Instala Postman para probar la API. Puedes descargarlo aquí.

### Pasos para la instalación

1- Clona el repositorio:

 ```bash    

    git clone https://github.com/IvanJimra/ApiRestFullE.git

 ```


2- Configura la base de datos:

Ejecuta el script SQL proporcionado en la sección Estructura de la base de datos para crear las tablas e insertar datos de ejemplo.

3- Configura el archivo `application.properties`:

Asegúrate de que el archivo `src/main/resources/application.properties` tenga la configuración correcta para conectarse a tu base de datos. Por ejemplo:

 ```bash
    spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
    spring.datasource.username=System
    spring.datasource.password=123456
    spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
    spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
    spring.jpa.properties.hibernate.format_sql=true
    logging.level.org.hibernate.SQL=DEBUG
    logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
    spring.jackson.time-zone=America/Mexico_City
    Compila y ejecuta el proyecto:
```

4- Abre Postman y prueba los endpoints.

## 🔍 Pruebas en Postman

### Endpoints disponibles

#### Employees

- **Listar empleados**: GET `http://localhost:9000/api/EmployeesWs/listar`

- **Buscar empleado por ID**: POST `http://localhost:9000/api/EmployeesWs/buscar`

- **Guardar empleado**: POST `http://localhost:9000/api/EmployeesWs/guardar`

- **Editar empleado**: POST `http://localhost:9000/api/EmployeesWs/editar`

- **Eliminar empleado**: POST `http://localhost:9000/api/EmployeesWs/eliminar`

#### Genders

- **Listar géneros**: GET `http://localhost:9000/api/GendersWs/listar`

- **Buscar género por ID**: POST `http://localhost:9000/api/GendersWs/buscar`

- **Guardar género**: POST `http://localhost:9000/api/GendersWs/guardar`

- **Editar género**: POST `http://localhost:9000/api/GendersWs/editar`

- **Eliminar género**: POST `http://localhost:9000/api/GendersWs/eliminar`

#### Jobs

- **Listar puestos de trabajo**: GET `http://localhost:9000/api/JobsWs/listar`

- **Buscar puesto por ID**: POST `http://localhost:9000/api/JobsWs/buscar`

- **Guardar puesto**: POST `http://localhost:9000/api/JobsWs/guardar`

- **Editar puesto**: POST `http://localhost:9000/api/JobsWs/editar`

- **Eliminar puesto**: POST `http://localhost:9000/api/JobsWs/eliminar`

#### EmployeeWorkedHours

- **Listar horas trabajadas**: GET `http://localhost:9000/api/EmployeeWorkedHoursWs/listar`

- **Buscar horas trabajadas por ID**: POST `http://localhost:9000/api/EmployeeWorkedHoursWs/buscar`

- **Guardar horas trabajadas**: POST `http://localhost:9000/api/EmployeeWorkedHoursWs/guardar`

- **Editar horas trabajadas**: POST `http://localhost:9000/api/EmployeeWorkedHoursWs/editar`

- **Eliminar horas trabajadas**: POST `http://localhost:9000/api/EmployeeWorkedHoursWs/eliminar`

## 🤝 Contribución

Si deseas contribuir a este proyecto, sigue estos pasos:

1. Haz un fork del repositorio.

2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).

3. Haz tus cambios y commitea (`git commit -m 'Añade nueva funcionalidad`).

4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).

5. Abre un Pull Request.

## 📄 Licencia
Este proyecto está bajo la licencia MIT. Consulta el archivo LICENSE para más detalles.

## 📫 Contacto
Iván Jiménez - LinkedIn - ivanrjpl12@gmail.com


