-- CREACION DE TABLAS

CREATE DATABASE IF NOT EXISTS stockbarracas;
USE stockbarracas;

CREATE TABLE proveedores (
    cuit_proveedor VARCHAR(14) NOT NULL,
    nombre_proveedor VARCHAR(100) PRIMARY KEY,
    direccion VARCHAR(150),
    telefono VARCHAR(30),
    mail VARCHAR(150),
    web VARCHAR(150),
    categoria VARCHAR(30)
);

CREATE TABLE articulos (
    codigo_articulo VARCHAR(20) PRIMARY KEY,
    nombre_articulo VARCHAR(100) NOT NULL,
    area_usuaria varchar(30) not NULL,
    stock_actual INT not null,
    UM varchar(30) NOT NULL
);

CREATE TABLE ordenes_compra (
    tipo_oc ENUM('CDB','CDS') not NULL,
    numero_oc SMALLINT PRIMARY KEY,
    nombre_proveedor VARCHAR(100) not null,
    fecha DATE NOT NULL,
    total DECIMAL(12, 2) NOT NULL,
    moneda ENUM('PESOS','DOLARES') not NULL,
    descripcion VARCHAR(100) not null,
    FOREIGN KEY (nombre_proveedor) REFERENCES proveedores(nombre_proveedor) ON UPDATE CASCADE
);

CREATE TABLE facturas (
    numero_factura INT PRIMARY KEY,
    nombre_proveedor VARCHAR(100) not null,
    fecha DATE NOT NULL,
    total DECIMAL(12, 2) NOT NULL,
    tipo_oc ENUM('CDB','CDS','AUTORIZADO') not NULL,
    numero_oc SMALLINT,
    descripcion VARCHAR(100) not null,
    FOREIGN KEY (nombre_proveedor) REFERENCES proveedores(nombre_proveedor) ON UPDATE CASCADE,
    FOREIGN KEY (numero_oc) REFERENCES ordenes_compra(numero_oc)
);

CREATE TABLE inventario (
    fecha DATE NOT NULL,
    codigo_articulo VARCHAR(20) NOT NULL,
    nombre_articulo VARCHAR(100) NOT NULL,
    area_usuaria varchar(30),
    cantidad INT NOT NULL,
    UM varchar(30) NOT NULL,
    movimiento ENUM('INGRESO','EGRESO','EXISTENCIA') not NULL,
    stock_fecha INT NOT NULL,
    observaciones VARCHAR(150),
    FOREIGN KEY (codigo_articulo) REFERENCES articulos(codigo_articulo)
);
   
-- INSERTAR DATOS

-- Insertar datos en la tabla proveedores
INSERT INTO proveedores (cuit_proveedor, nombre_proveedor, direccion, telefono, mail, web, categoria)
VALUES 
('20-12345678-9', 'Proveedor A', 'Calle Falsa 123', '123456789', 'contacto@proveedora.com', 'www.proveedora.com', 'Electrónica'),
('20-98765432-1', 'Proveedor B', 'Calle Real 456', '987654321', 'contacto@proveedorb.com', 'www.proveedorb.com', 'Ropa');

-- Insertar datos en la tabla articulos
INSERT INTO articulos (codigo_articulo, nombre_articulo, area_usuaria, stock_actual, UM)
VALUES 
('ART001', 'Televisor', 'Electrónica', 10, 'Unidad'),
('ART002', 'Camiseta', 'Ropa', 50, 'Unidad');

-- Insertar datos en la tabla ordenes_compra
INSERT INTO ordenes_compra (tipo_oc, numero_oc, nombre_proveedor, fecha, total, moneda, descripcion)
VALUES 
('CDB', '0001', 'Proveedor A', '2024-10-10', 10000.00, 'PESOS', 'Compra de artículos electrónicos'),
('CDS', '0002', 'Proveedor B', '2024-10-11', 5000.00, 'DOLARES', 'Compra de ropa');

-- Insertar datos en la tabla facturas
INSERT INTO facturas (numero_factura, nombre_proveedor, fecha, total, tipo_oc, numero_oc, descripcion)
VALUES 
(1, 'Proveedor A', '2024-10-12', 9500.00, 'CDB', '0001', 'Factura por compra de electrónicos'),
(2, 'Proveedor B', '2024-10-13', 4800.00, 'CDS', '0002', 'Factura por compra de ropa');

-- Insertar datos en la tabla inventario
INSERT INTO inventario (fecha, codigo_articulo, nombre_articulo, area_usuaria, cantidad, UM, movimiento, stock_fecha, observaciones)
VALUES 
('2024-10-12', 'ART001', 'Televisor', 'Electrónica', 10, 'Unidad', 'INGRESO', 10, 'Ingreso inicial de stock'),
('2024-10-13', 'ART002', 'Camiseta', 'Ropa', 50, 'Unidad', 'INGRESO', 50, 'Ingreso inicial de stock');