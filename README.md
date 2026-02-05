# HySpain Security System - Mod de Puertas Vinculadas

Sistema de seguridad desarrollado para el reto técnico de **HySpain**. Implementado en Java 21 utilizando Gradle como gestor de construcción.

## Características Técnicas
- **Vinculación por UUID**: Los datos de propiedad se asocian al ID único del jugador, garantizando seguridad persistente incluso ante cambios de nombre.
- **Validación de PIN Robusta**: Implementación de expresiones regulares (RegEx) para asegurar que los códigos de acceso cumplan con el estándar de 4 dígitos numéricos.
- **Arquitectura de Datos**: Uso de una clase interna `DoorData` para facilitar la expansión futura (listas de amigos, múltiples niveles de acceso).

## Cómo Compilar
Si deseas recompilar el proyecto, utiliza el Gradle Wrapper incluido:
```bash
./gradlew build