package partida;

import java.util.ArrayList;

import monopoly.*;


public class Jugador {

    //Atributos:
    private String nombre; //Nombre del jugador
    private Avatar avatar; //Avatar que tiene en la partida.
    private float fortuna; //Dinero que posee.
    private float gastos; //Gastos realizados a lo largo del juego.
    private boolean enCarcel; //Será true si el jugador está en la carcel
    private int tiradasCarcel; //Cuando está en la carcel, contará las tiradas sin éxito que ha hecho allí para intentar salir (se usa para limitar el numero de intentos).
    private int vueltas; //Cuenta las vueltas dadas al tablero.
    private ArrayList<Casilla> propiedades; //Propiedades que posee el jugador.
    private Tablero tablero;

    //Constructor vacío. Se usará para crear la banca.
    public Jugador() {
        this.propiedades = new ArrayList<Casilla>();
    }

    /*Constructor principal. Requiere parámetros:
    * Nombre del jugador, tipo del avatar que tendrá, casilla en la que empezará y ArrayList de
    * avatares creados (usado para dos propósitos: evitar que dos jugadores tengan el mismo nombre y
    * que dos avatares tengan mismo ID). Desde este constructor también se crea el avatar.
     */
    public Jugador(String nombre, String tipoAvatar, Casilla inicio, ArrayList<Avatar> avCreados) {
        this.nombre = nombre;
        this.propiedades = new ArrayList<Casilla>();
        this.avatar = new Avatar(tipoAvatar, this, inicio, avCreados);
        this.avatar.getLugar().anhadirAvatar(avatar);
        this.fortuna = 15000000;
    }

    //Getters y setters:

    public String getNombre() {
        return nombre;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public float getFortuna() {
        return fortuna;
    }

    public float getGastos() {
        return gastos;
    }

    public boolean estaEnCarcel() {
        return enCarcel;
    }

    public int getTiradasCarcel() {
        return tiradasCarcel;
    }

    public int getVueltas() {
        return vueltas;
    }

    public ArrayList<Casilla> getPropiedades() {
        return propiedades;
    }

    public Tablero getTablero(){
        return tablero;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public void setFortuna(float fortuna) {
        this.fortuna = fortuna;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public void setEnCarcel(boolean enCarcel) {
        this.enCarcel = enCarcel;
    }

    public void setTiradasCarcel(int tiradasCarcel) {
        this.tiradasCarcel = tiradasCarcel;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }
    
    public void setPropiedades(ArrayList<Casilla> propiedades) {
        this.propiedades = propiedades;
    }

    public void setTablero(Tablero tablero){
        this.tablero = tablero;
    }

    //Otros métodos:
    //Método para añadir una propiedad al jugador. Como parámetro, la casilla a añadir.
    public void anhadirPropiedad(Casilla casilla) {
        this.propiedades.add(casilla);
    }

    //Método para eliminar una propiedad del arraylist de propiedades de jugador.
    public void eliminarPropiedad(Casilla casilla) {
        this.propiedades.remove(casilla);
    }

    //Método para añadir fortuna a un jugador
    //Como parámetro se pide el valor a añadir. Si hay que restar fortuna, se pasaría un valor negativo.
    public void sumarFortuna(float valor) {
        this.fortuna += valor;
        
    }

    //Método para sumar gastos a un jugador.
    //Parámetro: valor a añadir a los gastos del jugador (será el precio de un solar, impuestos pagados...).
    public void sumarGastos(float valor) {

    }

    /*Método para establecer al jugador en la cárcel. 
    * Se requiere disponer de las casillas del tablero para ello (por eso se pasan como parámetro).*/
    public void encarcelar(ArrayList<ArrayList<Casilla>> pos) {
        this.enCarcel = true; //Marcamos al jugador como encarcelado
        this.tiradasCarcel = 0; //Reiniciamos el contador de tiradas en la carcel.

        int posActual = this.avatar.getLugar().getPosicion();
        int carcel = tablero.encontrar_casilla("Carcel").getPosicion();

        int tiradasACarcel = 0; // Las tiradas que quedan desde la posición actual hasta llegar a la cárcel

        if(carcel < posActual){
            tiradasACarcel = (carcel - posActual + 40); //Calculamos las tiradas necesarias para llegar a la carcel desde la posición actual.
        }
        else{
            tiradasACarcel = (carcel - posActual); //Calculamos las tiradas necesarias para llegar a la carcel desde la posición actual.
        }
        this.avatar.moverAvatar(pos, tiradasACarcel); //Movemos el avatar a la casilla de la carcel.
        
    }

    public String listaPropiedades() {
        StringBuilder sb = new StringBuilder();

        if(propiedades.isEmpty()) {
            sb.append("-");
            return sb.toString();
        }

        sb.append("[ ");
        for (Casilla c : this.propiedades) {
            sb.append(c.getNombre() + " ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public String toString() {
        return "{\n\tnombre: " + nombre + ", \n\tavatar: " + avatar.getId() + ", \n\tfortuna: " + fortuna + ", \n\tpropiedades: " + listaPropiedades() + "\n}";
    }
    
}
