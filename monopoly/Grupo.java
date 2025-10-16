package monopoly;

import partida.Jugador;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Grupo {

    // Atributos
    private ArrayList<Casilla> miembros; // Casillas miembros del grupo.
    private String colorGrupo;           // Color del grupo (e.g., "rojo", "azul", ...)
    private int numCasillas;            // Número de casillas del grupo.

    // Constructor vacío.
    public Grupo() {
        this.miembros = new ArrayList<>();
        this.colorGrupo = "blanco";
        this.numCasillas = 0;
    }

    /* Constructor para cuando el grupo está formado por DOS CASILLAS:
     * Requiere como parámetros las dos casillas miembro y el color del grupo.
     */
    public Grupo(Casilla cas1, Casilla cas2, String colorGrupo) {
        this.miembros = new ArrayList<>();
        this.miembros.add(cas1);
        this.miembros.add(cas2);
        this.colorGrupo = colorGrupo;
        this.numCasillas = 2;
    }

    /* Constructor para cuando el grupo está formado por TRES CASILLAS:
     * Requiere como parámetros las tres casillas miembro y el color del grupo.
     */
    public Grupo(Casilla cas1, Casilla cas2, Casilla cas3, String colorGrupo) {
        this.miembros = new ArrayList<>();
        this.miembros.add(cas1);
        this.miembros.add(cas2);
        this.miembros.add(cas3);
        this.colorGrupo = colorGrupo;
        this.numCasillas = 3;
    }

    // Getters y utilidades
    public String getColorGrupo() {
        return colorGrupo;
    }

    /** Nombre del color (para usar con Valor.getColor(String) o Valor.getColor(Grupo)). */
    public String getColorNombre() {
        return colorGrupo == null ? "blanco" : colorGrupo;
    }

    /** Código ANSI del color del grupo (útil al imprimir en consola). */
    public String getColorAnsi() {
        return Valor.getColor(getColorNombre());
    }

    public int getNumCasillas() {
        return numCasillas;
    }

    public List<Casilla> getMiembros() {
        return Collections.unmodifiableList(miembros);
    }

    /* Añade una casilla al grupo. */
    public void anhadirCasilla(Casilla miembro) {
        if (miembro == null) return;
        this.miembros.add(miembro);
        this.numCasillas = this.miembros.size();
    }

    /* true si el jugador es dueño de todas las casillas del grupo. */
    public boolean esDuenhoGrupo(Jugador jugador) {
        if (jugador == null || miembros.isEmpty()) return false;
        for (Casilla c : miembros) {
            // Ajusta este getter si tu clase Casilla usa otro nombre (p. ej., getDuenho()).
            Jugador prop = c.getPropietario();
            if (prop == null || !prop.equals(jugador)) return false;
        }
        return true;
    }
}
