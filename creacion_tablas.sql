-- CREACION DE TABLAS

CREATE DATABASE IF NOT EXISTS stockbarracas;
USE stockbarracas;

CREATE TABLE proveedores (
    id_proveedor INT AUTO_INCREMENT PRIMARY KEY,
    nombre_proveedor VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(150)
);

CREATE TABLE categorias (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL
);

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre_producto VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    id_categoria INT,
    id_proveedor INT,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY,
    nombre_cliente VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    direccion VARCHAR(150)
);

CREATE TABLE facturas (
    id_factura INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    id_cliente INT,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE detalles_factura (
    id_detalle_factura INT AUTO_INCREMENT PRIMARY KEY,
    id_factura INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_factura) REFERENCES facturas(id_factura) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ordenes_compra (
    id_orden_compra INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    id_proveedor INT,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE detalles_orden_compra (
    id_detalle_orden_compra INT AUTO_INCREMENT PRIMARY KEY,
    id_orden_compra INT,
    id_producto INT,
    cantidad INT NOT NULL,
    precio_unitario DECIMAL(10, 2) NOT NULL,
    subtotal DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (id_orden_compra) REFERENCES ordenes_compra(id_orden_compra) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE inventario (
    id_inventario INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT,
    cantidad_actual INT NOT NULL,
    fecha_actualizacion DATE NOT NULL,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) ON DELETE CASCADE ON UPDATE CASCADE
);
   
-- INSERTAR DATOS

INSERT INTO proveedores (nombre_proveedor, telefono, direccion)
VALUES ('Proveedor A', '123456789', 'Calle Falsa 123'),
       ('Proveedor B', '987654321', 'Calle Real 456');

INSERT INTO categorias (nombre_categoria)
VALUES ('Electrónica'), ('Ropa'), ('Muebles');

INSERT INTO productos (nombre_producto, descripcion, precio, stock, id_categoria, id_proveedor)
VALUES ('Televisor', 'Televisor 50 pulgadas', 45000.00, 10, 1, 1),
       ('Sofá', 'Sofá de 3 plazas', 12000.00, 5, 3, 2),
       ('Camiseta', 'Camiseta de algodón', 500.00, 20, 2, 1);

INSERT INTO clientes (nombre_cliente, email, telefono, direccion)
VALUES ('Juan Pérez', 'juan.perez@email.com', '111222333', 'Calle ABC 123'),
       ('María García', 'maria.garcia@email.com', '444555666', 'Calle XYZ 456');

INSERT INTO facturas (fecha, total, id_cliente)
VALUES ('2024-10-06', 100000.00, 1),
       ('2024-10-07', 50000.00, 2);

INSERT INTO detalles_factura (id_factura, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 1, 2, 45000.00, 90000.00),
       (1, 3, 1, 500.00, 500.00),
       (2, 2, 1, 12000.00, 12000.00);

INSERT INTO ordenes_compra (fecha, total, id_proveedor)
VALUES ('2024-10-05', 80000.00, 1),
       ('2024-10-06', 20000.00, 2);

INSERT INTO detalles_orden_compra (id_orden_compra, id_producto, cantidad, precio_unitario, subtotal)
VALUES (1, 1, 5, 45000.00, 225000.00),
       (2, 2, 2, 12000.00, 24000.00);

INSERT INTO inventario (id_producto, cantidad_actual, fecha_actualizacion)
VALUES (1, 10, '2024-10-06'),
       (2, 5, '2024-10-06'),
       (3, 20, '2024-10-06');

