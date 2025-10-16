# Proyecto Monopoly ETSE – Parte 1

Implementación básica del tablero y lógica mínima del juego Monopoly para la asignatura de POO (Parte 1).
Incluye impresión ASCII del tablero con colores por grupo, avatares por casilla y un pequeño intérprete de comandos.

# Requisitos

JDK 17 o superior (probado con Microsoft OpenJDK 17).

Terminal/Bash/PowerShell.

Estructura del proyecto
Monopoly/
├─ monopoly/        # Código del dominio: Tablero, Casilla, Grupo, Valor, Menu, MonopolyETSE
└─ partida/         # Código de partida: Jugador, Avatar, Dado

# Compilación

Sitúate en la carpeta raíz del proyecto (donde están las carpetas monopoly y partida).

Linux/macOS:

    cd Monopoly
    javac -encoding UTF-8 -d out monopoly/*.java partida/*.java


Windows (si javac está en el PATH):

    cd Monopoly
    javac -encoding UTF-8 -d .\out .\monopoly\*.java .\partida\*.java


Windows (ruta completa al JDK 17):

    cd Monopoly
    
    & "C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot\bin\javac.exe" -encoding UTF-8 -d .\out .\monopoly\*.java .\partida\*.java


Esto generará los .class en la carpeta out/.

# Ejecución

Linux/macOS:

    java -cp out monopoly.MonopolyETSE


Windows (PATH):

    java -cp .\out monopoly.MonopolyETSE


Windows (ruta completa al JDK 17):

    & "C:\Program Files\Microsoft\jdk-17.0.16.8-hotspot\bin\java.exe" -cp .\out monopoly.MonopolyETSE

# Comandos básicos (en la consola del juego)
    crear jugador Alice Coche
    crear jugador Bob Sombrero
    ver tablero
    lanzar dados
    lanzar dados 3+3 (forzado)
    acabar turno
    listar jugadores
    listar avatares
    salir


Nota: el tablero imprime Norte y Sur como filas de 11 celdas y Oeste/Este como columnas alineadas; las casillas de grupo se pintan (ANSI) y se muestran hasta 2 avatares por casilla.

# Solución de problemas

javac o java no se reconocen:

Verifica que el JDK esté instalado y agregado al PATH. 

Comprueba:

    java -version
    javac -version


Errores de compilación por rutas:

Asegúrate de estar en la carpeta raíz del proyecto (Monopoly) antes de compilar.