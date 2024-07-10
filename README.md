# Eco_Recicla

# Contexto

Nuestra propuesta busca implementar un centro de reciclaje innovador en la ciudad de Bogotá, donde operamos una flota de vehículos estratégicamente ubicados. Mediante una aplicación móvil, los usuarios pueden notificar cuando han acumulado una cantidad significativa de materiales reciclables. El vehículo más cercano recibe la alerta, etiqueta la recogida y se dirige al lugar indicado para recolectar los materiales. Una vez que los reciclables llegan a nuestra planta, se pesan y clasifican, registrando automáticamente los kilos en la cuenta del usuario. Además, se asignan "coins" a cada usuario
como recompensa por su contribución al reciclaje. Este sistema no solo optimiza la logística de recolección, sino que también incentiva y facilita a los usuarios el seguimiento y mejora continua de sus hábitos de reciclaje, fomentando un comportamiento más sostenible en Bogotá.

Esta propuesta está pensada para las personas que no se dedican al reciclaje, que viven en viviendas y apartamentos, y que cuentan con dos canecas de desperdicios: una para reciclables y otra para otro tipo de basura. De esta manera, buscamos involucrar a más ciudadanos en la práctica del reciclaje, haciéndolo accesible y conveniente para todos.

**_Nota_ :** _Este problema es totalmente ipotetico porque no tenemos ningun cliente o empresa que este interesada en el proyecto._


# Levantamiento de requerimientos.
El proyecto comenzó con el planteamiento del problema para entender la situación y las posibles necesidades. Después, se procedió a descartar y aprobar los casos de uso potenciales. Se desarrolló una flor de loto para identificar los módulos y las posibles pantallas según los requisitos. Durante este proceso, surgió la complejidad del proyecto al definirse tres tipos de usuarios: usuario general, conductor de vehículo y empresa. Por cuestiones de tiempo, se decidió enfocarse únicamente en el desarrollo para el usuario general.

Posteriormente, se llevó a cabo una planificación de sprint y se creó el backlog  con la ayuda de Jira. El objetivo de esta primera entrega es diseñar, implementar y facilitar la navegación entre las diversas funcionalidades.

| **Flor de loto** | **Cronograma Sprint 1** |
|:----------------:|:-----------------------:|
| <img src="/assets/florLoto.png" width="400"> | <img src="/assets/jira.png" width="400"> |


## Instalación GIT
- 1.Crear una carpeta en el ordenador en donde se almacenara el proyecto. 
- 2.Entrar en ella
- 3.Dar clic derecho y ir a Mostrar Mas Opciones.
- 4.Abri Git Bash
- 5.Si su rama local es diferente de main escriba el siguiente comando: git branch -m main
- 6.Insertar el comando git clone https://github.com/JonathanCamiloDuarteGomez/Eco_Recicla.git
- 7.Ingrese a la nueva carpeta que se genero.
- 8.Dentro de esta carpeta abra de nuevo el **bash**
- 6.realice un pull al gitHub para comprobar que todo esta en orden, ejecute el siguiente comando: git pull origin main --allow-unrelated-histories
- 7.Crear tu propia rama local: git checkout -b <tu nombre>
## Crear tu rama en el repositorio en la web GITHUB
- 8.Asegúrese de que esta sobre su rama en el **git Bash**
- 9.git push origin (nombre de la rama)
- 10.Si necesitas cambiar entre ramas utiliza git checkout <nombreRama>

## Guardar los cambios realizados en tu repositorio local git

- 1.Estar seguro de estar en la rama que necesitas
- 2.escribir **git status**
- 3.visualizar los documentos afectados que estan en rojo
- 4.cuando ya estes seguro agregar los cambios a tu repositorio con **git add .** //Esto agrega todos los archivos, por tanto puedes agregar de a uno con git add <nombreArchivo.txt>
- 5.escribir git status y visualizar que todo lo que desea agregar esta en verde
- 6.crear el commit con **git commit -am"Este es el mensaje que describe el cambio"**
- 7.ir a la rama dev  **git checkout dev**
- 8.hacer un pull a la rama dev con el comando **git pull origin dev**
- 9.luego si ubicarse en su rama personal y hacer un push con el comando git push origin **(mi rama)**
- 10.Dar notificacion al grupo de **_whatsapp_**
- 11._La rama main esta bloqueda para hacer push._


## Créditos

Desarrollado por:
- Guillermo Segura
- Jonathan Camilo Duarte Gomez
- Henry Castañeda
- Hernando Stiven Cortes Lopez
- Ivan Correa
- Jhon Fabian Rodriguez


