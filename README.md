# Acortador de URLs y Redimensionador de imagenes
###
Servicio para acortar urls y redimensionar imagenes
####
## Instalación

##Prerequisitos
``` 
Tener instalado Docker
Tener instalado Maven
Tener instalada y configurada la jdk 17.
```

###Pasos para instación
#### 1. Deployar la última versión de mysql.
``` 
docker pull mysql:5.7
```
#### 2. Crear una red de docker para comunicar la aplicación y la base de datos.
``` 
Utilice uno de estos dos comandos:

  docker network create mynet

  docker network create --driver nat mynet  (si utiliza windows puede que debar usar este comando)
```
#### 3. Ejecutar este comando para correr la imagen de MySQL en un container
``` 
docker run -it --name mysqldb --network=mynet -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=farmu -e MYSQL_USER=sys -e MYSQL_PASSWORD=1234 -d mysql:5.7
```
#### 4. Desplegar la aplicación en un container
 
 - Descargar código fuente desde el repositorio de Git.
 - Pararse en el directorio raiz y ejecutar el comando: mvn clean package -DskipTests
 - Construir la imagen de docker con el siguiente comando:
```    
    docker build -t farmu .
 ```
 - Ejecutar la imagen de docker en un container:
 ````
     docker run --network=mynet --name farmu-container -p 8080:8080 -d farmu
 ````



#Testing

- Creación de una url corta a partir de una url larga:

 ```
curl --location --request POST 'http://localhost:8080/shortener-url/short-url' \
--header 'Content-Type: application/json' \
--data-raw '{
"url": "https://www.farmu.com.co/url-muy-larga-v3"
}'
 ```

- Obtener una url larga a partir de una url corta

 ```
curl --location --request GET 'http://localhost:8080/shortener-url/short-url/f6a01563'
 ```

- Redimensionar una imagen (la imagen se pasa en base64). 

 ```
curl --location --request POST 'http://localhost:8080/resizer/resize' \
--header 'Content-Type: application/json' \
--data-raw '{
    "width": 400,
    "height" : 200,
    "image" : "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"
}'
 ```
