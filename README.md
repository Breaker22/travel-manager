# travel-manager
Servicios de viajes

## Descripcion del proyecto
Servicios para controllador de mejores viajes

## Url de Swagger
```
http://localhost:8080/swagger-ui/index.html
```

## Url de Health Check
```
http://localhost:8080/actuator/health
```

## Ejecucion de proyecto
```
1) Descargar el proyecto con git clone
2) Instalar mvn, java 17 y docker
3) Abrir una consola cmd y ejecutar lo siguiente:

mvn clean install
docker build -t travel-manager .
docker run -p8080:8080 travel-manager:latest
```

## Dependencias
* Spring 3.3.1
* Open Api
* Actuator
* H2