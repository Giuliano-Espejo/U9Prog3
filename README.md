# Sistema de Gestión de Pedidos 🛒

Trabajo Práctico - Programación III - Spring Boot

## 📋 Descripción

Sistema de gestión de pedidos desarrollado con Spring Boot que implementa las mejores prácticas de arquitectura en capas, inyección de dependencias y uso de DTOs con Records.

## 🏗️ Arquitectura

El proyecto sigue una arquitectura en capas:

```
com.gestion.pedidos/
├── entity/              # Entidades JPA
│   ├── Usuario
│   ├── Pedido
│   ├── DetallePedido
│   ├── Producto
│   └── Categoria
├── dto/                 # Data Transfer Objects (Records)
│   ├── usuario/
│   ├── pedido/
│   ├── detallepedido/
│   ├── producto/
│   └── categoria/
├── repository/          # Repositorios Spring Data JPA
│   ├── UsuarioRepository
│   ├── PedidoRepository
│   ├── DetallePedidoRepository
│   ├── ProductoRepository
│   └── CategoriaRepository
└── service/            # Lógica de negocio
    ├── UsuarioService
    ├── PedidoService
    ├── ProductoService
    └── CategoriaService
```



## ✨ Características Principales

### 1. Inyección de Dependencias
- Uso de inyección por constructor con `@RequiredArgsConstructor` de Lombok
- Implementación de estereotipos correctos (`@Service`, `@Repository`, `@Component`)

### 2. DTOs con Records
Todos los DTOs están implementados como Records de Java con métodos de mapeo:

```java
public record CategoriaCreate(String nombre, String descripcion) {
    public Categoria toEntity() {
        return new Categoria(this.nombre, this.descripcion);
    }
}

public record CategoriaResponse(Long id, String nombre, String descripcion) {
    public static CategoriaResponse fromEntity(Categoria categoria) {
        return new CategoriaResponse(
            categoria.getId(),
            categoria.getNombre(),
            categoria.getDescripcion()
        );
    }
}
```

### 4. Carga de Datos Inicial
`DataLoader` implementa `CommandLineRunner` para inicializar:
- ✅ 2 Usuarios
- ✅ 3 Categorías
- ✅ 10 Productos
- ✅ 3 Pedidos (con al menos 2 detalles cada uno)

## 🚀 Tecnologías Utilizadas

- **Spring Boot 3.2.2**
- **Spring Data JPA**
- **Spring Web**
- **H2 Database** (base de datos en memoria)
- **Lombok**
- **Spring Boot DevTools**
- **Java 17**
- **Gradle 8.5** (sistema de construcción)

## 📦 Dependencias

```gradle
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- h2database
- lombok
- spring-boot-devtools
```

## ⚙️ Configuración

### application.properties

```properties
# H2 Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```


## 📊 Datos de Prueba

Al iniciar la aplicación, se cargan automáticamente:

### Usuarios
1. Juan Pérez - juan.perez@email.com
2. María García - maria.garcia@email.com

### Categorías
1. Electrónica
2. Ropa
3. Alimentos

### Productos (10 en total)
- Laptop HP, Mouse Logitech, Teclado Mecánico (Electrónica)
- Camiseta Nike, Pantalón Jeans, Zapatillas Adidas (Ropa)
- Café Premium, Chocolate Amargo, Galletas Integrales, Jugo de Naranja (Alimentos)

### Pedidos
1. **Pedido 1** (Juan) - 3 detalles - Estado: PENDIENTE
2. **Pedido 2** (Juan) - 2 detalles - Estado: CONFIRMADO
3. **Pedido 3** (María) - 3 detalles - Estado: EN_PREPARACION
