package partida;

import java.util.Random;


public class Dado {
    //El dado solo tiene un atributo en nuestro caso: su valor.
    private Random valor;
    
    //Contructor para inicializar un dado
    public Dado() {
        this.valor = new Random();
    }

    //Metodo para simular lanzamiento de un dado: devolverá un valor aleatorio entre 1 y 6.
    public int hacerTirada() {
        return valor.nextInt(6) + 1; //Generamos un valor entre 1 y 6
    }

    //Método para forzar el valor de los dados
    public int tirarDadoForzado(int valorForzado1, int valorForzado2) {
        if (valorForzado1 < 1 || valorForzado1 > 6 || valorForzado2 < 1 || valorForzado2 > 6) {
            System.out.println("El valor forzado de los dados debe estar entre 1 y 6.");
        }
        int valorTotal = valorForzado1 + valorForzado2;
        return valorTotal;
    }
}
