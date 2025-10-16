package partida;

import monopoly.*;

import java.util.ArrayList;
import java.util.Random;

public class Avatar {

    // Atributos
    private char id;            // Identificador: letra gerada aleatoriamente (única)
    private String tipo;        // Sombrero, Esfinge, Pelota, Coche
    private Jugador jugador;    // Dono do avatar
    private Casilla lugar;      // Casilla atual

    // Constructor vacío
    public Avatar() {
    }

    /* Constructor principal.
     * tipo do avatar, jogador dono, casilla inicial e lista de avatares já criados (p/ evitar ID repetido).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.tipo = tipo;
        this.jugador = jugador;
        this.lugar = lugar;
        this.generarId(avCreados);
        // Garante que o avatar aparece listado na casilla inicial
        if (this.lugar != null) {
            this.lugar.anhadirAvatar(this);
        }
    }

    // ===== Getters/Setters =====
    public char getId() { return id; }
    public String getTipo() { return tipo; }
    public Jugador getJugador() { return jugador; }
    public Casilla getLugar() { return lugar; }
    /** Define a casilla do avatar sem aplicar efeitos de jogo (útil para cárcel). */
    public void setLugar(Casilla nueva) {
        this.lugar = nueva;
    }

    // ===== Movimento =====
    /* Move o avatar 'valorTirada' casas adiante no tabuleiro (posições 1..40).
     * Regras incluídas:
     * - se cruzar a Saída (40 -> 1), jogador recebe Valor.SUMA_VUELTA e incrementa voltas.
     * - atualiza a lista de avatares das casillas (remove da antiga, adiciona na nova).
     * NÃO aplica efeitos da casilla (isso vai no motor/turno).
     */
    public void moverAvatar(ArrayList<ArrayList<Casilla>> casillas, int valorTirada) {
        if (casillas == null || casillas.isEmpty() || lugar == null || valorTirada == 0) return;

        int posActual = lugar.getPosicion(); // 1..40
        int posDestino = posActual + valorTirada;

        boolean pasoSalida = false;
        // Normalmente os dados não passam de 12, mas deixo robusto para >40
        while (posDestino > 40) {
            posDestino -= 40;
            pasoSalida = true;
        }

        // Bonus por passar pela Saída
        if (pasoSalida) {
            if (jugador != null) {
                jugador.cobrar(Valor.SUMA_VUELTA);
                jugador.incrementarVueltas();
            }
        }

        // Encontrar a casilla destino
        Casilla destino = buscarPorPosicion(casillas, posDestino);
        if (destino == null) return;

        // Atualizar presença do avatar nas casillas
        if (this.lugar != null) {
            this.lugar.eliminarAvatar(this);
        }
        destino.anhadirAvatar(this);

        // Mover
        this.lugar = destino;
    }

    /** Busca uma casilla pelo número de posição (1..40) dentro da estrutura de lados. */
    private Casilla buscarPorPosicion(ArrayList<ArrayList<Casilla>> casillas, int posicion) {
        for (ArrayList<Casilla> lado : casillas) {
            for (Casilla c : lado) {
                if (c != null && c.getPosicion() == posicion) return c;
            }
        }
        return null;
    }

    // ===== Geração de ID =====
    /* Gera um ID maiúsculo único (A..Z) distinto dos já existentes em avCreados.
       O enunciado menciona 256 ASCII; se quiser, depois expandimos para mais caracteres. */
    private void generarId(ArrayList<Avatar> avCreados) {
        boolean idRepetido;
        char idPosible;
        Random random = new Random();
        do {
            idRepetido = false;
            idPosible = (char) ('A' + random.nextInt(26)); // letra maiúscula aleatória
            if (avCreados != null) {
                for (Avatar a : avCreados) {
                    if (a.getId() == idPosible) {
                        idRepetido = true;
                        break;
                    }
                }
            }
        } while (idRepetido);
        id = idPosible;
        if (avCreados != null) avCreados.add(this);
    }

    @Override
    public String toString(){
        return "&" + id;
    }
}
