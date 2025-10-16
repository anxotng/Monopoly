package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.List;

public class MonopolyETSE {

    // ===== Estado da partida =====
    private final Jugador banca;
    private final Tablero tablero;
    private final Dado dado = new Dado();

    private final ArrayList<Avatar> avatares = new ArrayList<>();
    private final ArrayList<Jugador> jugadores = new ArrayList<>();
    private int turno = 0;               // índice do jogador atual
    private int doblesSeguidos = 0;      // contador de dobles do jogador atual

    // ===== Inicialização =====
    public MonopolyETSE() {
        this.banca = new Jugador();          // "Banca"
        this.tablero = new Tablero(banca);   // monta o tabuleiro
    }

    public static void main(String[] args) {
    new Menu();  // inicia o loop interativo no console
}


    // ===== API pública (para o Menu) =====
    /** Cria jogador na casilla "Salida" com o avatar do tipo informado. */
    public Jugador crearJugador(String nombre, String tipoAvatar) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("Nombre vacío");
        // evitar nome repetido
        for (Jugador j : jugadores) {
            if (j.getNombre().equalsIgnoreCase(nombre)) {
                throw new IllegalArgumentException("Ya existe un jugador con ese nombre");
            }
        }
        Casilla salida = tablero.encontrar_casilla("Salida");
        if (salida == null) throw new IllegalStateException("No se encontró la casilla Salida");
        Jugador j = new Jugador(nombre, tipoAvatar, salida, avatares);
        jugadores.add(j);
        return j;
    }

    /** Devolve o jogador do turno atual. */
    public Jugador jugador() {
        if (jugadores.isEmpty()) throw new IllegalStateException("No hay jugadores");
        return jugadores.get(turno);
    }

    /** Lista jogadores (nome, avatar, fortuna). */
    public void listarJugadores() {
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

    /** Imprime o tabuleiro (ASCII). */
    public void verTablero() {
        System.out.println(tablero.toString());
    }

    /** Lança dois dados aleatórios e move o jogador atual. Aplica regra de dobles (3º → cárcel). */
    public void lanzarDados() {
        Jugador j = jugador();
        if (j.isEnCarcel()) {
            System.out.println("El jugador está en la cárcel. (Reglas de salida se aplicarán en la Parte 1/2)");
            // aqui depois aplicaremos: pagar 500k o intentar dobles
            return;
        }

        Dado.ResultadoDoble res = dado.tirarPar();
        int pasos = res.suma();
        boolean doble = res.esDoble();

        System.out.println("{ tirada: " + res.toString() + " }");

        // mover avatar
        Avatar av = j.getAvatar();
        if (av != null) {
            av.moverAvatar(tablero.getPosiciones(), pasos);
            System.out.println("El avatar " + av + " avanza " + pasos + " posiciones hasta " + av.getLugar().getNombre());
        }

        // TODO: aplicar efectos de la casilla (alquiler, impuestos, parking, etc.)

        // gestionar dobles
        if (doble) {
            doblesSeguidos++;
            if (doblesSeguidos >= 3) {
                System.out.println("Tercer doble consecutivo → a la Cárcel");
                j.encarcelar(tablero.getPosiciones());
                doblesSeguidos = 0;
                // turno termina al ir a la cárcel
            } else {
                System.out.println("Dobles: el jugador " + j.getNombre() + " vuelve a lanzar.");
                return; // no avanza el turno; sigue el mismo jugador
            }
        } else {
            doblesSeguidos = 0; // se corta la racha de dobles
        }
    }

    /** Lança dois dados forçados (ex.: “2+4”), útil para testes. */
    public void lanzarDados(int d1, int d2) {
        Jugador j = jugador();
        if (j.isEnCarcel()) {
            System.out.println("El jugador está en la cárcel. (Reglas de salida se aplicarán en la Parte 1/2)");
            return;
        }
        Dado.ResultadoDoble res = dado.tirarForzado(d1, d2);
        int pasos = res.suma();
        boolean doble = res.esDoble();

        System.out.println("{ tirada: " + res.toString() + " }");

        Avatar av = j.getAvatar();
        if (av != null) {
            av.moverAvatar(tablero.getPosiciones(), pasos);
            System.out.println("El avatar " + av + " avanza " + pasos + " posiciones hasta " + av.getLugar().getNombre());
        }

        // TODO: aplicar efectos de la casilla

        if (doble) {
            doblesSeguidos++;
            if (doblesSeguidos >= 3) {
                System.out.println("Tercer doble consecutivo → a la Cárcel");
                j.encarcelar(tablero.getPosiciones());
                doblesSeguidos = 0;
            } else {
                System.out.println("Dobles: el jugador " + j.getNombre() + " vuelve a lanzar.");
                return;
            }
        } else {
            doblesSeguidos = 0;
        }
    }

    /** Encerra o turno e passa para o próximo jogador. */
    public void acabarTurno() {
        if (jugadores.isEmpty()) return;
        turno = (turno + 1) % jugadores.size();
        doblesSeguidos = 0;
        System.out.println("Turno para: " + jugador().getNombre());
    }

    // ===== Helpers =====
    public List<Jugador> getJugadores() { return jugadores; }
    public Jugador getBanca() { return banca; }
    public Tablero getTablero() { return tablero; }
}
