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
        Casilla salida = new Casilla("Salida", "Especial", 1, banca);
        ladoSur.add(salida);

        Casilla solar1 = new Casilla("Solar1", "Solar", 2, 600000, banca);
        ladoSur.add(solar1);
        
        Casilla caja = new Casilla("Caja", "Comunidad", 3, banca);
        ladoSur.add(caja);

        Casilla solar2 = new Casilla("Solar2", "Solar", 4, 600000, banca);
        ladoSur.add(solar2);

        Casilla imp1 = new Casilla("Imp1", 5, 2000000, banca);
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

        Casilla carcel = new Casilla("Carcel", "Especial", 11, banca);
        ladoSur.add(carcel);

        grupos.put("negro", new Grupo(solar1, solar2, "Negro")); // creamos un nuevo grupo en el que añ
        solar1.setGrupo(grupos.get("negro")); // Solar 1
        solar2.setGrupo(grupos.get("negro")); // Solar 2


        grupos.put("azul", new Grupo(solar3, solar4, solar5, "Azul")); // creamos un nuevo grupo en el que añadimos las casillas
        solar3.setGrupo(grupos.get("azul")); // Solar 3
        solar4.setGrupo(grupos.get("azul")); // Solar 4
        solar5.setGrupo(grupos.get("azul")); // Solar 5

        



    }


    private void insertarLadoOeste() { // Precio solares grupo rpsa y salmon. Rosa -> 1400000 6 y 7, 1600000 8. Salmón -> 1800000 9 y 10, 2200000 11
        ArrayList<Casilla> ladoOeste = new ArrayList<>();
        this.posiciones.add(ladoOeste);
        
        //Creamos las casillas del lado Oeste:
        Casilla solar6 = new Casilla("Solar6", "Solar", 12, 1400000, banca);
        ladoOeste.add(solar6);

        Casilla serv1 = new Casilla("Serv1", "Servicio", 13, 500000, banca);
        ladoOeste.add(serv1);

        Casilla solar7 = new Casilla("Solar7", "Solar", 14, 1400000, banca);
        ladoOeste.add(solar7);

        Casilla solar8 = new Casilla("Solar8", "Solar", 15, 1600000, banca);
        ladoOeste.add(solar8);

        Casilla trans2 = new Casilla("Trans2", "Transporte", 16, 500000, banca);
        ladoOeste.add(trans2);

        Casilla solar9 = new Casilla("Solar9", "Solar", 17, 1800000, banca);
        ladoOeste.add(solar9);

        Casilla caja = new Casilla("Caja", "Caja de comunidad", 18, banca);
        ladoOeste.add(caja);

        Casilla solar10 = new Casilla("Solar10", "Solar", 19, 1800000, banca);
        ladoOeste.add(solar10);

        Casilla solar11 = new Casilla("Solar11", "Solar", 20, 2200000, banca);
        ladoOeste.add(solar11);

        grupos.put("rosa", new Grupo(solar6, solar7, solar8, "Rosa")); // creamos un nuevo grupo en el que añadimos las casillas
        solar6.setGrupo(grupos.get("rosa")); // Solar 6
        solar7.setGrupo(grupos.get("rosa")); // Solar 7
        solar8.setGrupo(grupos.get("rosa")); // Solar 8

        grupos.put("salmon", new Grupo(solar9, solar10, solar11, "Salmon")); // creamos un nuevo grupo en el que añadimos las casillas
        solar9.setGrupo(grupos.get("salmon")); // Solar 9
        solar10.setGrupo(grupos.get("salmon")); // Solar 10
        solar11.setGrupo(grupos.get("salmon")); // Solar 11
    }
    

    //Método para insertar las casillas del lado norte.
    private void insertarLadoNorte() { // Precio solares grupo rojo y amarillo. Rojo -> 2200000 12 y 13, 2400000 14. Amarillo -> 2600000 15 y 16, 2800000 17
        ArrayList<Casilla> ladoNorte = new ArrayList<>();
        this.posiciones.add(ladoNorte);

        // Creamos las casillas del lado norte:
        Casilla parking = new Casilla("Parking", "Especial", 21, 0, banca);
        ladoNorte.add(parking);

        Casilla solar12 = new Casilla("Solar12", "Solar", 22, 2200000, banca);
        ladoNorte.add(solar12);
        
        Casilla suerte = new Casilla("Suerte", "Suerte", 23, banca);
        ladoNorte.add(suerte);

        Casilla solar13 = new Casilla("Solar13", "Solar", 24, 220000, banca);
        ladoNorte.add(solar13);

        Casilla solar14 = new Casilla("Solar14", "Solar", 25, 2400000, banca);
        ladoNorte.add(solar14);

        Casilla trans3 = new Casilla("Trans3", "Transporte", 26, 500000, banca);
        ladoNorte.add(trans3);

        Casilla solar15 = new Casilla("Solar15", "Solar", 27, 2600000, banca);
        ladoNorte.add(solar15);

        Casilla solar16 = new Casilla("Solar16", "Solar", 28, 2600000, banca);
        ladoNorte.add(solar16);

        Casilla serv2 = new Casilla("Serv2", "servicio", 29, 500000, banca);
        ladoNorte.add(serv2);

        Casilla solar17 = new Casilla("Solar17", "Solar", 30, 2800000, banca);
        ladoNorte.add(solar17);

        Casilla carcel = new Casilla("Carcel", "Especial", 31, banca);
        ladoNorte.add(carcel);
    }
    

    //Método que inserta las casillas del lado este.
    private void insertarLadoEste() { //Precio solares verde y morado. Verde -> 3.000.000 18 y 19, 3.200.000 20, Morado -> 3.500.000 21 y 4.000.000 22
        ArrayList<Casilla> ladoEste = new ArrayList<>();
        this.posiciones.add(ladoEste);

        //Creamos las casillas del lado este
        Casilla solar18 = new Casilla("Solar18", "Solar", 32, 3000000, banca);
        ladoEste.add(solar18);

        Casilla solar19 = new Casilla("Solar19", "Solar", 33, 3000000, banca);
        ladoEste.add(solar19);

        Casilla caja = new Casilla("Caja", "Caja de comunidad", 34, banca);
        ladoEste.add(caja);

        Casilla solar20 = new Casilla("Solar20", "Solar", 35, 3200000, banca);
        ladoEste.add(solar20);

        Casilla trans4 = new Casilla("Trans4", "Transporte", 36, 500000, banca);
        ladoEste.add(trans4);
        
        Casilla suerte = new Casilla("Suerte", "Suerte", 37, banca);
        ladoEste.add(suerte);

        Casilla solar21 = new Casilla("Solar21", "Solar", 38, 3500000, banca);
        ladoEste.add(solar21);

        Casilla imp2 = new Casilla("Imp2", 39, 2000000, banca);
        ladoEste.add(imp2);

        Casilla solar22 = new Casilla("Solar22", "Solar", 40, 4000000, banca);
        ladoEste.add(solar22);
        
    }

    //Para imprimir el tablero, modificamos el método toString().
    @Override
    public String toString() {
        StringBuilder sb =  new StringBuilder();
      
        // Lado Norte
        sb.append("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        sb.append("| ");
        for (int i = 0; i <= 10; i++) {
            sb.append(String.format("%-12s", colorearCasilla(posiciones.get(2).get(i)))).append("| ");        
        }

        sb.append("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    
        // Lado Oeste y Este
        for (int i = 0; i <= 8; i++) {

            sb.append("| ").append(String.format("%-12s", colorearCasilla(posiciones.get(1).get(8 - i)))).append("|");

            sb.append("                                                                                                                             ");

            sb.append("| ").append(String.format("%-12s", colorearCasilla(posiciones.get(3).get(i)))).append("|\n");

            if (i != 8) {
                sb.append("---------------                                                                                                                             ---------------\n");
            }
        }

        
        // Lado Sur: igual que Norte pero recorriendo posiciones[0] en orden inverso
        sb.append("-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        
        sb.append("| ");
        for (int i = 0; i <= 10; i++) {
            sb.append(String.format("%-12s", colorearCasilla(posiciones.get(0).get(10 - i)))).append("| ");
        }

        sb.append("\n-----------------------------------------------------------------------------------------------------------------------------------------------------------\n");

        return sb.toString();
    }

    //Método para colorear los solares
    public String colorearCasilla(Casilla casilla){
        String color = Valor.getColor(casilla.getGrupo().getColorGrupo());
        return color + casilla.getNombre(); // Queremos que el color solo afecte al nombre de la casilla, y no al texto entero.
    }
    
    //Método usado para buscar la casilla con el nombre pasado como argumento:
    /*public Casilla encontrar_casilla(String nombre){
    }*/
}





