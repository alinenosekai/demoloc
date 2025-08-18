
# 📦 demoloc

Un projet Spring Boot qui permet la location simplifiée de véhicules.

## 🔧 Fonctionnalités

- API RESTful
- Accès base de données avec Spring Data JPA
- Documentation Swagger/OpenAPI


## 🏁 Prérequis

- Java 21
- Maven

## 🚀 Démarrage rapide

### 1. Lancer l’application
Compile et démarre avec ./mvn spring-boot:run ou via un IDE acceptant un projet MAVEN en java 21.

### 2. Console H2 (BDD) : 
http://localhost:8080/h2-console (url jdbc = jdbc:h2:mem:testdb)

### 3. Description des API de l'application : 
http://localhost:8080/swagger-ui/index.html
Exemple:
Accède à http://localhost:8080/cars/available pour voir les véhicules disponibles.

### 4. Test simple à effectuer: 
http://localhost:8080/test/coffee


