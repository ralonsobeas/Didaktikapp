# Didaktikapp

<p align="center"><img src="https://www.theblocklearning.com/wp-content/uploads/2018/09/mobile.png" alt="Imagen"></p>

Aplicación creada por alumnos del centro CIFP Ciudad Jardin (Vitoria) en colaboración con los alumnos de magisterio de la UPV en Leioa.
La finalidad de la app es que sea usada por niños para hacer una excursión y conocer mejor Oñate.
Esta app consta de varias actividades diferentes que se podrán realizar cuando el dispositivo esté a una distancia pequeña de los monumentos/ lugares a los que se refieren.

<b>Cualquier ayuda es bienvenida. </b>


Application created by students from CIFP Ciudad Jardin (Vitoria/Spain) in collaboration with students from education in UPV in Leioa.
The app's goal is to be used by kids doing a trip and know better Oñate.
This app is made by different activities that can be done when the device is neaer the monuments/ places they refer to.

<b>Any help is welcome.</b>

Hall of Fame
------------------------------------------------------------------------

[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/0)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/0)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/1)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/1)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/2)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/2)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/3)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/3)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/4)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/4)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/5)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/5)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/6)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/6)[![](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/images/7)](https://sourcerer.io/fame/gennakk/gennakk/Didaktikapp/links/7)


APIs Utilizadas
------------------------------------------------------------------------
Mapas -  https://www.mapbox.com/



Seguimiento
------------------------------------------------------------------------
<h4>19/11/2019 Reunidos Todos</h4>

- [x] Primer contacto
- [x] Toma de decisiones
- [x] Compromisos, normas
- [x] Reconocimiento de tareas, recursos faltantes
- [x] Elegir representante

<h4>20/11/2019 Reunidos Todos</h4>

- [x] Toma de decisiones visuales
- [x] Toma de contacto con UPV
- [x] Mandar sugerencias (Correo de contacto)
- [x] Creación de proyecto en github con dos ramas (master y branch1)

<h4>21/11/2019 Trabajo de desarrollo</h4>

- [x] Logos del login (falta el logo de Oñate)
- [x] Login preparado
- [x] Imagenes para Markers
- [x] Implantación del mapa en dispositivo y carga de los Markers necesarios
- [x] Comienzo de implantación de ubicación real del dispositivo (https://docs.mapbox.com/android/maps/examples/show-a-users-location/)
- [x] Ideas de implantación de las actividades a realizar por los usuarios (¿Fragments?)
- [x] Resolución de conflictos en GitHub
- [x] Mejoras del proyecto en github y creación de licencia de uso MIT

<h4>22/11/2019 Trabajo de desarrollo</h4>

- [x] Implantación terminada de ubicación en tiempo real
- [ ] Intento de lanzar fragment mediante ubicación
- [x] Comienzo de implantación de los fragments con las actividades a realizar con algunas funcionalidades no deseadas (¿botón de volver?,¿click fuera?,¿mover el mapa mientras se está en el fragment?)
- [x] Comienzo de actividad con sopa de letras
- [x] Merge de Branch1 con Master #1
- [x] Comienzo de actividad San Miguel (test) Actividad número 2

<h4>25/11/2019 Trabajo de desarrollo</h4>

- [x] Trabajo sobre actividad San Miguel
- [ ] Avances en Sopa de letras
- [x] Animaciones de entrada/salida de fragments
- [x] Animaciones en botones
- [ ] Problemas con la documentación aportada y espera de un correo de respuesta por parte de los alumnos de la UPV
- [ ] Link source code sopa de letras https://github.com/abdularis/Word-Search-Game
- [ ] Link source code puzle https://dragosholban.com/2018/03/09/how-to-build-a-jigsaw-puzzle-android-game/
- [ ] Posible uso de script de python desde java https://github.com/chaquo/chaquopy ; https://github.com/chaquo/chaquopy-console

<h4>26/11/2019 Trabajo de desarrollo</h4>

- [x] Trabajo sobre sopa de letras
- [x] Actividades universidad y san miguel casi terminadas. Falta solucionar videos
- [x] Inicio de puzle
- [x] Creación de algunos estilos comunes entre fragments
- [x] Merge de Branch1 con Master #2

<h4>27/11/2019 Trabajo de desarrollo</h4>

- [x] Problemas con sopa de letras, intento de busqueda de otro repositorio mmas simple
- [x] Puzle terminado y audio al terminarlo incluido
- [x] Trabajo sobre estilos comunes
- [x] Comienzo de trabajo sobre actividad San Miguel Errota


<h4>28/11/2019 Trabajo de desarrollo</h4>

- [x] Sopa de letras eliminada y se trabaja sobre el clone de la sopa de letras original para eliminar todo lo no necesario y que sea funcional
- [x] Trabajo sobre actividad de Tren texto con huecos
- [x] Actividad San Miguel Errota casi terminada


Funcionamiento de la app
------------------------------------------------------------------------


<pre>
stateDiagram
[*] --> Menu
Menu --> [*]
Menu --> Mapa
Mapa --> Fragment
Fragments --> Mapa
Fragment --> Error
Error --> [*]
</pre>


:yum:
