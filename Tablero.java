package monopoly;

import partida.*;
import java.util.ArrayList;
import java.util.HashMap;


public class Tablero {
    //Atributos.
    private ArrayList<ArrayList<Casilla>> posiciones; //Posiciones del tablero: se define como un arraylist de arraylists de casillas (uno por cada lado del tablero).
    private HashMap<String, Grupo> grupos; //Grupos del tablero, almacenados como un HashMap con clave String (será el color del grupo).
    private Jugador banca; //Un jugador que será la banca.

    //Constructor: únicamente le pasamos el jugador banca (que se creará desde el menú).
    public Tablero(Jugador banca) {
        this.banca = banca;
        this.posiciones = new ArrayList<>();
        this.grupos = new HashMap<>();
        this.generarCasillas(); //Llamamos al método que genera las casillas del tablero.
    }

    
    //Método para crear todas las casillas del tablero. Formado a su vez por cuatro métodos (1/lado).
    private void generarCasillas() {
        this.insertarLadoSur();
        this.insertarLadoOeste();
        this.insertarLadoNorte();
        this.insertarLadoEste();
    }
    
    //Método para insertar las casillas del lado sur.
    private void insertarLadoSur() { // Precio solares grupo marron y azul. Marron -> 600000. Azul -> 1000000 3 y 4, 1200000 5
        ArrayList<Casilla> ladoSur = new ArrayList<>();
        this.posiciones.add(ladoSur);

        // Creamos las casillas del lado sur:
        Casilla salida = new Casilla("Salida", "Especial", 1, 0, banca);
        ladoSur.add(salida);

        Casilla solar1 = new Casilla("Solar1", "Solar", 2, 600000, banca);
        ladoSur.add(solar1);
        
        Casilla caja = new Casilla("Caja", "Comunidad", 3, banca);
        ladoSur.add(caja);

        Casilla solar2 = new Casilla("Solar2", "Solar", 4, 600000, banca);
        ladoSur.add(solar2);

        Casilla imp1 = new Casilla("Imp1", 5, 0, banca);
        ladoSur.add(imp1);

        Casilla trans1 = new Casilla("Trans1", "Transporte", 6, 500000, banca);
        ladoSur.add(trans1);

        Casilla solar3 = new Casilla("Solar3", "Solar", 7, 1000000, banca);
        ladoSur.add(solar3);

        Casilla suerte = new Casilla("Suerte1", "Suerte", 8, banca);
        ladoSur.add(suerte);

        Casilla solar4 = new Casilla("Solar4", "Solar", 9, 1000000, banca);
        ladoSur.add(solar4);

        Casilla solar5 = new Casilla("Solar5", "Solar", 10, 1200000, banca);
        ladoSur.add(solar5);

        Casilla carcel = new Casilla("Carcel", "Especial", 11, 0, banca);
        ladoSur.add(carcel);

    }


    private void insertarLadoOeste() { // Precio solares grupo rpsa y salmon. Rosa -> 1400000 6 y 7, 1600000 8. Salmón -> 1800000 9 y 10, 2200000 11
        ArrayList<Casilla> ladoOeste = new ArrayList<>();
        this.posiciones.add(ladoOeste);
        
        //Creamos las casillas del lado Oeste:
        Casilla Solar6 = new Casilla("Solar6", "Solar", 12, 1400000, banca);
        ladoOeste.add(Solar6);

        Casilla Serv1 = new Casilla("Serv1", "Servicio", 13, 500000, banca);
        ladoOeste.add(Serv1);

        Casilla Solar7 = new Casilla("Solar7", "Solar", 14, 1400000, banca);
        ladoOeste.add(Solar7);

        Casilla Solar8 = new Casilla("Solar8", "Solar", 15, 1600000, banca);
        ladoOeste.add(Solar8);

        Casilla Trans2 = new Casilla("Trans2", "Transporte", 16, 500000, banca);
        ladoOeste.add(Trans2);

        Casilla Solar9 = new Casilla("Solar9", "Solar", 17, 1800000, banca);
        ladoOeste.add(Solar9);

        Casilla Caja = new Casilla("Caja", "Caja de comunidad", 18);
        ladoOeste.add(Caja);

        Casilla Solar10 = new Casilla("Solar10", "Solar", 19, 1800000, banca);
        ladoOeste.add(Solar10);

        Casilla Solar11 = new Casilla("Solar11", "Solar", 20, 2200000, banca);
        ladoOeste.add(Solar11);
    }
    

    //Método para insertar las casillas del lado norte.
    private void insertarLadoNorte() { // Precio solares grupo rojo y amarillo. Rojo -> 2200000 12 y 13, 2400000 14. Amarillo -> 2600000 15 y 16, 2800000 17
        
    }
    

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() { //Precio solares verde y morado. Verde -> 3.000.000 18 y 19, 3.200.000 20, Morado -> 3.500.000 21 y 4.000.000 22
        ArrayList<Casilla> ladoEste = new ArrayList<>();
        this.posiciones.add(ladoEste);

        Casilla solar18 = new Casilla("Solar18", "Solar", 32, 3000000, banca);
        ladoEste.add(solar18);

        Casilla 
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {
    }
    
    //Método usado para buscar la casilla con el nombre pasado como argumento:
    public Casilla encontrar_casilla(String nombre){
    }
}
