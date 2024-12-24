# Recetas App 

Este es un proyecto de Android de prueba que sigue una arquitectura de multimódulo utilizando Clean Architecture y el patrón MVVM (Model-View-ViewModel). El proyecto incluye una prueba unitaria para un caso de uso específico.

## Librerias principales del proyecto
MockK, Hilt, Room, Retrofit, Coroutines

## Estructura del Proyecto

El proyecto está organizado en los siguientes módulos para seguir el principio de separación de responsabilidades:

- **app**: Contiene la configuración principal de la aplicación, las actividades/fragments, los ViewModels y la lógica de presentación.
- **data**: Maneja la capa de datos, incluyendo repositorios y fuentes de datos (API, base de datos local, etc.).
- **domain**: Contiene los casos de uso (use cases) y las entidades del dominio.

## Arquitectura

Este proyecto sigue los principios de Clean Architecture y el patrón MVVM para asegurar un código limpio y mantenible.

### Clean Architecture

- **Data Layer**: Responsable de la obtención de datos (API, base de datos, etc.) y la implementación de repositorios.
- **Domain Layer**: Contiene los casos de uso (use cases) que representan las reglas de negocio.
- **App Layer**: Maneja la lógica de presentación, la interacción con la UI a través de ViewModels y configuración de la aplicación

### MVVM (Model-View-ViewModel)

- **Model**: Representa los datos y las reglas de negocio.
- **View**: La interfaz de usuario que observa cambios en el ViewModel.
- **ViewModel**: Actúa como un intermediario entre la Vista y el Modelo, gestionando la lógica de presentación.

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/mychael29/recetas.git
