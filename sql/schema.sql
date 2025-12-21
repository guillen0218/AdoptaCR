-- =====================================================
-- BASE DE DATOS
-- =====================================================
DROP DATABASE IF EXISTS adoptacr;
CREATE DATABASE adoptacr
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE adoptacr;

-- =====================================================
-- TABLA: USUARIO
-- =====================================================
CREATE TABLE usuario (
  id_usuario BIGINT PRIMARY KEY AUTO_INCREMENT,
  correo VARCHAR(120) NOT NULL UNIQUE,
  contrasena VARCHAR(120) NOT NULL,
  nombre VARCHAR(120) NOT NULL,
  telefono VARCHAR(20),
  rol VARCHAR(40) NOT NULL DEFAULT 'ADOPTANTE',
  activo BOOLEAN NOT NULL DEFAULT TRUE,
  fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
-- TABLA: MASCOTA
-- =====================================================
CREATE TABLE mascota (
  id_mascota BIGINT PRIMARY KEY AUTO_INCREMENT,
  nombre VARCHAR(120) NOT NULL,
  raza VARCHAR(120) NOT NULL,
  edad INT,
  sexo ENUM('MACHO','HEMBRA'),
  descripcion TEXT,
  ubicacion VARCHAR(120) NOT NULL,
  estado VARCHAR(40) NOT NULL DEFAULT 'DISPONIBLE',
  id_usuario BIGINT,
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_mascota_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

-- =====================================================
-- TABLA: SOLICITUD DE ADOPCIÓN
-- =====================================================
CREATE TABLE solicitud_adopcion (
  id_solicitud BIGINT PRIMARY KEY AUTO_INCREMENT,
  id_usuario BIGINT NOT NULL,
  id_mascota BIGINT NOT NULL,
  fecha_solicitud DATE NOT NULL,
  estado VARCHAR(40) NOT NULL DEFAULT 'PENDIENTE',
  observaciones VARCHAR(255),
  CONSTRAINT fk_solicitud_usuario
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
  CONSTRAINT fk_solicitud_mascota
    FOREIGN KEY (id_mascota) REFERENCES mascota(id_mascota)
);

-- =====================================================
-- TABLA: FOTO DE MASCOTA
-- =====================================================
CREATE TABLE foto_mascota (
  id_foto BIGINT PRIMARY KEY AUTO_INCREMENT,
  id_mascota BIGINT NOT NULL,
  url VARCHAR(255) NOT NULL,
  CONSTRAINT fk_foto_mascota
    FOREIGN KEY (id_mascota) REFERENCES mascota(id_mascota)
);

-- =====================================================
-- DATOS DE EJEMPLO
-- =====================================================

-- USUARIOS
INSERT INTO usuario (correo, contrasena, nombre, telefono, rol) VALUES
('demo@adopta.cr', 'demo123', 'Usuario Demo', '88888888', 'ADOPTANTE'),
('refugio@adopta.cr', 'refugio123', 'Refugio Central', '87777777', 'RESCATISTA')
ON DUPLICATE KEY UPDATE nombre = VALUES(nombre);

-- MASCOTAS
INSERT INTO mascota (nombre, raza, edad, sexo, descripcion, ubicacion, estado, id_usuario) VALUES
('Luna','Labrador',3,'HEMBRA','Perra juguetona y cariñosa','San José','DISPONIBLE',2),
('Max','Poodle',4,'MACHO','Tranquilo y obediente','Heredia','DISPONIBLE',2),
('Toby','Beagle',5,'MACHO','Activo y curioso','Cartago','ADOPTADO',2),
('Nala','Schnauzer',2,'HEMBRA','Muy sociable','Alajuela','DISPONIBLE',2);

-- FOTOS
INSERT INTO foto_mascota (id_mascota, url) VALUES
(1,'luna.jpg'),
(2,'max.jpg'),
(3,'toby.jpg'),
(4,'nala.jpg');

-- SOLICITUDES DE ADOPCIÓN
INSERT INTO solicitud_adopcion (id_usuario, id_mascota, fecha_solicitud, estado) VALUES
(1, 1, CURDATE(), 'PENDIENTE'),
(1, 2, CURDATE(), 'APROBADA');

-- =====================================================
-- CONSULTAS DE VERIFICACIÓN
-- =====================================================

-- Mascotas disponibles
SELECT nombre, raza, ubicacion
FROM mascota
WHERE estado = 'DISPONIBLE';

-- Solicitudes con información completa
SELECT 
  u.nombre AS adoptante,
  m.nombre AS mascota,
  s.estado,
  s.fecha_solicitud
FROM solicitud_adopcion s
JOIN usuario u ON s.id_usuario = u.id_usuario
JOIN mascota m ON s.id_mascota = m.id_mascota;
