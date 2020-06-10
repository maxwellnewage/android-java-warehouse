# Android Warehouse v0.1
No tengo idea como llegaste, pero bienvenido/a!  La idea de este repo es que sea mi navaja suiza. Vas a encontrar distintas demos de código de cosas que voy haciendo y reutilizo.

## Detalles Técnicos
- compileSdkVersion / targetSdkVersion 29
- buildToolsVersion "29.0.2"
- minSdkVersion 16
- IDE Android Studio v3.6.3

## Instalación
Este proyecto fue pensado para clonarse directamente desde File -> New -> Project from Version Control -> Git.

## Demos
Cada Activity implica una demo distinta. Se agregan en una lista mediante un RecyclerView, las cuales se pueden probar a medida que se van sumando nuevas demos.

- Demo Frutas: Se guarda una lista de objetos en SharedPreferences como json usando la librería Gson.
- Demo Bitmap - Base 64: Dos métodos para trabajar con la conversión de Bitmap a Base64 y viceversa.
