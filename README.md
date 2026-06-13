# ⚡ Organizador Automático de Descargas

Un script ligero y eficiente desarrollado en **Java** que monitorea en tiempo real tu carpeta de descargas (`/Downloads`) y clasifica automáticamente los archivos nuevos en carpetas ordenadas según su extensión (ej. `.pdf`, `.jpg`, `.zip`).

Diseñado específicamente con una interfaz gráfica compatible con entornos modernos de Linux (como CachyOS, Arch, Ubuntu bajo Wayland/X11), Windows y macOS.

---

## 🚀 Características

* **Monitoreo en Tiempo Real:** Utiliza la API nativa `WatchService` de Java, lo que significa que el programa no consume CPU mientras espera archivos; se activa únicamente cuando ocurre una descarga.
* **Multithreading (Multi-hilo):** Separa la interfaz gráfica del proceso de monitoreo para evitar que la ventana se congele.
* **Filtro Inteligente contra Temporales:** Ignora automáticamente los archivos parciales e incompletos de los navegadores (`.crdownload` de Chrome/Edge o `.part` de Firefox).
* **Cierre Seguro Garantizado:** Al cerrar la ventana del programa, el proceso de Java se destruye por completo de la memoria RAM de forma automática.

---

## 🛠️ Requisitos

* **Java JDK 11** o superior instalado en el sistema.
* Un entorno de desarrollo (como VS Code con la extensión de Java, o IntelliJ IDEA) o la terminal.

---
