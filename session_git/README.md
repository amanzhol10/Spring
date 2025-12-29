Session Project (Spring Boot)

1 Запуск проекта 
Требования
- Java 17+
- PostgreSQL
- Gradle
- Dependencies

2 Настройка базы данных

CREATE DATABASE library_db;

3 application.properties
spring.application.name=session
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/library_db
spring.datasource.username=root
spring.datasource.password=spring10
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=none

spring.liquibase.enabled=true
spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml

4 Запуск проекта

-открыть SessionApplication
-нажать Run
-Приложение стартует на:

http://localhost:8080


 Миграции (Liquibase)

Liquibase запускается автоматически при старте приложения.

Структура миграций:

db/
└── changelog/
├── create-users.xml
├── create-permissions.xml
├── create-user-permissions.xml
├── create-books.xml
├── create-authors.xml
├── create-categories.xml
├── create-reservations.xml
├── create-user-permissions.xml
├── insert-users.xml
├── insert-books.xml
├── insert-authors.xml
├── insert-categories.xml
├── insert-permissions.xml
├── insert-user-permissions.xml
└── db.changelog-master.xml


При старте:

создаются таблицы

добавляются роли (ROLE_ADMIN, ROLE_USER, ROLE_LIBRARIAN)

добавляются тестовые пользователи

 Аутентификация и авторизация

Используется JWT (Bearer Token).

 Логин
POST /api/auth/login


Пример запроса:

{
"username": "admin",
"password": "admin123"
}


Ответ:

eyJhbGciOiJIUzI1NiJ9...


 Токен передаётся в заголовке:

Authorization: Bearer <JWT_TOKEN>

 Роли и доступы
Роль	Возможности
ROLE_USER	регистрация, просмотр книг, бронирование
ROLE_LIBRARIAN	управление книгами и бронированиями
ROLE_ADMIN	управление пользователями, ролями
 Регистрация пользователя
POST /api/users/register


Доступно без авторизации.
Пользователю автоматически назначается ROLE_USER (другие роли назначает только ADMIN).

Пример:

{
"username": "newUser",
"password": "user123"
}


 Архитектура проекта
controller  → REST API
service     → бизнес-логика
repository  → доступ к данным (JPA)
entity      → сущности БД
dto         → DTO для API
mapper      → MapStruct
security    → JWT, фильтры, конфигурация
config      → SecurityConfig

Архитектурные принципы:

REST

DTO (Entity не возвращаются напрямую)

Stateless (JWT)

Role-based access control

Clean separation of layers