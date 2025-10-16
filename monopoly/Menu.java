package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import partida.*;

public class Menu {

    // Atributos
    private ArrayList<Jugador> jugadores;     // Jugadores de la partida
    private ArrayList<Avatar> avatares;       // Avatares en la partida
    private int turno = 0;                    // Índice del jugador que tiene el turno
    private int lanzamientos;                 // nº lanzamientos en el turno (informativo)
    private int doblesSeguidos;               // racha de dobles del jugador actual
    private Tablero tablero;                  // Tablero
    private Dado dado1;                       // Dados físicos (para modo no-forzado)
    private Dado dado2;
    private Jugador banca;                    // Banca
    private boolean tirado;                   // si el jugador ya tiró en este sub-turno
    private boolean solvente;                 // no usado en Parte 1 (placeholder)

    // ====== Construcción / Loop ======
    public Menu() {
        iniciarPartida();
        loopComandos();
    }

    // Método para iniciar una partida: crea banca, tablero, listas y dados
    private void iniciarPartida() {
        this.jugadores = new ArrayList<>();
        this.avatares = new ArrayList<>();
        this.banca = new Jugador();                // "Banca" con fortuna base
        this.tablero = new Tablero(banca);
        this.dado1 = new Dado();
        this.dado2 = new Dado();
        this.turno = 0;
        this.lanzamientos = 0;
        this.doblesSeguidos = 0;
        this.tirado = false;
        this.solvente = true;
        System.out.println("Partida iniciada. Use 'crear jugador <nombre> <tipoAvatar>' para empezar.");
    }

