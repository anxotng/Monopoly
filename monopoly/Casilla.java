package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.List;

public class Casilla {

    // Atributos:
    private String nombre;    // Nombre de la casilla
    private String tipo;      // "Solar", "Especial", "Transporte", "Servicio", "Comunidad", "Suerte", "Impuesto", ...
    private long valor;       // Precio de compra / bote (Parking)
    private int posicion;     // Posición en el tablero (1..40)
    private Jugador duenho;   // Dueño de la casilla (por defecto: banca)
    private Grupo grupo;      // Grupo (si es solar)
    private long impuesto;    // Canon/impuesto según tipo
    private long hipoteca;    // Valor de hipoteca (futuras partes)
    private ArrayList<Avatar> avatares; // Avatares situados en esta casilla

    // ================== Constructores ==================
    public Casilla() {
        this.avatares = new ArrayList<>();
    }

    /** Constructor para Solar / Servicio / Transporte. */
    public Casilla(String nombre, String tipo, int posicion, long valor, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.duenho = duenho;
        this.avatares = new ArrayList<>();
    }

    /** Constructor para casillas de tipo Impuesto. */
    public Casilla(String nombre, int posicion, long impuesto, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = "Impuesto";
        this.posicion = posicion;
        this.impuesto = impuesto;
        this.duenho = duenho;
        this.avatares = new ArrayList<>();
    }

    /** Constructor para Suerte / Comunidad / Especiales. */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.duenho = duenho;
        this.avatares = new ArrayList<>();
    }

    // ================== Getters / Setters ==================
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }

    public int getPosicion() { return posicion; }
    public void setPosicion(int posicion) { this.posicion = posicion; }

    public long getValor() { return valor; }
    public void setValor(long valor) { this.valor = valor; }

    public long getImpuesto() { return impuesto; }
    public void setImpuesto(long impuesto) { this.impuesto = impuesto; }

    public long getHipoteca() { return hipoteca; }
    public void setHipoteca(long hipoteca) { this.hipoteca = hipoteca; }

    public Grupo getGrupo() { return grupo; }
    public void setGrupo(Grupo g) { this.grupo = g; }

    // Propietario (usado por Menu/Tablero)
    public Jugador getPropietario() { return duenho; }
    public void setPropietario(Jugador duenho) { this.duenho = duenho; }

    // Avatares presentes
    public List<Avatar> getAvatares() { return avatares; }

    // ================== Gestión de avatares en la casilla ==================
    /** Añade el avatar si no está ya presente. */
    public void anhadirAvatar(Avatar av) {
        if (av == null) return;
        if (this.avatares == null) this.avatares = new ArrayList<>();
        if (!this.avatares.contains(av)) {
            this.avatares.add(av);
        }
    }

    /** Elimina el avatar si está presente. */
    public void eliminarAvatar(Avatar av) {
        if (av == null || this.avatares == null) return;
        this.avatares.remove(av);
    }

    // ================== Utilidades mínimas Parte 1 ==================
    /** Suma valor (útil para Parking/botes en fases futuras). */
    public void sumarValor(long suma) {
        if (suma > 0) this.valor += suma;
    }

    /** Comprar desde la banca (placeholder Parte 1: lógica de efectos no requerida aún). */
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        // Implementación completa queda para partes posteriores.
        // Aquí dejamos el esqueleto por compatibilidad de interfaz.
        // Ejemplo (futuro):
        // if (!"Solar".equalsIgnoreCase(tipo) && !"Transporte".equalsIgnoreCase(tipo) && !"Servicio".equalsIgnoreCase(tipo)) return;
        // if (duenho != banca) return;
        // if (!solicitante.pagar(valor)) return;
        // banca.cobrar(valor);
        // setPropietario(solicitante);
        // solicitante.anhadirPropiedad(this);
    }

    // (Opcional) Métodos de información textual podem ser adicionados em partes futuras
    // public String infoCasilla() { ... }
    // public String casEnVenta() { ... }
}
