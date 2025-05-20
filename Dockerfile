FROM eclipse-temurin:17-jdk
 
WORKDIR /app
 
# Copia el código fuente y el conector JDBC
COPY granja/ /app/granja
COPY libs/ /app/libs
 
# Crear carpeta para .class
RUN mkdir -p /app/bin
 
# Compilar el código Java usando el JAR como classpath
RUN javac -cp "libs/*" -d /app/bin $(find granja -name "*.java")
 
# Ejecutar la app con el classpath correcto
CMD ["java", "-cp", "libs/*:bin", "main.Main"]