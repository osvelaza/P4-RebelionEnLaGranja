# P4-RebelionEnLaGranja
# 🐮 Granja Digital - Sistema de Gestión

## 📌 Descripción
Este proyecto es un **sistema de gestión para una granja digital**, diseñado para registrar, monitorear y administrar animales, empleados y actividades diarias de manera eficiente. La información se almacena en **MySQL**, con respaldo en archivos `.txt` y `.csv`.

## 🎯 Características principales
✔ **Gestión de animales** (registro, consulta, edición y eliminación).  
✔ **Administración de empleados** (alta, baja y consulta de personal).  
✔ **Registro de actividades** (ordeñe, alimentación, vacunación, etc.).  
✔ **Base de datos relacional** con `JDBC`.  
✔ **Logs y respaldo diario de información** en archivos `.txt/.csv`.  
✔ **Interfaz por consola** con menús intuitivos.

---
## 🚀 Instalación y configuración

### 1️⃣ **Requisitos previos**
- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [MySQL Server](https://dev.mysql.com/downloads/)
- [MySQL Workbench](https://www.mysql.com/products/workbench/)
- Eclipse o cualquier **IDE compatible con Java**.

### 2️⃣ **Clonar el repositorio**
```sh
git clone https://github.com/TU_USUARIO/Granja-Digital.git
cd Granja-Digital

## 3️⃣ **Configurar la base de datos**
Abrir MySQL Workbench y ejecutar el script SQL (granja.sql) incluido en el proyecto.

Actualizar las credenciales en utilidades.ConexionBD.java:
java
String url = "jdbc:mysql://localhost:3306/granja";
String usuario = "root";
String contrasena = "TU_CONTRASEÑA";
Verificar conexión ejecutando Main.java.

## 4️⃣ **Ejecutar el sistema**
En la terminal de tu IDE, corre:
sh
javac main/Main.java
java main.Main

##📂 **Estructura del proyecto**
📦 Granja-Digital
 ┣ 📂 controlador
 ┃ ┣ 📜 ActividadControlador.java
 ┃ ┣ 📜 AnimalDAO.java
 ┃ ┣ 📜 EmpleadoControlador.java
 ┣ 📂 modelo
 ┃ ┣ 📜 Actividad.java
 ┃ ┣ 📜 Animal.java
 ┃ ┣ 📜 Empleado.java
 ┣ 📂 utilidades
 ┃ ┣ 📜 ConexionBD.java
 ┣ 📂 vista
 ┃ ┣ 📜 MenuActividad.java
 ┃ ┣ 📜 MenuAnimal.java
 ┃ ┣ 📜 MenuEmpleado.java
 ┃ ┣ 📜 MenuPrincipal.java
 ┃ ┣ 📜 SubMenuActividad.java
 ┃ ┣ 📜 SubMenuAnimal.java
 ┃ ┣ 📜 SubMenuEmpleado.java
 ┣ 📂 main
 ┃ ┣ 📜 Main.java
 ┣ 📜 README.md
 ┣ 📜 granja.sql
