package partida;

import java.util.Random;

public class Dado {

    private int valor; // Último valor tirado
    private static final Random random = new Random();

    // Constructor
    public Dado() {
        this.valor = 1;
    }

    /** Lança o dado e devolve um valor aleatório entre 1 e 6. */
    public int hacerTirada() {
        this.valor = random.nextInt(6) + 1; // 1..6
        return this.valor;
    }

    /** Getter para consultar o último valor do dado. */
    public int getValor() {
        return this.valor;
    }

    /** Lança dois dados e devolve um objeto com os resultados e se houve doble. */
    public ResultadoDoble tirarPar() {
        int d1 = hacerTirada();
        int d2 = hacerTirada();
        return new ResultadoDoble(d1, d2);
    }

    /** Força valores específicos nos dados (usado no comando “lanzar dados 2+4”). */
    public ResultadoDoble tirarForzado(int d1, int d2) {
        if (d1 < 1 || d1 > 6 || d2 < 1 || d2 > 6) {
            throw new IllegalArgumentException("Los dados deben estar entre 1 y 6");
        }
        return new ResultadoDoble(d1, d2);
    }

    /** Classe auxiliar que representa una tirada doble. */
    public static class ResultadoDoble {
        public final int dado1;
        public final int dado2;

        public ResultadoDoble(int d1, int d2) {
            this.dado1 = d1;
            this.dado2 = d2;
        }

        public boolean esDoble() {
            return dado1 == dado2;
        }

        public int suma() {
            return dado1 + dado2;
        }

        @Override
        public String toString() {
            return "Dado1: " + dado1 + ", Dado2: " + dado2 + (esDoble() ? " (DOBLES)" : "");
        }
    }
}
