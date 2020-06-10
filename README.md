# Android Warehouse v0.1
No tengo idea como llegaste, pero bienvenido/a!  La idea de este repo es ahorrarle dolores de cabeza al Maxi del futuro, cortesía del Maxi del presente/pasado :)
Vas a encontrar distintas demos de código de cosas que voy haciendo y reutilizando.

## Detalles Técnicos
- compileSdkVersion / targetSdkVersion 29
- buildToolsVersion "29.0.2"
- minSdkVersion 16
- IDE Android Studio v3.6.3

## Instalación
Este proyecto fue pensado para clonarse directamente desde File -> New -> Project from Version Control -> Git.

## Demos
Cada Activity implica una demo distinta. Se agregan en una lista mediante un RecyclerView, las cuales se pueden probar a medida que se van sumando nuevas demos.

- Frutas: Se guarda una lista de objetos en SharedPreferences como json usando la librería Gson.
- Bitmap - Base 64: Dos métodos para trabajar con la conversión de Bitmap a Base64 y viceversa.
- Geolocalización: Implementación de LocationManager para manejar geolocalización devolviendo latitud y longitud.
- Simple Camera: Intent a la camara nativa de android y captura de imagen en Bitmap.
- Retrofit & Glide Demo: Servicios con Retrofit, carga de imagenes via URL con Glide y una API de gatos, necesitas algo mas?
