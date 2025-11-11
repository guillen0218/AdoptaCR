-- Esquema básico según el curso (MySQL + Workbench)
CREATE DATABASE IF NOT EXISTS adoptacr CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE adoptacr;

CREATE TABLE IF NOT EXISTS usuario (
  id_usuario BIGINT PRIMARY KEY AUTO_INCREMENT,
  correo VARCHAR(120) NOT NULL UNIQUE,
  contrasena VARCHAR(120) NOT NULL,
  nombre VARCHAR(120) NOT NULL
);

CREATE TABLE IF NOT EXISTS mascota (
  id_mascota BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(120) NOT NULL,
  raza VARCHAR(120) NOT NULL,
  ubicacion VARCHAR(120) NOT NULL,
  estado VARCHAR(40) NOT NULL DEFAULT 'DISPONIBLE'
);

-- Datos de ejemplo
INSERT INTO usuario (correo, contrasena, nombre) VALUES
('demo@adopta.cr', 'demo123', 'Usuario Demo')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

INSERT INTO mascota (nombre, raza, ubicacion, estado) VALUES
('Luna','Labrador','San José','DISPONIBLE'),
('Max','Poodle','Heredia','DISPONIBLE'),
('Toby','Beagle','Cartago','ADOPTADO'),
('Nala','Schnauzer','Alajuela','DISPONIBLE')
;