    private void loopComandos() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            if (!sc.hasNextLine()) break;
            String comando = sc.nextLine().trim();
            if (comando.isEmpty()) continue;
            if (comando.equalsIgnoreCase("salir")) {
                System.out.println("Saliendo...");
                break;
            }
            analizarComando(comando);
        }
        sc.close();
    }

    /* Interpreta el comando introducido y toma la acción correspondiente. */
    private void analizarComando(String comando) {
        String lc = comando.toLowerCase();

        // crear jugador <nombre> <tipo>
        if (lc.startsWith("crear jugador")) {
            String[] partes = comando.split("\\s+");
            if (partes.length < 4) {
                System.out.println("Uso: crear jugador <nombre> <tipoAvatar>");
                return;
            }
            String nombre = partes[2];
            String tipo = partes[3];
            Casilla salida = tablero.encontrar_casilla("Salida");
            if (salida == null) {
                System.out.println("Error: no se encontró la casilla 'Salida'.");
                return;
            }
            // evitar duplicado
            for (Jugador j : jugadores) {
                if (j.getNombre().equalsIgnoreCase(nombre)) {
                    System.out.println("Ya existe un jugador con ese nombre.");
                    return;
                }
            }
            Jugador nuevo = new Jugador(nombre, tipo, salida, avatares);
            jugadores.add(nuevo);
            System.out.println("Jugador creado: " + nombre + " (" + tipo + ").");
            if (jugadores.size() == 1) {
                System.out.println("Empieza " + nombre + ".");
            }
            return;
        }

        // jugador (actual)
        if (lc.equals("jugador")) {
            Jugador j = getJugadorActual();
            if (j == null) return;
            System.out.println("Jugador actual: " + j.getNombre());
            return;
        }

        // listar jugadores
        if (lc.equals("listar jugadores")) {
            listarJugadores();
            return;
        }

        // listar avatares
        if (lc.equals("listar avatares")) {
            listarAvatares();
            return;
        }

        // ver tablero
        if (lc.equals("ver tablero")) {
            System.out.println(tablero.toString());
            return;
        }

        // lanzar dados  (o lanzar dados d1+d2)
        if (lc.startsWith("lanzar dados")) {
            // formato opcional: "lanzar dados 2+4"
            String resto = lc.replaceFirst("lanzar dados", "").trim();
            if (resto.isEmpty()) {
                lanzarDados();
            } else {
                String originalResto = comando.replaceFirst("(?i)lanzar dados", "").trim();
                String[] dd = originalResto.split("\\+");
                if (dd.length != 2) {
                    System.out.println("Uso: lanzar dados 2+4");
                    return;
                }
                try {
                    int d1 = Integer.parseInt(dd[0].trim());
                    int d2 = Integer.parseInt(dd[1].trim());
                    lanzarDadosForzado(d1, d2);
                } catch (NumberFormatException e) {
                    System.out.println("Valores de dados inválidos. Use enteros entre 1 y 6.");
                }
            }
            return;
        }

        // acabar turno
        if (lc.equals("acabar turno")) {
            acabarTurno();
            return;
        }

        // comandos de Parte 1 no implementados agora
        if (lc.startsWith("describir jugador")) { descJugador(comando.split("\\s+")); return; }
        if (lc.startsWith("describir avatar"))  { descAvatar(comando.substring("describir avatar".length()).trim()); return; }
        if (lc.startsWith("describir "))        { descCasilla(comando.substring("describir ".length()).trim()); return; }
        if (lc.startsWith("comprar "))          { comprar(comando.substring("comprar ".length()).trim()); return; }
        if (lc.equals("salir carcel"))          { salirCarcel(); return; }
        if (lc.equals("listar enventa"))        { listarVenta(); return; }

        System.out.println("Comando no reconocido.");
    }

    /* describe jugador <nombre> — Placeholder Parte 1 */
    private void descJugador(String[] partes) {
        if (partes.length < 3) { System.out.println("Uso: describir jugador <nombre>"); return; }
        String nombre = partes[2];
        for (Jugador j : jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("{ nombre: " + j.getNombre()
                        + ", fortuna: " + j.getFortuna()
                        + ", avatar: " + (j.getAvatar() != null ? j.getAvatar().toString() : "-")
                        + ", enCarcel: " + j.isEnCarcel()
                        + ", propiedades: " + j.getPropiedades().size()
                        + " }");
                return;
            }
        }
        System.out.println("No existe jugador con nombre '" + nombre + "'.");
    }

    /* describir avatar <ID> — Placeholder Parte 1 */
    private void descAvatar(String ID) {
        if (ID == null || ID.isBlank()) { System.out.println("Uso: describir avatar <ID>"); return; }
        char buscado = ID.trim().toUpperCase().charAt(0);
        for (Avatar a : avatares) {
            if (a.getId() == buscado) {
                System.out.println("{ avatar: &" + a.getId()
                        + ", tipo: " + a.getTipo()
                        + ", jugador: " + (a.getJugador() != null ? a.getJugador().getNombre() : "-")
                        + ", casilla: " + (a.getLugar() != null ? a.getLugar().getNombre() : "-")
                        + " }");
                return;
            }
        }
        System.out.println("No existe avatar con ID '&" + buscado + "'.");
    }

    /* describir <nombre_casilla> — Placeholder Parte 1 */
    private void descCasilla(String nombre) {
        if (nombre == null || nombre.isBlank()) { System.out.println("Uso: describir <nombre_casilla>"); return; }
        Casilla c = tablero.encontrar_casilla(nombre.trim());
        if (c == null) {
            System.out.println("No se encontró la casilla '" + nombre + "'.");
            return;
        }
        String prop = c.getPropietario() == null ? "banca" : c.getPropietario().getNombre();
        String grupo = (c.getGrupo() == null ? "-" : c.getGrupo().getColorGrupo());
        System.out.println("{ nombre: " + c.getNombre()
                + ", tipo: " + c.getTipo()
                + ", posicion: " + c.getPosicion()
                + ", valor: " + c.getValor()
                + ", grupo: " + grupo
                + ", propietario: " + prop
                + " }");
    }

    // ===== Dados / Turno =====
    private void lanzarDados() {
        Jugador actual = getJugadorActual();
        if (actual == null) return;
        if (jugadores.isEmpty()) { System.out.println("No hay jugadores."); return; }
        if (actual.isEnCarcel()) {
            System.out.println("El jugador está en la cárcel (reglas de salida no implementadas en Parte 1).");
            return;
        }

        int d1 = dado1.hacerTirada();
        int d2 = dado2.hacerTirada();
        int suma = d1 + d2;
        boolean doble = (d1 == d2);
        System.out.println("{ tirada: Dado1: " + d1 + ", Dado2: " + d2 + (doble ? " (DOBLES)" : "") + " }");

        Avatar av = actual.getAvatar();
        if (av != null) {
            av.moverAvatar(tablero.getPosiciones(), suma);
            System.out.println("El avatar " + av + " avanza " + suma + " posiciones hasta " + av.getLugar().getNombre());
        }

        // Parte 1: sin efectos de casilla

        // gestión de dobles mínimos
        if (doble) {
            doblesSeguidos++;
            if (doblesSeguidos >= 3) {
                System.out.println("Tercer doble consecutivo → enviado a la Cárcel.");
                actual.encarcelar(tablero.getPosiciones());
                doblesSeguidos = 0;
            } else {
                System.out.println("Dobles: el jugador " + actual.getNombre() + " vuelve a lanzar.");
                // no pasamos el turno
                return;
            }
        } else {
            doblesSeguidos = 0;
        }

        tirado = true;
        lanzamientos++;
    }

    private void lanzarDadosForzado(int d1, int d2) {
        if (d1 < 1 || d1 > 6 || d2 < 1 || d2 > 6) {
            System.out.println("Los dados deben estar entre 1 y 6.");
            return;
        }
        Jugador actual = getJugadorActual();
        if (actual == null) return;
        if (actual.isEnCarcel()) {
            System.out.println("El jugador está en la cárcel (reglas de salida no implementadas en Parte 1).");
            return;
        }

        int suma = d1 + d2;
        boolean doble = (d1 == d2);
        System.out.println("{ tirada: Dado1: " + d1 + ", Dado2: " + d2 + (doble ? " (DOBLES)" : "") + " }");

        Avatar av = actual.getAvatar();
        if (av != null) {
            av.moverAvatar(tablero.getPosiciones(), suma);
            System.out.println("El avatar " + av + " avanza " + suma + " posiciones hasta " + av.getLugar().getNombre());
        }

        // Parte 1: sin efectos de casilla

        if (doble) {
            doblesSeguidos++;
            if (doblesSeguidos >= 3) {
                System.out.println("Tercer doble consecutivo → enviado a la Cárcel.");
                actual.encarcelar(tablero.getPosiciones());
                doblesSeguidos = 0;
            } else {
                System.out.println("Dobles: el jugador " + actual.getNombre() + " vuelve a lanzar.");
                return;
            }
        } else {
            doblesSeguidos = 0;
        }

        tirado = true;
        lanzamientos++;
    }

    // ===== Placeholders de comandos no usados na Parte 1 =====
    private void comprar(String nombre) {
        System.out.println("Compra no implementada en Parte 1.");
    }

    private void salirCarcel() {
        System.out.println("Salida de cárcel no implementada en Parte 1.");
    }

    private void listarVenta() {
        System.out.println("Listado de casillas en venta no implementado en Parte 1.");
    }

    private void listarJugadores() {
        if (jugadores.isEmpty()) { System.out.println("No hay jugadores."); return; }
        System.out.println("{ jugadores: [");
        for (Jugador j : jugadores) {
            System.out.println("  { nombre: " + j.getNombre()
                    + ", avatar: " + (j.getAvatar() != null ? j.getAvatar().toString() : "-")
                    + ", fortuna: " + j.getFortuna()
                    + ", enCarcel: " + j.isEnCarcel()
                    + " }");
        }
        System.out.println("]}");
    }

    private void listarAvatares() {
        if (avatares.isEmpty()) { System.out.println("No hay avatares."); return; }
        System.out.println("{ avatares: [");
        for (Avatar a : avatares) {
            System.out.println("  { id: &" + a.getId()
                    + ", tipo: " + a.getTipo()
                    + ", jugador: " + (a.getJugador() != null ? a.getJugador().getNombre() : "-")
                    + ", casilla: " + (a.getLugar() != null ? a.getLugar().getNombre() : "-")
                    + " }");
        }
        System.out.println("]}");
    }

    // Método que realiza las acciones asociadas al comando 'acabar turno'.
    private void acabarTurno() {
        if (jugadores.isEmpty()) { System.out.println("No hay jugadores."); return; }
        turno = (turno + 1) % jugadores.size();
        lanzamientos = 0;
        doblesSeguidos = 0;
        tirado = false;
        Jugador j = getJugadorActual();
        System.out.println("Turno para: " + j.getNombre());
    }

    // ===== Helpers =====
    private Jugador getJugadorActual() {
        if (jugadores.isEmpty()) {
            System.out.println("No hay jugadores.");
            return null;
        }
        if (turno < 0 || turno >= jugadores.size()) turno = 0;
        return jugadores.get(turno);
    }
}
