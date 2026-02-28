package com.gestion.pedidos;

import com.gestion.pedidos.dto.categoria.CategoriaCreate;
import com.gestion.pedidos.dto.detallepedido.DetallePedidoCreate;
import com.gestion.pedidos.dto.pedido.PedidoCreate;
import com.gestion.pedidos.dto.producto.ProductoCreate;
import com.gestion.pedidos.dto.usuario.UsuarioCreate;
import com.gestion.pedidos.enums.FormaPago;
import com.gestion.pedidos.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    private final CategoriaService categoriaService;
    private final ProductoService productoService;
    private final UsuarioService usuarioService;
    private final PedidoService pedidoService;

    @Override
    public void run(String... args) throws Exception {
        log.info("========== INICIANDO CARGA DE DATOS DE PRUEBA ==========");

        // 1. Crear 3 Categorías
        log.info("Creando categorías...");
        var categoria1 = categoriaService.crearCategoria(
                new CategoriaCreate("Electrónica", "Dispositivos y componentes electrónicos")
        );
        var categoria2 = categoriaService.crearCategoria(
                new CategoriaCreate("Ropa", "Prendas de vestir y accesorios")
        );
        var categoria3 = categoriaService.crearCategoria(
                new CategoriaCreate("Alimentos", "Productos comestibles y bebidas")
        );
        log.info("✓ Categorías creadas: {}, {}, {}", categoria1.nombre(), categoria2.nombre(), categoria3.nombre());

        // 2. Crear 10 Productos
        log.info("Creando productos...");
        var producto1 = productoService.crearProducto(
                new ProductoCreate("Laptop HP", "Laptop HP Core i5 16GB RAM", 899.99, 15, categoria1.id())
        );
        var producto2 = productoService.crearProducto(
                new ProductoCreate("Mouse Logitech", "Mouse inalámbrico Logitech", 29.99, 50, categoria1.id())
        );
        var producto3 = productoService.crearProducto(
                new ProductoCreate("Teclado Mecánico", "Teclado mecánico RGB", 79.99, 30, categoria1.id())
        );
        var producto4 = productoService.crearProducto(
                new ProductoCreate("Camiseta Nike", "Camiseta deportiva Nike", 39.99, 100, categoria2.id())
        );
        var producto5 = productoService.crearProducto(
                new ProductoCreate("Pantalón Jeans", "Pantalón de mezclilla azul", 49.99, 80, categoria2.id())
        );
        var producto6 = productoService.crearProducto(
                new ProductoCreate("Zapatillas Adidas", "Zapatillas deportivas Adidas", 89.99, 60, categoria2.id())
        );
        var producto7 = productoService.crearProducto(
                new ProductoCreate("Café Premium", "Café colombiano 500g", 12.99, 200, categoria3.id())
        );
        var producto8 = productoService.crearProducto(
                new ProductoCreate("Chocolate Amargo", "Chocolate 70% cacao", 4.99, 150, categoria3.id())
        );
        var producto9 = productoService.crearProducto(
                new ProductoCreate("Galletas Integrales", "Paquete de galletas 300g",3.99, 120, categoria3.id())
        );
        var producto10 = productoService.crearProducto(
                new ProductoCreate("Jugo de Naranja", "Jugo natural 1L",5.99, 90, categoria3.id())
        );
        log.info("✓ 10 Productos creados exitosamente");

        // 3. Crear 2 Usuarios
        log.info("Creando usuarios...");
        var usuario1 = usuarioService.crearUsuario(
                new UsuarioCreate("Juan", "Pérez", "juan.perez@email.com", "555-1234", "Calle Principal 123")
        );
        var usuario2 = usuarioService.crearUsuario(
                new UsuarioCreate("María", "García", "maria.garcia@email.com", "555-5678", "Avenida Central 456")
        );
        log.info("✓ Usuarios creados: {} {}, {} {}", 
                usuario1.nombre(), usuario1.apellido(), 
                usuario2.nombre(), usuario2.apellido());

        // 4. Crear 3 Pedidos (con al menos 2 detalles cada uno)
        log.info("Creando pedidos...");
        
        // Pedido 1 - Usuario 1
        var pedido1 = pedidoService.crearPedido(
                new PedidoCreate(
                        usuario1.id(),
                        FormaPago.EFECTIVO,
                        List.of(
                                new DetallePedidoCreate(1, producto1.id()),
                                new DetallePedidoCreate(2, producto2.id()),
                                new DetallePedidoCreate(1, producto3.id())
                        )
                )
        );
        log.info("✓ Pedido 1 creado - ID: {}, Total: ${}, Detalles: {}", 
                pedido1.id(), pedido1.total(), pedido1.detalles().size());

        // Pedido 2 - Usuario 1
        var pedido2 = pedidoService.crearPedido(
                new PedidoCreate(
                        usuario1.id(),
                        FormaPago.TARJETA,
                        List.of(
                                new DetallePedidoCreate(3, producto3.id()),
                                new DetallePedidoCreate(5, producto8.id())
                        )
                )
        );
        log.info("✓ Pedido 2 creado - ID: {}, Total: ${}, Detalles: {}", 
                pedido2.id(), pedido2.total(), pedido2.detalles().size());

        // Pedido 3 - Usuario 2
        var pedido3 = pedidoService.crearPedido(
                new PedidoCreate(
                        usuario2.id(),
                        FormaPago.TRANSFERENCIA,
                        List.of(
                                new DetallePedidoCreate(2,producto4.id()),
                                new DetallePedidoCreate(1,producto5.id()),
                                new DetallePedidoCreate(1,producto6.id())
                        )
                )
        );
        log.info("✓ Pedido 3 creado - ID: {}, Total: ${}, Detalles: {}", 
                pedido3.id(), pedido3.total(), pedido3.detalles().size());

        log.info("========== CARGA DE DATOS COMPLETADA ==========");
        log.info("Resumen:");
        log.info("  - Categorías: 3");
        log.info("  - Productos: 10");
        log.info("  - Usuarios: 2");
        log.info("  - Pedidos: 3");
        log.info("================================================");
    }
}
