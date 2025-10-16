package partida;

import java.util.ArrayList;
import java.util.List;

import monopoly.*;

public class Jugador {

    // Atributos:
    private String nombre;           // Nombre del jugador
    private Avatar avatar;           // Avatar del jugador
    private float fortuna;           // Dinero
    private float gastos;            // Gastos acumulados
    private boolean enCarcel;        // true si está en la cárcel
    private int tiradasCarcel;       // intentos en cárcel (futuras partes)
    private int vueltas;             // nº de vueltas al tablero
    private ArrayList<Casilla> propiedades; // Propiedades del jugador

    // ================== Constructores ==================
    /** Constructor vacío: se usa para crear la BANCA. */
    public Jugador() {
        this.nombre = "Banca";
        this.avatar = null;
        this.fortuna = Valor.FORTUNA_BANCA;
        this.gastos = 0f;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.propiedades = new ArrayList<>();
    }

    /** Constructor principal (jugador normal). */
    public Jugador(String nombre, String tipoAvatar, Casilla inicio, ArrayList<Avatar> avCreados) {
        this.nombre = nombre;
        this.fortuna = Valor.FORTUNA_INICIAL;
        this.gastos = 0f;
        this.enCarcel = false;
        this.tiradasCarcel = 0;
        this.vueltas = 0;
        this.propiedades = new ArrayList<>();
        // crea el avatar y lo coloca en la casilla de inicio (agrega también en la casilla)
        this.avatar = new Avatar(tipoAvatar, this, inicio, avCreados);
    }

    // ================== Getters / Setters ==================
    public String getNombre() { return nombre; }
    public Avatar getAvatar() { return avatar; }
    public float getFortuna() { return fortuna; }
    public float getGastos() { return gastos; }
    public boolean isEnCarcel() { return enCarcel; }
    public int getTiradasCarcel() { return tiradasCarcel; }
    public int getVueltas() { return vueltas; }
    public List<Casilla> getPropiedades() { return propiedades; }

    public void setEnCarcel(boolean enCarcel) { this.enCarcel = enCarcel; }
    public void setAvatar(Avatar avatar) { this.avatar = avatar; }

    // ================== Propiedades ==================
    /** Añade una propiedad al jugador (si no estaba ya). */
    public void anhadirPropiedad(Casilla casilla) {
        if (casilla == null) return;
        if (!this.propiedades.contains(casilla)) {
            this.propiedades.add(casilla);
        }
    }

    /** Elimina una propiedad del jugador (si estaba). */
    public void eliminarPropiedad(Casilla casilla) {
        if (casilla == null) return;
        this.propiedades.remove(casilla);
    }

    // ================== Dinero / Vueltas ==================
    /** Suma (o resta si negativo) fortuna del jugador. */
    public void sumarFortuna(float valor) {
        this.fortuna += valor;
        if (valor < 0) this.gastos += (-valor);
    }

    /** Suma gastos (estadística). */
    public void sumarGastos(float valor) {
        if (valor > 0) this.gastos += valor;
    }

    /** Cobra una cantidad (alias de sumarFortuna positiva). */
    public void cobrar(float cantidad) {
        if (cantidad > 0) this.fortuna += cantidad;
    }

    /**
     * Paga una cantidad. Devuelve true si alcanza con la fortuna,
     * false si quedaría en negativo (no gestionamos bancarrota en Parte 1).
     */
    public boolean pagar(float cantidad) {
        if (cantidad <= 0) return true;
        if (this.fortuna >= cantidad) {
            this.fortuna -= cantidad;
            this.gastos += cantidad;
            return true;
        } else {
            // En Parte 1 no resolvemos bancarrota; dejamos negativo si se usara.
            this.fortuna -= cantidad;
            this.gastos += cantidad;
            return false;
        }
    }

    /** Incrementa el contador de vueltas. */
    public void incrementarVueltas() {
        this.vueltas++;
    }

    // ================== Cárcel ==================
    /**
     * Envía al jugador a la casilla "Carcel".
     * - Marca flags de cárcel
     * - Elimina el avatar de su casilla actual
     * - Coloca el avatar en "Carcel" y lo añade allí
     */
    public void encarcelar(ArrayList<ArrayList<Casilla>> pos) {
        if (pos == null) return;

        // localizar "Carcel"
        Casilla carcel = null;
        outer:
        for (ArrayList<Casilla> lado : pos) {
            for (Casilla c : lado) {
                if (c != null && "Carcel".equalsIgnoreCase(c.getNombre())) {
                    carcel = c;
                    break outer;
                }
            }
        }
        if (carcel == null) return;

        // flags de cárcel
        this.enCarcel = true;
        this.tiradasCarcel = 0;

        // mover avatar con limpieza en la casilla anterior
        if (this.avatar != null) {
            Casilla actual = this.avatar.getLugar();
            if (actual != null) {
                actual.eliminarAvatar(this.avatar); // quita de SolarX (o donde esté)
            }
            this.avatar.setLugar(carcel);           // actualiza referencia
            carcel.anhadirAvatar(this.avatar);      // añade en "Carcel"
        }
    }
}
