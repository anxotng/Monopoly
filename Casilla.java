package monopoly;

import partida.*;
import java.util.ArrayList;

public class Casilla {
    //Atributos:
    private String nombre; //Nombre de la casilla
    private String tipo; //Tipo de casilla (Solar, Especial, Transporte, Servicios, Comunidad, Suerte y Impuesto).
    private float valor; //Valor de esa casilla (en la mayoría será valor de compra, en la casilla parking se usará como el bote).
    private int posicion; //Posición que ocupa la casilla en el tablero (entero entre 1 y 40).
    private Jugador duenho; //Dueño de la casilla (por defecto sería la banca).
    private Grupo grupo; //Grupo al que pertenece la casilla (si es solar).
    private float impuesto; //Cantidad a pagar por caer en la casilla: el alquiler en solares/servicios/transportes o impuestos.
    private float hipoteca; //Valor otorgado por hipotecar una casilla
    private ArrayList<Avatar> avatares; //Avatares que están situados en la casilla.
    private ArrayList<Jugador> jugadores;
    private Tablero tablero;

    //Constructores:
    public Casilla() {
    }//Parámetros vacíos

    /*Constructor para casillas tipo Solar, Servicios o Transporte:
    * Parámetros: nombre casilla, tipo (debe ser solar, serv. o transporte), posición en el tablero, valor y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, float valor, Jugador duenho, Tablero tablero) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.valor = valor;
        this.duenho = duenho;
        this.tablero = tablero;
        this.avatares = new ArrayList<Avatar>();
    }

    /*Constructor utilizado para inicializar las casillas de tipo IMPUESTOS.
    * Parámetros: nombre, posición en el tablero, impuesto establecido y dueño.
     */
    public Casilla(String nombre, int posicion, float impuesto, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = "Impuesto";
        this.posicion = posicion;
        this.impuesto = impuesto;
        this.duenho = duenho;
        this.avatares = new ArrayList<Avatar>();
    }

