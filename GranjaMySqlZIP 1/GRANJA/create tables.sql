-- Usar la base de datos
USE granja;

-- Tabla de empleados
CREATE TABLE empleados (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rol VARCHAR(50) NOT NULL, -- veterinario, peón, encargado, etc.
    telefono VARCHAR(20),
    fecha_contratacion DATE NOT NULL
);

-- Tabla de animales
CREATE TABLE animales (
    id INT AUTO_INCREMENT PRIMARY KEY,
    especie VARCHAR(50) NOT NULL,
    raza VARCHAR(50),
    fecha_nacimiento DATE NOT NULL,
    arete VARCHAR(30) UNIQUE NOT NULL, -- identificador único
    estado_salud VARCHAR(50) NOT NULL, -- saludable, enfermo, crítico, etc.
    ubicacion VARCHAR(100),
    estado_actual VARCHAR(50) DEFAULT 'activo' -- vendido, fallecido, trasladado, etc.
);

-- Tabla de actividades
CREATE TABLE actividades (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    tipo_actividad VARCHAR(50) NOT NULL, -- ordeñe, vacunación, etc.
    id_empleado INT NOT NULL,
    FOREIGN KEY (id_empleado) REFERENCES empleados(id)
);

-- Tabla intermedia: animales involucrados en actividades
CREATE TABLE actividad_animal (
    id_actividad INT,
    id_animal INT,
    PRIMARY KEY (id_actividad, id_animal),
    FOREIGN KEY (id_actividad) REFERENCES actividades(id) ON DELETE CASCADE,
    FOREIGN KEY (id_animal) REFERENCES animales(id) ON DELETE CASCADE
);