package partida;

import java.util.Random;


public class Dado {
    //El dado solo tiene un atributo en nuestro caso: su valor.
    private int valor;
    private Random random = new Random();
    
    //Contructor para inicializar un dado
    public Dado() {
    }

    public int getValorDado(){
        return valor;
    }

    public void setValorDado(int valorDado){
        this.valor = valorDado;
    }

    //Metodo para simular lanzamiento de un dado: devolverá un valor aleatorio entre 1 y 6.
    public int hacerTirada() {
        return random.nextInt(6) + 1; //Generamos un valor entre 1 y 6
    }
}
