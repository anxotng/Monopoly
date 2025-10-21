package partida;

import monopoly.*;

import java.util.ArrayList;
import java.util.Random;


public class Avatar {

    //Atributos
    private char id; //Identificador: una letra generada aleatoriamente.
    private String tipo; //Sombrero, Esfinge, Pelota, Coche
    private Jugador jugador; //Un jugador al que pertenece ese avatar.
    private Casilla lugar; //Los avatares se sitúan en casillas del tablero.

    //Constructor vacío
    public Avatar() {
    }

    /*Constructor principal. Requiere éstos parámetros:
    * Tipo del avatar, jugador al que pertenece, lugar en el que estará ubicado, y un arraylist con los
    * avatares creados (usado para crear un ID distinto del de los demás avatares).
     */
    public Avatar(String tipo, Jugador jugador, Casilla lugar, ArrayList<Avatar> avCreados) {
        this.tipo = tipo;
        this.jugador = jugador;
        this.lugar = lugar;
        this.generarId(avCreados); //Llamamos al método que genera un ID para el avatar.
    }
    //Getters y Setters:
    public char getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Casilla getLugar() {
        return lugar;
    }

    public void setLugar(Casilla lugar) {
        this.lugar = lugar;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setId(char id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //A continuación, tenemos otros métodos útiles para el desarrollo del juego.
    /*Método que permite mover a un avatar a una casilla concreta. Parámetros:
    * - Un array con las casillas del tablero. Se trata de un arrayList de arrayList de casillas (uno por lado).
    * - Un entero que indica el numero de casillas a moverse (será el valor sacado en la tirada de los dados).
    * EN ESTA VERSIÓN SUPONEMOS QUE valorTirada siemrpe es positivo.
     */
    public void moverAvatar(ArrayList<ArrayList<Casilla>> casillas, int valorTirada) {
        int posActual = lugar.getPosicion(); //Posición actual del avatar
        lugar.eliminarAvatar(this); //Eliminamos el avatar de la casilla en la que estaba.
        int nuevaPos = (posActual + valorTirada) % 40; //Calculamos la nueva posición del avatar.


        // Determinar el nuevo lugar según la nueva posición
        if (nuevaPos <= 11) { // Sur (1-11)
            lugar = casillas.get(0).get(nuevaPos - 1);
        } 
        else if (nuevaPos >= 12 && nuevaPos <= 20) { // Oeste (12-20)
            lugar = casillas.get(1).get(nuevaPos - 12);
        } 
        else if (nuevaPos >= 21 && nuevaPos <= 31) { // Norte (21-31)
            lugar = casillas.get(2).get(nuevaPos - 21);
        } 
        else { // Este (32-40)
            lugar = casillas.get(3).get(nuevaPos - 32);
        }

        lugar.anhadirAvatar(this);

    }

    /*Método que permite generar un ID para un avatar. Sólo lo usamos en esta clase (por ello es privado).
    * El ID generado será una letra mayúscula. Parámetros:
    * - Un arraylist de los avatares ya creados, con el objetivo de evitar que se generen dos ID iguales.

    Cada jugador tendrá asignado un avatar y cada avatar estará identificado por uno de los 256 caracteres ASCII
    y su identificador se decide automáticamente de forma aleatoria.*/
    private void generarId(ArrayList<Avatar> avCreados) {
        boolean idRepetido;
        char idPosible;
        Random random = new Random();
        do {
            idRepetido = false; // de momento no ha sido repetido
            idPosible = (char) ('A' + random.nextInt(26)); // genera letra mayúscula aleatoria
            for (Avatar a : avCreados) { //Comprobamos que no se haya generado ya ese ID.
                if (a.getId() == idPosible) {
                    idRepetido = true;
                    break;
                }
            }
        } while (idRepetido); //Si se ha repetido, volvemos a generar otro ID. 
        id = idPosible;
    }

    @Override
    public String toString(){
        return "&" + id;
    }
}

