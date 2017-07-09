# Práctica 12: ANDROID AVANZADO
## Juan A. Caballero
--------------------------------------

La práctica aquí presentada se ha desarrolla dentro del Máster Bootcamp 4ª edición impartido por Keepcoding y corresponde a la unidad 12 del temario. 


OBJETIVO DE LA PRÁCTICA
---------------------------

La práctica consiste en completar el diseño de la app **MadridShops** desarrollada en clase que muestra un listado de tiendas o actividades en Madrid. Se ha amplido la funcionalidad de mostrar tiendas desarrollada en clase añadiendo aquí la funcionalidad de mostrar también actividades.  


FUNCIONALIDAD DE LA APP
---------------------------

La app presenta una primera pantalla con el logo y tres opciones: Shops, Activities y Clear Cache. Las dos primeras nos llevan a una pantalla de tiendas o actividades, según la opción seleccionada, con un mapa centrado en nuestra posición y un pin por cada una en la mitad superior de la pantalla y en la parte inferior un listado de ellas con su logo, imagen de fondo y nombre. 

Pulsando sobre los pins del mapa, podemos ver el nombre de la tienda o actividad, y si pulsamos sobre el nombre nos lleva a una pantalla de detalle de esa tienda o actividad que muestra una imagen de la tienda, la dirección, una descripción y un mapa estático de posición.

Pulsando sobre cualquier tienda o actividad de la lista mostrada en la mitad inferior de la pantalla también nos lleva a la pantalla de vista de detalle de la misma. 


DETALLES DE LA IMPLEMENTACIÓN
--------------------------------------

Se ha partido del código generado en clase por el profesor. No he podido partir de mi código porque tenía algunos errores con las versiones de appcompat-v7, como ya me pasara con la práctica de Android Básico, agravados al cambiar de ordenador en las últimas clases por encontrarme desplazado. Por esa razón, para evitar problemas, he preferido partir de ese código que parece funcionarme casi bien del todo. 

Partiendo del código de clase, se ha generado completamente el código que muestra la lista, mapa y vista en detalle de actividades. Se ha desarrollado siguiendo el esquema visto en clase como puede verse en los commits del proyecto, empezando por el modelo en el módulo domain, para seguir con la lista de actividades y demás usando arquitectura Clean con Interactor y Managers y para acabar con los mapas y algunas correcciones finales tras repasar todos los detalles de funcionamiento de la aplicación.

Se han aprovechado siempre que ha sido posible algunos de los managers de tiendas que se han podido generalizar, así como algunas clases de diseño de celdas y mapas, dado que los JSON de tiendasy actividades y sus modelos tienen la misma estructura. Aunque lo suyo hubiera sido cambiarle el nombre a estos managers para que en lugar de tratar con Shop trataran, por ejemplo, con Model como modelo genérico, no he querido hacerlo para no tener problemas por algún error tipográfico.   

El aprovechamiento de algnos managers para tiendas y actividades ha sido posible principalmente porque la estructura del modelo de entities usado para trabajar con bases de datos es el mismo, y las entities obtenidas en el Interactor se han mapeado en agregado de Shops para GetAllShopsInteractor y en agregado de Activities para GetAllActivitiesInteractor.

Para usar distintas url's según sean Shops o Actividades supongo que se podía haber hecho por inyección de dependencias o, como se ha hecho aquí, usando el contexto para averiguar en qué actividad está y según ésta usar la url de Shops o de Activities.

Del código de tiendas, por tanto, no se han modificado más que algunos detalles estéticos como incluir una foto de fondo en las celdas y los managers que se han podido generalizar para ser usados también para las actividades.  

También se han añadido algunos test adicionales para minimizar bugs, tanto para actividades como alguno también para tiendas, aunque habría que añadir algunos más, pero ya no tengo más tiempo. Todos los test incluidos pasan correctamente. 

También se ha incluido la funcionalidad de BORRADO AL CABO DE UNA SEMANA de los datos de base de datos. Se ha incluido solo en Activities porque no he querido tocar el código de tiendas más que lo justo para tenerlo como referencia. 


OTROS
--------------------------------------

He intentado también personalizar los pins del mapa para que mostraran en lugar de un pin el icono de cada tienda y actividad, pero al usar la clase BitmapDescriptorFactory me he encontrado que solo puede cargar los iconos de disco o Bitmap, por lo que no me ha parecido óptimo descargar todos los logos en disco para usarlos por optimización de recursos si el número de tiendas o actividades fuera muy grande en el futuro. Seguro que hay otra manera de hacerlo pero no he sido capaz de hacerlo y tampoco tengo mucho más tiempo. 

Tampoco he sido capaz de ampliar la foto del fondo de las celdas del Recycler View para que ocupen toda la celda que es lo estéticamente oportuno por más que lo he intentado por código, pues lo hubiera intentado también vía graáfica pero  tengo algún problema con el render y no me muestra la celda.


NOTAS FINALES
--------------------------------------

Me hubiera gustado hacer otra versión de la práctica intentando generalizar aún más todas las clases que hubiera podido, pues viendo que los modelos de tiendas y actividades son iguales seguro que pueden reutilizarse muchas más clases, si no casi todas, aunque supongo que lleva bastante tiempo y ya no lo tengo. 

Si puedo lo haré para aprender, pues me parece muy interesante de cara al futuro usar estructuras lo más estándar y reutilizables posible para distintas fuentes de datos a usar, sobre todo cuando si se tiene la opción de diseñar el backend claro, con estas mismas estructuras estándar.

En cualqueir caso, a pesar de las horas y el esfuerzo que han requerido, he aprendido mucho con estas clases y esta práctica.

Muchas gracias.


