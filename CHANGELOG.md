# spring-data-redis-starter

# change log

## 09-JUNE-2021

### Documentation

    - Added CHANGELOG.md

### Implementation

    - Added Create Todo

### Configuration

    - Added CORS Support. 

- References:

  [HowTodoInJava - Spring CORS](https://howtodoinjava.com/spring5/webmvc/spring-mvc-cors-configuration/)

  [Baeldung - Spring CORS](https://www.baeldung.com/spring-cors)

## 07-JUNE-2021

### Implementation

    - Developed controller to get all todos through TDD
    - Added basic tests for getAllTodos

## 06-JUNE-2021

### Configuration

    - Created basic spring boot project with redis dependency
    - Using Jedis connector instead of lettuce
    - Added docker compose with redis alpine
    - Configured Redis Connectivity to use the default RedisConnectionFactory

### Feature implementation

    - Created Todo Model and Configured that to be stored as entity in Redis using @RedisHash