    /*Constructor utilizado para crear las otras casillas (Suerte, Caja de comunidad y Especiales):
    * Parámetros: nombre, tipo de la casilla (será uno de los que queda), posición en el tablero y dueño.
     */
    public Casilla(String nombre, String tipo, int posicion, Jugador duenho) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.posicion = posicion;
        this.duenho = duenho;
        this.avatares = new ArrayList<Avatar>();
    }

    //Getters y Setters:
    public String getNombre() { 
        return nombre; 
    }
    
    public String getTipo() { 
        return tipo; 
    }

    public int getPosicion() { 
        return posicion; 
    }
    
    public Grupo getGrupo() { 
        return grupo; 
    }

    public float getValor() { 
        return valor; 
    }

    public float getImpuesto() { 
        return impuesto; 
    }

    public float getHipoteca() { 
        return hipoteca; 
    }

    public Jugador getDuenho() { 
        return duenho; 
    }

    public ArrayList<Jugador> getJugadores(){
        return jugadores;
    }

    /*public boolean hayAvatares() { 
        if(avatares.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }*/
    
    public void setGrupo(Grupo g) { 
        this.grupo = g; 
    }

    public void setPosicion(int posicion) { 
        this.posicion = posicion; 
    }

    public void setValor(float valor) { 
        this.valor = valor; 
    }

    public void setImpuesto(float impuesto) { 
        this.impuesto = impuesto; 
    }

    public void setHipoteca(float hipoteca) { 
        this.hipoteca = hipoteca; 
    }

    public void setDuenho(Jugador duenho) { 
        this.duenho = duenho; 
    }

    public void setJugadores(ArrayList<Jugador> jugadores){
        this.jugadores = jugadores;
    }

    //Método utilizado para añadir un avatar al array de avatares en casilla.
    public void anhadirAvatar(Avatar av) {
        if(!this.avatares.contains(av)){
            this.avatares.add(av);
        } else{
            System.out.println("El avatar ya está en la casilla.");
        }
    }

    //Método utilizado para eliminar un avatar del array de avatares en casilla.
    public void eliminarAvatar(Avatar av){
        if(this.avatares.contains(av)){
            this.avatares.remove(av);
        } else{
            System.out.println("El avatar no está en la casilla.");
        }
    }

    /*Método para evaluar qué hacer en una casilla concreta. Parámetros:
    * - Jugador cuyo avatar está en esa casilla.
    * - La banca (para ciertas comprobaciones).
    * - El valor de la tirada: para determinar impuesto a pagar en casillas de servicios.
    * Valor devuelto: true en caso de ser solvente (es decir, de cumplir las deudas), y false
    * en caso de no cumplirlas.*/
    /*public boolean evaluarCasilla(Jugador actual, Jugador banca, int tirada) {
    }*/

    /*Método usado para comprar una casilla determinada. Parámetros:
    * - Jugador que solicita la compra de la casilla.
    * - Banca del monopoly (es el dueño de las casillas no compradas aún).*/
    public void comprarCasilla(Jugador solicitante, Jugador banca) {
        if(tipo.equals("Solar") | tipo.equals("Transporte") | tipo.equals("Servicio")){
            if(duenho == banca && solicitante.getFortuna() >= valor){
                duenho = solicitante;
                
                solicitante.sumarFortuna(-valor);
                banca.sumarFortuna(valor);

                solicitante.anhadirPropiedad(this);
                banca.eliminarPropiedad(this);

                System.out.println("El jugador " + solicitante.getNombre() + " compra la casilla " + nombre + " por " + valor + "€. Su fortuna actual es " + solicitante.getFortuna() + "€.");
            }
        }
    }

    /*Método para añadir valor a una casilla. Utilidad:
    * - Sumar valor a la casilla de parking.
    * - Sumar valor a las casillas de solar al no comprarlas tras cuatro vueltas de todos los jugadores.
    * Este método toma como argumento la cantidad a añadir del valor de la casilla.*/
    public void sumarValor(float suma) {
        this.valor += suma;
    }

    /*Método para mostrar información sobre una casilla.
    * Devuelve una cadena con información específica de cada tipo de casilla.*/
    public String infoCasilla() {
        String info = "";
        switch(tipo.toLowerCase()) {
            case "solar" -> {
                info = "{\n   tipo: " + tipo + ",\n   grupo: " + grupo.getColorGrupo() + ",\n   propietario: " + duenho + ",\n   valor: " + valor + "\n}";
            }

            case "servicio" -> {
                info = "{\n  tipo: " + tipo + ",\n  propietario: " + duenho + ",\n  valor: " + valor + "\n}";
            }
            
            case "transporte" -> {
                info = "{\n  tipo: " + tipo + ",\n  propietario: " + duenho + ",\n  valor: " + valor + "\n}";
            }
            
            case "impuesto" -> {
                info = "{\n  tipo: " + tipo + ",\n  apagar: " + impuesto + "\n}";
            }
            
            case "especial" -> {
                switch (nombre.toLowerCase()) {
                    case "parking" -> {
                        ArrayList<Jugador> jugadoresEnParking = jugadores;
                        String listaParking = "["; // Inicia la lista con el corchete abierto
                        if (jugadoresEnParking.isEmpty()) {
                            listaParking = "[]"; // Vacío si no hay jugadores
                        }
                        else{
                            for (int i = 0; i < jugadoresEnParking.size(); i++) {
                                listaParking += jugadoresEnParking.get(i).getNombre();
                                if (i < jugadoresEnParking.size() - 1) {
                                    listaParking += ", "; // Añade una coma si no es el último jugador
                                }
                            }
                            listaParking += "]"; // Cierra la lista con el corchete
                        }
                        info = "{\n  bote: " + tablero.getBoteParking() + ",\n  jugadores: " + listaParking + "\n}";
                    }
                    case "carcel" -> {
                        ArrayList<Jugador> jugadoresEnCarcel = jugadores;
                        String listaCarcel = ""; // Inicializa la cadena de jugadores
                        if (jugadoresEnCarcel.isEmpty()) {
                            listaCarcel = "[]"; // Vacío si no hay jugadores
                        }
                        else{
                            for (int i = 0; i < jugadoresEnCarcel.size(); i++) {
                                Jugador jugador = jugadoresEnCarcel.get(i);
                                listaCarcel += "[" + jugador.getNombre() + "," + jugador.getTiradasCarcel() + "]"; // Añade nombre y turnos
                                if (i < jugadoresEnCarcel.size() - 1) {
                                    listaCarcel += " "; // Añade un espacio si no es el último jugador
                                }
                            }    
                        }
                        info = "{\n  salir: " + this + ",\n  jugadores: " + listaCarcel + "\n}";
                    }
                }
            }

            default -> {
                info = "\n{Casilla desconocida.}\n";
            }
        }

        return info;
    }

    /* Método para mostrar información de una casilla en venta.
     * Valor devuelto: texto con esa información.
     */
    /*public String casEnVenta() {
    }*/

}
